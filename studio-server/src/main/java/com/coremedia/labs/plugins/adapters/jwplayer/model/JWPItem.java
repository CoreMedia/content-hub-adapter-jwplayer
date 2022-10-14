package com.coremedia.labs.plugins.adapters.jwplayer.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.Item;
import com.coremedia.labs.plugins.adapters.jwplayer.service.JWPService;
import edu.umd.cs.findbugs.annotations.NonNull;

public abstract class JWPItem extends JWPContentHubObject implements Item {

  private JWPService JWPService;

  public JWPItem(@NonNull ContentHubObjectId objectId, @NonNull JWPService JWPService) {
    super(objectId);
    this.JWPService = JWPService;
  }

  JWPService getJwPlayerService() {
    return JWPService;
  }

  public String getThumbnailUrl() {
    return null;
  }

  public String getExternalRef() {
    return String.format("%s://%s/%s", getId().getConnectionId(), getContentHubType().getName(), getId().getExternalId());
  }

}
