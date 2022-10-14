package com.coremedia.labs.plugins.adapters.jwplayer;

public interface JWPContentHubSettings {

  /**
   * The JWPlayer site id.
   * @return site id
   */
  String getSiteId();

  /**
   * The JWPlayer API key.
   * @return API key
   */
  String getApiKey();

  /**
   * Custom display name for root folder.
   * @return custom display name
   */
  String getDisplayName();

}
