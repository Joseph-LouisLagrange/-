package com.alpha.classpie.util.file;

import java.io.File;
import java.util.Iterator;
import java.util.List;


public interface ParentEntryNode extends EntryNode , Iterator<EntryNode> {
    public void addFile(String name, File file);
    public void addFile(String name,String path);
    public ParentEntryNode createParentEntryNode(String name);

    public ParentEntryNode returnParentEntryNode();
    public List<EntryNode> getChildren();
    @Override
    default boolean isFolder(){
        return true;
    }
}
