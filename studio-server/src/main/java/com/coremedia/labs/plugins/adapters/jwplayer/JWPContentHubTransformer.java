package com.coremedia.labs.plugins.adapters.jwplayer;

import com.coremedia.contenthub.api.*;
import com.coremedia.labs.plugins.adapters.jwplayer.model.*;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class JWPContentHubTransformer implements ContentHubTransformer {

  private static final Logger LOG = LoggerFactory.getLogger(JWPContentHubTransformer.class);

  @Nullable
  @Override
  public ContentModel transform(Item source, ContentHubAdapter contentHubAdapter, ContentHubContext contentHubContext) throws ContentHubContentCreationException {
    if (!(source instanceof JWPItem)) {
      throw new IllegalArgumentException("Cannot transform source " + source);
    }

    JWPItem item = (JWPItem) source;
    LOG.info("Creating content mode for item {}.", item);

    String contentName = item.getName();
    ContentModel contentModel = ContentModel.createContentModel(contentName, item.getId(), item.getCoreMediaContentType());
    contentModel.put("title", contentName);

    if (item instanceof JWPMediaItem) {
      JWPMediaItem mediaItem = (JWPMediaItem) item;
      contentModel.put("dataUrl", mediaItem.getExternalRef());

      // Create image reference
      String thumbnailUrl = mediaItem.getThumbnailUrl();
      if (StringUtils.isNotBlank(thumbnailUrl)) {
        ContentModelReference thumbnailRef = ContentModelReference.create(contentModel, "CMPicture", thumbnailUrl);
        contentModel.put("pictures", Collections.singletonList(thumbnailRef));
      }

    } else if (item instanceof JWPPlaylistItem) {
      // Create CMPlaceholder
      JWPPlaylistItem playlistItem = (JWPPlaylistItem) item;
      contentModel.put("id", playlistItem.getExternalRef());
    }

    return contentModel;
  }

  @Nullable
  @Override
  public ContentModel resolveReference(ContentHubObject owner, ContentModelReference reference, ContentHubAdapter contentHubAdapter, ContentHubContext contentHubContext) {
    Object data = reference.getData();
    if (!(data instanceof String)) {
      throw new IllegalArgumentException("Not my reference: " + reference);
    }

    String imageUrl = (String) data;
    String imageName = reference.getOwner().getContentName() + " (Thumbnail)";

    ContentModel referenceModel = ContentModel.createReferenceModel(imageName, reference.getCoreMediaContentType());
    referenceModel.put("data", new UrlBlobBuilder(owner, "thumbnail").withUrl(imageUrl).build());
    referenceModel.put("title", "Video thumbnail " + imageName)
    ;
    return referenceModel;
  }
}
