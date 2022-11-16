package com.coremedia.labs.plugins.adapters.jwplayer;

import com.coremedia.contenthub.api.*;
import com.coremedia.contenthub.api.exception.ContentHubException;
import com.coremedia.contenthub.api.pagination.PaginationRequest;
import com.coremedia.contenthub.api.search.ContentHubSearchResult;
import com.coremedia.contenthub.api.search.ContentHubSearchService;
import com.coremedia.contenthub.api.search.Sort;
import com.coremedia.contenthub.api.search.SortDirection;
import com.coremedia.labs.plugins.adapters.jwplayer.model.*;
import com.coremedia.labs.plugins.adapters.jwplayer.service.JWPService;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Media;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Playlist;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class JWPContentHubAdapter implements ContentHubAdapter, ContentHubSearchService {

  private static final Logger LOG = LoggerFactory.getLogger(JWPContentHubAdapter.class);

  private static final List<ContentHubType> SEARCH_TYPES = List.of(
          JWPContentHubType.AUDIO.getType(),
          JWPContentHubType.VIDEO.getType(),
          JWPContentHubType.PLAYLIST.getType()
  );

  private static final Map<Sort, String> SEARCH_SORT_CRITERIA = Map.of(
          new Sort("name", SortDirection.ASCENDING), "title",
          new Sort("created", SortDirection.DESCENDING), "created",
          new Sort("lastModified", SortDirection.DESCENDING), "last_modified"
  );

  private String connectionId;
  private JWPContentHubSettings settings;
  private JWPService JWPService;

  private JWPFolder rootFolder;
  private JWPFolder mediaLibraryFolder;
  private JWPFolder playlistsFolder;

  private static final String ROOT = "root";
  private static final String MEDIA = "media";
  private static final String PLAYLISTS = "playlists";

  public JWPContentHubAdapter(String connectionId, JWPContentHubSettings settings) {
    this.connectionId = connectionId;
    this.settings = settings;

    ContentHubObjectId rootFolderId = new ContentHubObjectId(connectionId, ROOT);
    rootFolder = new JWPFolder(rootFolderId, settings.getDisplayName(), JWPContentHubType.FOLDER);

    ContentHubObjectId mediaLibraryFolderId = new ContentHubObjectId(connectionId, MEDIA);
    mediaLibraryFolder = new JWPFolder(mediaLibraryFolderId, "Media Library", JWPContentHubType.FOLDER);

    ContentHubObjectId playlistsFolderId = new ContentHubObjectId(connectionId, PLAYLISTS);
    playlistsFolder = new JWPFolder(playlistsFolderId, "Playlists", JWPContentHubType.FOLDER);

    rootFolder.addSubfolder(mediaLibraryFolder);
    rootFolder.addSubfolder(playlistsFolder);

    JWPService = new JWPService(settings.getSiteId(), settings.getApiKey());
  }

  // --- ContentHubAdapter ---------------------------------------------------------------------------------------------

  @Override
  public Folder getRootFolder(ContentHubContext context) throws ContentHubException {
    return rootFolder;
  }

  @Nullable
  @Override
  public Folder getFolder(ContentHubContext context, ContentHubObjectId id) throws ContentHubException {
    Folder folder = null;
    switch (id.getExternalId()) {

      case MEDIA:
        folder = mediaLibraryFolder;
        break;

      case PLAYLISTS:
        folder = playlistsFolder;
        break;

      default:
        folder = rootFolder;
    }

    return folder;
  }

  public List<Folder> getSubFolders(ContentHubContext context, Folder folder) throws ContentHubException {
    if (folder instanceof JWPFolder) {
      JWPFolder parent = (JWPFolder) folder;
      return parent.getSubfolders();
    }
    return Collections.emptyList();
  }

  @Nullable
  @Override
  public Folder getParent(ContentHubContext context, ContentHubObject contentHubObject) throws ContentHubException {
    return rootFolder == contentHubObject ? null : getRootFolder(context);
  }

  public List<Item> getItems(ContentHubContext context, Folder folder) throws ContentHubException {
    List<Item> items = Collections.emptyList();

    try {

      if (mediaLibraryFolder == folder) {
        // List all medias
        items = JWPService.listAllMedia()
                .stream()
                .map(this::createMediaItem)
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
      }

      if (playlistsFolder == folder) {
        // List all playlists
        items = JWPService.listPlaylists()
                .stream()
                .map(this::createPlaylistItem)
                .collect(Collectors.toList());
      }

    } catch (Exception e) {
      LOG.warn("Unable to get items for folder {}. {}", folder, e);
    }

    return items;
  }

  @Nullable
  @Override
  public Item getItem(ContentHubContext context, ContentHubObjectId id) throws ContentHubException {
    Item result = null;

    String externalId = id.getExternalId();
    try {
      result = Optional.ofNullable(JWPService.getMediaById(externalId)).map(this::createMediaItem).orElse(null);
    } catch (Exception e) {
      LOG.error("Unable to fetch item {}. {}", externalId, e);
    }

    return result;
  }

  @Override
  public GetChildrenResult getChildren(ContentHubContext context, Folder folder, @Nullable PaginationRequest paginationRequest) {
    List<ContentHubObject> children = new ArrayList<>();
    children.addAll(getSubFolders(context, folder));
    children.addAll(getItems(context, folder));
    return new GetChildrenResult(children);
  }

  @Override
  public ContentHubTransformer transformer() {
    return new JWPContentHubTransformer();
  }

  // --- ContentHubSearchService ---------------------------------------------------------------------------------------

  @Override
  public Optional<ContentHubSearchService> searchService() {
    return Optional.of(this);
  }

  @Override
  public Collection<ContentHubType> supportedTypes() {
    return SEARCH_TYPES;
  }

  @Override
  public Set<Sort> supportedSortCriteria() {
    return SEARCH_SORT_CRITERIA.keySet();
  }

  @Override
  public int supportedLimit() {
    return 1000;
  }

  @Override
  public ContentHubSearchResult search(@NonNull String query,
                                       @Nullable Folder belowFolder,
                                       @Nullable ContentHubType type,
                                       Collection<String> filterQueries,
                                       List<Sort> sortCriteria,
                                       int limit) {

    if (type != null && type.getName() != null && !supportedTypes().contains(type)) {
      throw new IllegalArgumentException("Unsupported search type " + type);
    }

    if (limit < -1) {
      throw new IllegalArgumentException("limit must be >= -1");
    }

    if (limit > supportedLimit()) {
      throw new IllegalArgumentException("limit must be < " + supportedLimit());
    }

    ContentHubSearchResult result = null;

    Boolean isSearchForAllTypes = (type != null && type.getName() == null);

    try {
      List<JWPItem> resultItems = new ArrayList();

        // search media (audio / video)
        if (isSearchForAllTypes || (JWPContentHubType.AUDIO.getType().equals(type) ||
                JWPContentHubType.VIDEO.getType().equals(type))) {

          List<Media> hits = StringUtils.isNotBlank(query) ? JWPService.searchMedia(query) : JWPService.listAllMedia();
          List<JWPMediaItem> items = hits
                  .stream()
                  .filter(hit -> hit.getMediaType().equalsIgnoreCase(type.getName()))
                  .map(this::createMediaItem)
                  .collect(Collectors.toList());
          resultItems.addAll(items);
        }

        // search playlists
        if (isSearchForAllTypes || JWPContentHubType.PLAYLIST.getType().equals(type)) {
          List<JWPPlaylistItem> items = JWPService.listPlaylists()
                  .stream()
                  .map(this::createPlaylistItem)
                  .collect(Collectors.toList());
          resultItems.addAll(items);
        }

     result = new ContentHubSearchResult(resultItems);
    } catch (Exception e) {
      LOG.error("Search failed.", e);
    }

    return result;
  }

  // --- INTERNAL ------------------------------------------------------------------------------------------------------

  private JWPMediaItem createMediaItem(@NonNull Media media) {
    ContentHubObjectId hubId = new ContentHubObjectId(connectionId, media.getId());

    if ("video".equals(media.getMediaType())) {
      return new JWPVideoItem(hubId, media, JWPService);
    } else if ("audio".equals(media.getMediaType())) {
      return new JWPAudioItem(hubId, media, JWPService);
    }

    return null;
  }

  private JWPPlaylistItem createPlaylistItem(@NonNull Playlist playlist) {
    ContentHubObjectId hubId = new ContentHubObjectId(connectionId, playlist.getId());
    return new JWPPlaylistItem(hubId, playlist, JWPService);
  }


}
