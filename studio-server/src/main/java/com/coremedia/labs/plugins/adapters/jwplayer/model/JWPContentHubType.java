package com.coremedia.labs.plugins.adapters.jwplayer.model;

import com.coremedia.contenthub.api.ContentHubType;

public enum JWPContentHubType {

  FOLDER(new ContentHubType("folder")),
  VIDEO(new ContentHubType("video")),
  AUDIO(new ContentHubType("audio")),
  PLAYLIST(new ContentHubType("playlist"));

  private ContentHubType type;

  JWPContentHubType(ContentHubType type) {
    this.type = type;
  }

  public ContentHubType getType() {
    return type;
  }

}
