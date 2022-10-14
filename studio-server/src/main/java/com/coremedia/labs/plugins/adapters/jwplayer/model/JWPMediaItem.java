package com.coremedia.labs.plugins.adapters.jwplayer.model;

import com.coremedia.contenthub.api.ContentHubBlob;
import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.UrlBlobBuilder;
import com.coremedia.contenthub.api.preview.DetailsElement;
import com.coremedia.contenthub.api.preview.DetailsSection;
import com.coremedia.labs.plugins.adapters.jwplayer.JWPContentHubAdapterFactory;
import com.coremedia.labs.plugins.adapters.jwplayer.service.JWPService;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Media;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Thumbnail;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class JWPMediaItem extends JWPItem {

  private Logger LOG = LoggerFactory.getLogger(JWPMediaItem.class);

  private Media media;

  public JWPMediaItem(@NonNull ContentHubObjectId objectId, @NonNull Media media, @NonNull JWPService JWPService) {
    super(objectId, JWPService);
    this.media = media;
  }

  @Override
  public String getName() {
    return media.getMetadata().getTitle();
  }

  @Nullable
  @Override
  public String getDescription() {
    return media.getMetadata().getDescription();
  }

  public Media getMedia() {
    return media;
  }

  @NonNull
  @Override
  public List<DetailsSection> getDetails() {
    ContentHubBlob blob = getThumbnailBlob();
    Duration videoDuration = Duration.ofSeconds(media.getDuration());

    return List.of(
            // Details
            new DetailsSection("main", List.of(
                    new DetailsElement<>(getName(), false, Objects.requireNonNullElse(blob, SHOW_TYPE_ICON))
            ), false, false, false),

            // Metadata
            new DetailsSection("metadata", Stream.of(
                    new DetailsElement<>("id", media.getId()),
                    new DetailsElement<>("description", media.getMetadata().getDescription()),
                    new DetailsElement<>("hosting_type", media.getHostingType()),
                    new DetailsElement<>("status", getStatusHTML(), true),
                    new DetailsElement<>("mime_type", media.getMimeType()),
                    new DetailsElement<>("duration", DurationFormatUtils.formatDuration(videoDuration.toMillis(), "H:mm:ss", true)),
                    new DetailsElement<>("dashboard_link", getDashboardLinkHTML(), true)
            )
                    // Remove empty entries
                    .filter(p -> Objects.nonNull(p.getValue()))
                    .collect(Collectors.toUnmodifiableList()))
    );
  }

  private String getStatusHTML() {
    String styleCSSTpl = "background-color: %s; border: 1px solid %s; color: %s; padding: 1px 5px; border-radius: 3px; text-transform: uppercase; font-size: 11px;";
    String styleCss = "";

    String mediaStatus = media.getStatus();
    switch (mediaStatus) {

      case "ready":
        styleCss = String.format(styleCSSTpl, "#fff", "#2fac66", "#2fac66");
        break;

      case "failed":
        styleCss = String.format(styleCSSTpl, "#fff", "#dd342b", "#dd342b");
        break;

      default:
        styleCss = String.format(styleCSSTpl, "#fff", "#787878", "#787878");
    }


    return String.format("<span style=\"%s\">%s</span>", styleCss, mediaStatus);
  }

  @Override
  public String getThumbnailUrl() {
    try {
      return Optional.ofNullable(getJwPlayerService()
              .getStaticThumbnailForMedia(media.getId()))
              .map(Thumbnail::getDeliveryUrl)
              .orElse(null);
    } catch (JWPlatformException e) {
      LOG.warn("Unable to get thumbnail url.");
      return null;
    }
  }

  @Nullable
  @Override
  public ContentHubBlob getBlob(String classifier) {
    ContentHubBlob blob = null;

    String thumbnailUrl = getThumbnailUrl();
    if (StringUtils.isNotBlank(thumbnailUrl)) {
      blob = new UrlBlobBuilder(this, classifier).withUrl(thumbnailUrl).build();
    }
    return blob;
  }

  @Nullable
  @Override
  public ContentHubBlob getThumbnailBlob() {
    return getBlob(ContentHubBlob.THUMBNAIL_BLOB_CLASSIFIER);
  }

  private String getDashboardLink() {
    return String.format("https://dashboard.jwplayer.com/p/%s/media/%s", getJwPlayerService().getSiteId(), media.getId());
  }

  private String getDashboardLinkHTML() {
    return String.format("<a target='_blank' href='%s'>%s</a>", getDashboardLink(), getDashboardLink());
  }

}
