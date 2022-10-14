package com.coremedia.labs.plugins.adapters.jwplayer.service;

import com.coremedia.labs.plugins.adapters.jwplayer.service.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.jwplayer.jwplatform.client.MediaClient;
import com.jwplayer.jwplatform.client.PlaylistsClient;
import com.jwplayer.jwplatform.client.ThumbnailsClient;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class JWPService {

  private static final String MEDIA_LIST_PATH = "sites/{site_id}/media";
  private static final String MEDIA_BY_ID_PATH = "sites/{site_id}/media/{media_id}";
  private static final String THUMBNAILS_LIST_PATH = "sites/{site_id}/thumbnails";
  private static final String THUMBNAIL_BY_ID_PATH = "sites/{site_id}/thumbnails/{thumbnail_id}";
  private static final String PLAYLISTS_LIST_PATH = "sites/{site_id}/playlists";
  private static final String PLAYLIST_BY_ID_PATH = "sites/{site_id}/playlists/{thumbnail_id}";

  private static int DEFAULT_PAGE_LENGTH = 100;

  private String siteId;
  private String apiKey;

  private MediaClient mediaClient;
  private ThumbnailsClient thumbnailClient;
  private PlaylistsClient playlistClient;

  private ObjectMapper mapper;

  public JWPService(@NonNull String siteId, @NonNull String apiKey) {
    this.siteId = siteId;
    this.apiKey = apiKey;
    this.mediaClient = MediaClient.getClient(apiKey);
    this.thumbnailClient = ThumbnailsClient.getClient(apiKey);
    this.playlistClient = PlaylistsClient.getClient(apiKey);
    this.mapper = new ObjectMapper().registerModule(new JsonOrgModule());
  }

  public String getSiteId() {
    return siteId;
  }

  public String getApiKey() {
    return apiKey;
  }

  /**
   * List all medias.
   *
   * @return
   * @throws JWPlatformException
   */
  @NonNull
  public List<Media> listAllMedia() throws JWPlatformException {
    List<Media> result = new ArrayList<>();

    // Query params
    Map<String, String> params = new HashMap<>();
    params.put("page_length", String.format("%d", DEFAULT_PAGE_LENGTH));

    JSONObject listMediaJSON = mediaClient.listAllMedia(siteId, params);
    MediaList mediaList = mapper.convertValue(listMediaJSON, MediaList.class);
    // TODO: Load more results
    result.addAll(mediaList.getMedia());

    return result;
  }

  /**
   * Search media by the given search term.
   *
   * @param term search term
   * @return list of {@link Media} items
   *
   * @throws JWPlatformException
   */
  @NonNull
  public List<Media> searchMedia(@NonNull String term) throws JWPlatformException {
    return searchMedia(term, DEFAULT_PAGE_LENGTH);
  }

  /**
   * Search media by the given term with provided limit.
   *
   * @param term search term
   * @param limit maximum results
   * @return list of {@link Media} items
   *
   * @throws JWPlatformException
   */
  @NonNull
  public List<Media> searchMedia(@NonNull String term, int limit) throws JWPlatformException {
    List<Media> result = new ArrayList<>();

    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("q", "title:{term}");
    queryParams.add("page_length", String.format("%d", limit));

    Map<String, Object> requestVariables = Map.of(
            "site_id", siteId,
            "term", term
    );

    MediaList mediaList = doGet(MEDIA_LIST_PATH, queryParams, requestVariables, MediaList.class);
    result.addAll(mediaList.getMedia());

    return result;
  }

  /**
   * Get a media by its id.
   *
   * @param mediaId media id
   * @return
   *
   * @throws JWPlatformException
   */
  @Nullable
  public Media getMediaById(@NonNull String mediaId) throws JWPlatformException {
    JSONObject mediaResponse = mediaClient.retrieveMediaById(siteId, mediaId, Collections.emptyMap());
    return mapper.convertValue(mediaResponse, Media.class);
  }

  /**
   * Get thumbnails for the given media id.
   *
   * Note that this method will return static and video thumbnails.
   * Use either {@link #getStaticThumbnailForMedia(String) getStaticThumbnailForMedia}
   * or {@link #getVideoThumbnailForMedia(String) getVideoThumbnailForMedia} to get static or video thumbnails.
   *
   * @param mediaId media id
   * @return list of {@link Thumbnail}s
   *
   * @throws JWPlatformException
   */
  @NonNull
  public List<Thumbnail> getThumbnailsForMedia(@NonNull String mediaId) throws JWPlatformException {
    List<Thumbnail> result = new ArrayList<>();

    // Query params
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("q", "media_id:{media_id}");

    Map<String, Object> requestVariables = Map.of(
            "site_id", siteId,
            "media_id", mediaId
    );

    ThumbnailList thumbnailList = doGet(THUMBNAILS_LIST_PATH, queryParams, requestVariables, ThumbnailList.class);
    result.addAll(thumbnailList.getThumbnails());

    return result;
  }

  /**
   *
   * @param mediaId
   * @return
   * @throws JWPlatformException
   */
  @Nullable
  public Thumbnail getStaticThumbnailForMedia(@NonNull String mediaId) throws JWPlatformException {
    return getThumbnailForMediaWithType(mediaId, "static");
  }

  @Nullable
  public Thumbnail getVideoThumbnailForMedia(@NonNull String mediaId) throws JWPlatformException {
    return getThumbnailForMediaWithType(mediaId, "video");
  }

  private Thumbnail getThumbnailForMediaWithType(@NonNull String mediaId, @NonNull String thumbnailType) throws JWPlatformException{
    return getThumbnailsForMedia(mediaId)
            .stream()
            .filter(thumbnail -> thumbnail.getThumbnailType().equalsIgnoreCase(thumbnailType))
            .findFirst()
            .orElse(null);
  }

  // --- playlists ---
  public List<Playlist> listPlaylists() throws JWPlatformException {
    List<Playlist> result = new ArrayList();

    JSONObject jsonObject = playlistClient.listPlaylists(siteId, Map.of());
    PlaylistList playlistList = mapper.convertValue(jsonObject, PlaylistList.class);
    result.addAll(playlistList.getPlaylists());

    return result;
  }

  public List<Playlist> searchPlaylists(@NonNull String term) throws JWPlatformException {
    return searchPlaylists(term, DEFAULT_PAGE_LENGTH);
  }

  public List<Playlist> searchPlaylists(@NonNull String term, int limit) throws JWPlatformException {
    List<Playlist> result = new ArrayList<>();

    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("q", "title:{term}");
    queryParams.add("page_length", String.format("%d", limit));

    Map<String, Object> requestVariables = Map.of(
            "site_id", siteId,
            "term", term
    );

    PlaylistList hits = doGet(PLAYLISTS_LIST_PATH, queryParams, requestVariables, PlaylistList.class);
    result.addAll(hits.getPlaylists());

    return result;
  }



  // --- internal ---


  private <T> T doGet(String path, MultiValueMap<String, String> queryParams, Map<String, Object> variables, Class<T> responseType) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("api.jwplayer.com")
            .path("v2");

    uriBuilder.pathSegment(path);
    uriBuilder.queryParams(queryParams);

    UriComponents uriComponents = uriBuilder.buildAndExpand(variables);

    RestTemplate restTemplate = new RestTemplate();
    // create http headers and add authorization header we just created
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + apiKey);

    HttpEntity<String> request = new HttpEntity<>(headers);
    String url = uriComponents.toString();

    ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType);
    return response.getBody();
  }

}
