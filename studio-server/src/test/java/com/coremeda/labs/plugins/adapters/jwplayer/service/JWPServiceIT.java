package com.coremeda.labs.plugins.adapters.jwplayer.service;

import com.coremedia.labs.plugins.adapters.jwplayer.service.JWPService;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Media;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Playlist;
import com.coremedia.labs.plugins.adapters.jwplayer.service.model.Thumbnail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JWPServiceIT {

  private JWPService testling;

  @BeforeEach
  public void setUp() {
    testling = new JWPService("testsite", "s3cr3t");
  }

  @Test
  public void testListAllMedia() throws Exception {
    List<Media> hits = testling.listAllMedia();
    assertEquals(3, hits.size());
  }

  @Test
  public void testGetMediaById() throws Exception {
    String mediaId = "J3ihQFxj";

    Media media = testling.getMediaById(mediaId);
    assertNotNull(media);
    assertEquals(mediaId, media.getId());
    assertEquals("Big Buck Bunny 720p 10mb", media.getMetadata().getTitle());
  }

  @Test
  public void testGetThumbnailsForMedia() throws Exception {
    String mediaId = "J3ihQFxj";
    List<Thumbnail> thumbnailsForMedia = testling.getThumbnailsForMedia(mediaId);
    assertEquals(2, thumbnailsForMedia.size());

    Thumbnail staticThumbnail = testling.getStaticThumbnailForMedia(mediaId);
    assertEquals("https://cdn.jwplayer.com/v2/media/J3ihQFxj/thumbnails/jz98trus.jpg", staticThumbnail.getDeliveryUrl());

    Thumbnail videoThumbnail = testling.getVideoThumbnailForMedia(mediaId);
    assertEquals("https://cdn.jwplayer.com/v2/media/J3ihQFxj/thumbnails/6voayy81.mp4", videoThumbnail.getDeliveryUrl());
  }

  @Test
  public void testSearchMedia() throws Exception {
    List<Media> hits = testling.searchMedia("bunny");
    assertEquals(1, hits.size());
  }

  @Test
  public void testListPlaylists() throws Exception {
    List<Playlist> result = testling.listPlaylists();
    assertEquals(3, result.size());
  }

  @Test
  public void testSearchPlaylists() throws Exception {
    List<Playlist> hits = testling.searchPlaylists("Default Recommendations Playlist");
    assertEquals(1, hits.size());
  }

}
