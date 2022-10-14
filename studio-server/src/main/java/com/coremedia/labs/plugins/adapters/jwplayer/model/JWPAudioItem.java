package com.coremedia.labs.plugins.adapters.jwplayer.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.labs.plugins.adapters.jwplayer.service.JWPService;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Media;
import edu.umd.cs.findbugs.annotations.NonNull;

public class JWPAudioItem extends JWPMediaItem {

  public JWPAudioItem(@NonNull ContentHubObjectId objectId, @NonNull Media media, @NonNull JWPService JWPService) {
    super(objectId, media, JWPService);
  }

  @Override
  public String getCoreMediaContentType() {
    return "CMAudio";
  }

  @Override
  public ContentHubType getContentHubType() {
    return JWPContentHubType.AUDIO.getType();
  }

}
