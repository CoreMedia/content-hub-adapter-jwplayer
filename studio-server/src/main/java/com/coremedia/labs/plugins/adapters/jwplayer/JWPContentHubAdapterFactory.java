package com.coremedia.labs.plugins.adapters.jwplayer;

import com.coremedia.contenthub.api.ContentHubAdapter;
import com.coremedia.contenthub.api.ContentHubAdapterFactory;
import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

@DefaultAnnotation(NonNull.class)
public class JWPContentHubAdapterFactory implements ContentHubAdapterFactory<JWPContentHubSettings> {

  public static final String ADAPTER_ID = "jwplayer";

  @Override
  public String getId() {
    return ADAPTER_ID;
  }

  @Override
  public ContentHubAdapter createAdapter(JWPContentHubSettings settings, String connectionId) {
    return new JWPContentHubAdapter(connectionId, settings);
  }

}
