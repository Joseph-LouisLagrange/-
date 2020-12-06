package com.alpha.classpie.util.file;

import java.io.File;

/**
 * @author 杨能
 * @create 2020/11/23
 */
public interface GeneralEntryNode extends EntryNode {
    public File getFile();
    @Override
    default boolean isFolder(){
        return false;
    }
}
