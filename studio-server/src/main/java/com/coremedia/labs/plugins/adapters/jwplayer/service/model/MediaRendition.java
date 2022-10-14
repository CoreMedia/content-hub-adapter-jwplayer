package com.coremedia.labs.plugins.adapters.jwplayer.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaRendition {

  @JsonProperty("id")
  private String schema;

  /**
   * Unique identifier for a resource
   */
  @JsonProperty("id")
  private String id;

  /**
   * Date and time at which the resource was created
   */
  @JsonProperty("created")
  private String created;

  /**
   * Date and time at which the resource was most recently modified
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

  @JsonProperty("media_rendition_template")
  private Object mediaRenditionTemplate;

  @JsonProperty("metadata")
  private Object metadata;

  @JsonProperty("height")
  private int height;

  @JsonProperty("width")
  private int width;

  /**
   * URL to access the media rendition.
   * This URL is only returned when the media rendition status is ready.
   */
  @JsonProperty("delivery_url")
  private String deliveryUrl;

  /**
   * A human-readable error message for describing failures.
   */
  @JsonProperty("error_message")
  private String errorMessage;

  /**
   * Status
   * - failed
   * - processing
   * - ready
   */
  @JsonProperty("status")
  private String status;

  /**
   * Size of the media rendition, in bytes.
   */
  @JsonProperty("filesize")
  int filesize;

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

  public Object getMediaRenditionTemplate() {
    return mediaRenditionTemplate;
  }

  public void setMediaRenditionTemplate(Object mediaRenditionTemplate) {
    this.mediaRenditionTemplate = mediaRenditionTemplate;
  }

  public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public String getDeliveryUrl() {
    return deliveryUrl;
  }

  public void setDeliveryUrl(String deliveryUrl) {
    this.deliveryUrl = deliveryUrl;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getFilesize() {
    return filesize;
  }

  public void setFilesize(int filesize) {
    this.filesize = filesize;
  }
}
