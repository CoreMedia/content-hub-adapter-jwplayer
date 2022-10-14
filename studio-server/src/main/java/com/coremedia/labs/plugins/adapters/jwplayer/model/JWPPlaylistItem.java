package com.coremedia.labs.plugins.adapters.jwplayer.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.preview.DetailsElement;
import com.coremedia.contenthub.api.preview.DetailsSection;
import com.coremedia.labs.plugins.adapters.jwplayer.service.JWPService;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Playlist;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JWPPlaylistItem extends JWPItem {

  private Logger LOG = LoggerFactory.getLogger(JWPPlaylistItem.class);

  private Playlist playlist;

  public JWPPlaylistItem(@NonNull ContentHubObjectId objectId, @NonNull Playlist playlist, @NonNull JWPService JWPService) {
    super(objectId, JWPService);
    this.playlist = playlist;
  }

  @Override
  public String getName() {
    return playlist.getMetadata().getTitle();
  }

  @Nullable
  @Override
  public String getDescription() {
    return playlist.getMetadata().getDescription();
  }

  @Override
  public ContentHubType getContentHubType() {
    return JWPContentHubType.PLAYLIST.getType();
  }

  public Playlist getPlaylist() {
    return playlist;
  }

  @NonNull
  @Override
  public List<DetailsSection> getDetails() {
    return List.of(
            // Details
            new DetailsSection("main", List.of(
                    new DetailsElement<>(getName(), false, SHOW_TYPE_ICON)
            ), false, false, false),

            // Metadata
            new DetailsSection("metadata", List.of(
                    new DetailsElement<>("id", playlist.getId())
            ))
    );
  }

  @Override
  public String getCoreMediaContentType() {
    return "CMPlaceholder";
  }

}
