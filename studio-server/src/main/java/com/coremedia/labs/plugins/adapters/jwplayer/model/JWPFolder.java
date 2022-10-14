package com.coremedia.labs.plugins.adapters.jwplayer.model;

import com.coremedia.contenthub.api.ContentHubObjectId;
import com.coremedia.contenthub.api.ContentHubType;
import com.coremedia.contenthub.api.Folder;
import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class JWPFolder extends JWPContentHubObject implements Folder {

  private final String name;
  private final ContentHubType type;
  private List<Folder> subfolders;

  public JWPFolder(@NonNull ContentHubObjectId objectId, String name, JWPContentHubType type) {
    super(objectId);
    this.type = type.getType();
    this.name = name;
    this.subfolders = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @NonNull
  @Override
  public ContentHubType getContentHubType() {
    return type;
  }

  public List<Folder> getSubfolders() {
    return subfolders;
  }

  public void setSubFolders(List<Folder> subfolders) {
    this.subfolders = subfolders;
  }

  public void addSubfolder(Folder folder) {
    this.subfolders.add(folder);
  }

}
