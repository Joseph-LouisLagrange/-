package com.alpha.classpie.util.file;

import java.io.File;


public class GeneralEntryNodeImpl implements GeneralEntryNode {
    File file=null;
    String name=null;
    public GeneralEntryNodeImpl(String name,File file){
        this.file=file;
        this.name=name;
    }
    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return file.length();
    }
}
