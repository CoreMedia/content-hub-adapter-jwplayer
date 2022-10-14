package com.coremedia.labs.plugins.adapters.jwplayer.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {

  @JsonProperty("schema")
  private String schema;

  /**
   * Unique identifier for a resource
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
   * Ancilliary resources associated to the primary resource being read.
   */
  @JsonProperty("relationships")
  private Object relationships;


  @JsonProperty("metadata")
  private MediaMetadata metadata;

  /**
   * Media upload status.
   * created processing ready updating failed
   */
  @JsonProperty("status")
  private String status;

  /**
   * Media Type
   * - audio
   * - video
   */
  @JsonProperty("media_type")
  private String mediaType;

  /**
   * Indicates whether or not the media is hosted with JW Player or not.
   * - external
   * - hosted
   */
  @JsonProperty("hosting_type")
  private String hostingType;

  /**
   * Mime-Type
   * video/mp4 video/webm video/flv audio/aac audio/mpeg audio/ogg application/vnd.apple.mpegurl application/smil+xml application/dash+xml video/flash video/x-youtube
   */
  @JsonProperty("mime_type")
  private String mimeType;

  /**
   * Message describing an issue uploading or processing the media.
   */
  @JsonProperty("error_message")
  private String errorMessage;

  /**
   * Length of the media in seconds
   */
  @JsonProperty("duration")
  int duration;

  /**
   * Starting point to trim the video
   */
  @JsonProperty("trim_in_point")
  private String trimInPoint;

  /**
   * Ending point to trim the video
   */
  @JsonProperty("trim_out_point")
  private String trimOutPoint;

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

  public MediaMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(MediaMetadata metadata) {
    this.metadata = metadata;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public String getHostingType() {
    return hostingType;
  }

  public void setHostingType(String hostingType) {
    this.hostingType = hostingType;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getTrimInPoint() {
    return trimInPoint;
  }

  public void setTrimInPoint(String trimInPoint) {
    this.trimInPoint = trimInPoint;
  }

  public String getTrimOutPoint() {
    return trimOutPoint;
  }

  public void setTrimOutPoint(String trimOutPoint) {
    this.trimOutPoint = trimOutPoint;
  }
}
