package com.alpha.classpie.util.file;


public interface EntryNode {
    public boolean isFolder();
    public String getName();
    public long getSize();
    public static ParentEntryNode ROOT(String name){
        return new ParentEntryNodeImpl(name);
    }
}
