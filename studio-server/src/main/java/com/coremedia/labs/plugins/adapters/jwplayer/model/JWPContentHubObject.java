package com.coremedia.labs.plugins.adapters.jwplayer.model;

import com.coremedia.contenthub.api.ContentHubObject;
import com.coremedia.contenthub.api.ContentHubObjectId;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

public abstract class JWPContentHubObject implements ContentHubObject {

  private final ContentHubObjectId objectId;

  public JWPContentHubObject(@NonNull ContentHubObjectId objectId) {
    this.objectId = objectId;
  }

  @Override
  public ContentHubObjectId getId() {
    return objectId;
  }

  @Override
  public String getDisplayName() {
    return getName();
  }

  @Nullable
  @Override
  public String getDescription() {
    return null;
  }

}
