package com.coremedia.labs.plugins.adapters.jwplayer.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Thumbnail {

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
   * Ancilliary resources associated to the primary resource being read
   */
  @JsonProperty("relationships")
  private Object relationships;

  /**
   * Link to retrieve thumbnail once its been successfully uploaded and status is set to ready.
   */
  @JsonProperty("delivery_url")
  private String deliveryUrl;

  /**
   * Upload URL for this thumbnail
   */
  @JsonProperty("upload_link")
  private String uploadLink;

  /**
   * Thumbnail type.
   * - static
   * - video
   */
  @JsonProperty("thumbnail_type")
  private String thumbnailType;

  /**
   * Source of the thumbnail upload.
   * <p>
   * Possible Values:
   * custom_upload: User-provided thumbnail
   * default: Default thumbnail automatically pulled from 10 seconds into the video
   * generic_audio: Default thumbnail created for externally hosted media with media_type set to audio
   * generic_video: Default thumbnail created for externally hosted media with media_type set to video
   * thumbstrip_image: Thumbnail selected from the thumbstrip images via video_position or thumbstrip_index
   * custom_upload default generic_audio generic_video thumbstrip_image
   */
  @JsonProperty("source_type")
  private String sourceType;

  @JsonProperty("metadata")
  private Object metadata;

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

  public String getDeliveryUrl() {
    return deliveryUrl;
  }

  public void setDeliveryUrl(String deliveryUrl) {
    this.deliveryUrl = deliveryUrl;
  }

  public String getUploadLink() {
    return uploadLink;
  }

  public void setUploadLink(String uploadLink) {
    this.uploadLink = uploadLink;
  }

  public String getThumbnailType() {
    return thumbnailType;
  }

  public void setThumbnailType(String thumbnailType) {
    this.thumbnailType = thumbnailType;
  }

  public String getSourceType() {
    return sourceType;
  }

  public void setSourceType(String sourceType) {
    this.sourceType = sourceType;
  }

  public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }
}
