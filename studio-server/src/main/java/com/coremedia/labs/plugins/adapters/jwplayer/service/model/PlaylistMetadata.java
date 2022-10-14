package com.coremedia.labs.plugins.adapters.jwplayer.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistMetadata {

  /**
   * Title of the playlist.
   * When using JW Player's HTML5 player, the playlist title is displayed in the overlay.
   * A maximum of 256 characters is permitted.
   */
  @JsonProperty("title")
  private String title;

  /**
   * Description of the playlist
   * A maximum of 7500 characters is permitted.
   */
  @JsonProperty("description")
  private String description;

  /**
   * Author of the playlist
   * A maximum of 256 characters is permitted.
   */
  @JsonProperty("author")
  private String author;

  /**
   * URL to be associated with the playlist
   * A maximum of 2048 characters is permitted.
   */
  @JsonProperty("link")
  private String link;

  /**
   * User-generated key-value pairs
   * <p>
   * When defining custom_params, include all custom parameters that should be associated with the target resource.
   * <p>
   * When updating existing custom_params, include all custom_params keys with their updated values. Any custom_params key-value pair not included within the updated custom_params in the request body is deleted.
   */
  @JsonProperty("custom_params")
  private Map<String, Object> customParams;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Map<String, Object> getCustomParams() {
    return customParams;
  }

  public void setCustomParams(Map<String, Object> customParams) {
    this.customParams = customParams;
  }
}
