package com.alpha.classpie.util.file;

/**
 * @author 杨能
 * @create 2020/11/23
 */
public interface EntryNode {
    public boolean isFolder();
    public String getName();
    public long getSize();
    public static ParentEntryNode ROOT(String name){
        return new ParentEntryNodeImpl(name);
    }
}
