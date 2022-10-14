package com.coremedia.labs.plugins.adapters.jwplayer.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {

  @JsonProperty("schema")
  private String schema;

  /**
   * Unique identifier for a resource.
   */
  @JsonProperty("id")
  private String id;

  /**
   * Date and time at which the resource was created.
   */
  @JsonProperty("created")
  private String created;

  /**
   * Date and time at which the resource was most recently modified.
   */
  @JsonProperty("last_modified")
  private String lastModified;

  /**
   * Name of the type of resource.
   */
  @JsonProperty("type")
  private String type;

  /**
   *   Ancilliary resources associated to the primary resource being read.
   */
  @JsonProperty("relationships")
  private Object relationships;

  @JsonProperty("metadata")
  private PlaylistMetadata metadata;

  /**
   * Type of playlist.
   *
   * Possible values include:
   *
   *   article_matching: (Enterprise only) Insert contextually relevant videos into your articles. Filtering rules apply, including tag rules (inclusion and exclusion) and recency filtering. Article Matching playlists are limited to a maximum of 100 videos. Also read about how to Embed videos with Article Matching.
   *
   *   dynamic: Automatically sort your videos using PATCH /v2/playlists/{playlist_id}/dynamic_playlist API call to specify what to sort by (publish date for example). Dynamic playlists are limited to a maximum of 500 videos.
   *
   *           manual: Manual channels are ordered by the user manually. Manage the playlist's content using POST and PATCH /v2/playlists/{playlist_id}/manual_playlist API calls. Manual playlists are limited to a maximum of 10,000 videos.
   *
   *   recommendations: (Enterprise only) Recommendation playlists return a set of recommended videos based on a source video. Recommendations playlists are limited to a maximum of 100 videos.
   *
   *           search: (Enterprise only) Search playlists contain videos ordered by similarity to a search term or phrase. Set the rules by which the playlist is filtered by using PATCH /v2/playlists/{playlist_id}/search_playlist API call. Search playlists are limited to a maximum of 100 videos.
   *
   *           trending: (Enterprise only) Trending channels contain videos ordered by how much the videos are trending in the last few hours. Set the rules by which the playlist is filtered by using PATCH /v2/playlists/{playlist_id}/trending_playlist API call. Trending playlists are limited to a maximum of 500 videos.
   *
   *           watchlist: (Apps only) Watch list playlists return a set of videos based on viewer interactions within apps. For more information regarding Watch list playlists, please reach out to your JW Player representative.
   *
   *   article_matching dynamic manual search trending watchlist
   */
  @JsonProperty("playlist_type")
  private String playlistType;

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getLastModified() {
    return lastModified;
  }

  public void setLastModified(String lastModified) {
    this.lastModified = lastModified;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object getRelationships() {
    return relationships;
  }

  public void setRelationships(Object relationships) {
    this.relationships = relationships;
  }

  public PlaylistMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(PlaylistMetadata metadata) {
    this.metadata = metadata;
  }

  public String getPlaylistType() {
    return playlistType;
  }

  public void setPlaylistType(String playlistType) {
    this.playlistType = playlistType;
  }
}
