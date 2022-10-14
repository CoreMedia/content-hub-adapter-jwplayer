package com.coremedia.labs.plugins.adapters.jwplayer.configuration;

import com.coremedia.contenthub.api.ContentHubAdapterFactory;
import com.coremedia.labs.plugins.adapters.jwplayer.JWPContentHubAdapterFactory;
import com.coremedia.labs.plugins.adapters.jwplayer.JWPContentHubSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWPContentHubAdapterConfiguration {

  @Bean
  public ContentHubAdapterFactory<JWPContentHubSettings> jwplayerContentHubAdapterFactory() {
    return new JWPContentHubAdapterFactory();
  }

}
