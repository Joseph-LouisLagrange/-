package com.alpha.classpie.util.file;

import java.io.File;


public interface GeneralEntryNode extends EntryNode {
    public File getFile();
    @Override
    default boolean isFolder(){
        return false;
    }
}
