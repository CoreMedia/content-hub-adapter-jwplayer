package com.coremedia.labs.plugins.adapters.jwplayer.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaMetadata {

  /**
   * Title.
   * A maximum of 5000 characters is permitted.
   */
  @JsonProperty("title")
  private String title;

  /**
   * Description.
   * A maximum of 25000 characters is permitted.
   */
  @JsonProperty("description")
  private String description;

  /**
   * Author.
   * A maximum of 256 characters is permitted.
   */
  @JsonProperty("author")
  private String author;

  /**
   * Length of the media in seconds.
   * This can only be defined manually for externally hosted media. The duration is set automatically for hosted media.
   */
  @JsonProperty("duration")
  int duration;

  /**
   * URL of the page where this media is published.
   * A maximum of 2048 characters is permitted.
   */
  @JsonProperty("permalink")
  private String permalink;

  /**
   * IAB category
   * Automotive Books and Literature Business and Finance Careers Education Events and Attractions Family and Relationships Fine Art Food & Drink Healthy Living Hobbies & Interests Home & Garden Medical Health Movies Music and Audio News and Politics Personal Finance Pets Pop Culture Real Estate Religion & Spirituality Science Shopping Sports Style & Fashion Technology & Computing Television Travel Video Gaming
   */
  @JsonProperty("category")
  private String category;


  /**
   * Start date and time in ISO 8601 format when media is available for streaming.
   */
  @JsonProperty("publish_start_date")
  private String publishStartDate;

  /**
   * End date and time in ISO 8601 format when media is no longer available for streaming.
   */
  @JsonProperty("publish_end_date")
  private String publishEndDate;

  /**
   * User-generated labels used to classify a video.
   * Tags are case insensitive and trailing whitespace is removed.
   */
  @JsonProperty("tags")
  private String[] tags;

  /**
   * Two-letter ISO-639-1 language code for the media.
   * This is used to index the media by language, to provide relevant playlist recommendations.
   *
   *   aa ab ae af ak am an ar as av ay az ba be bg bh bi bm bn bo br bs ca ce ch co cr cs cu cv cy da de dv dz ee el en eo es et eu fa ff fi fj fo fr fy ga gd gl gn gu gv ha he hi ho hr ht hu hy hz ia id ie ig ii ik io is it iu ja jv ka kg ki kj kk kl km kn ko kr ks ku kv kw ky la lb lg li ln lo lt lu lv mg mh mi mk ml mn mr ms mt my na nb nd ne ng nl nn no nr nv ny oc oj om or os pa pi pl ps pt qu rm rn ro ru rw sa sc sd se sg si sk sl sm sn so sq sr ss st su sv sw ta te tg th ti tk tl tn to tr ts tt tw ty ug uk ur uz ve vi vo wa wo xh yi yo za zh zu
   */
  @JsonProperty("language")
  private String language;

  /**
   * User-generated key-value pairs.
   * When defining custom_params, include all custom parameters that should be associated with the target resource.
   * When updating existing custom_params, include all custom_params keys with their updated values.
   * Any custom_params key-value pair not included within the updated custom_params in the request body is deleted.
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

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getPublishStartDate() {
    return publishStartDate;
  }

  public void setPublishStartDate(String publishStartDate) {
    this.publishStartDate = publishStartDate;
  }

  public String getPublishEndDate() {
    return publishEndDate;
  }

  public void setPublishEndDate(String publishEndDate) {
    this.publishEndDate = publishEndDate;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Map<String, Object> getCustomParams() {
    return customParams;
  }

  public void setCustomParams(Map<String, Object> customParams) {
    this.customParams = customParams;
  }
}
