package com.coremedia.labs.plugins.adapters.jwplayer.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.labs.plugins.adapters.jwplayer.service.JWPService;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Media;
import edu.umd.cs.findbugs.annotations.NonNull;

public class JWPVideoItem extends JWPMediaItem {

  public JWPVideoItem(@NonNull ContentHubObjectId objectId, @NonNull Media media, @NonNull JWPService JWPService) {
    super(objectId, media, JWPService);
  }

  @Override
  public String getCoreMediaContentType() {
    return "CMVideo";
  }

  @Override
  public ContentHubType getContentHubType() {
    return JWPContentHubType.VIDEO.getType();
  }

}
