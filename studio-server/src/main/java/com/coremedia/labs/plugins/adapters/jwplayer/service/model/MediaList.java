package com.coremedia.labs.plugins.adapters.jwplayer.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaList {

  /**
   * Offset for returned resources.
   */
  @JsonProperty("page")
  private int page;

  /**
   * Maximum number of resources to return
   */
  @JsonProperty("page_length")
  private int pageLength;

  /**
   * Number of resources available before pagination
   */
  @JsonProperty("total")
  private int total;

  /**
   * array of objects.
   */
  @JsonProperty("media")
  private List<Media> media;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageLength() {
    return pageLength;
  }

  public void setPageLength(int pageLength) {
    this.pageLength = pageLength;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<Media> getMedia() {
    return media;
  }

  public void setMedia(List<Media> media) {
    this.media = media;
  }
}
