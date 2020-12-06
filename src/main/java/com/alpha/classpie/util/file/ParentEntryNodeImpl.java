package com.alpha.classpie.util.file;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/23
 */
@Data
public class ParentEntryNodeImpl implements ParentEntryNode {
    String name=null;


    ParentEntryNode parentEntryNode=null;

    List<EntryNode> child=new ArrayList<>();

    int nextIndex=0;

    public ParentEntryNodeImpl(String name, List<File> child) {
        this.name=name;
        child.forEach(file -> addFile(file.getName(),file));
    }

    public ParentEntryNodeImpl(String name) {
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return getSize(this);
    }

    private long getSize(EntryNode entryNode){
        int size=0;
        if(entryNode.isFolder()){
            ParentEntryNode parentEntryNode= (ParentEntryNode) entryNode;
            while (parentEntryNode.hasNext()){
                size+=getSize(parentEntryNode.next());
            }
        }else {
            return entryNode.getSize();
        }
        return size;
    }

    @Override
    public void addFile(String name, File file) {
        this.child.add(new GeneralEntryNodeImpl(name, file));
    }

    @Override
    public void addFile(String name, String path) {
        addFile(name,new File(path));
    }

    @Override
    public ParentEntryNode createParentEntryNode(String name) {
        ParentEntryNodeImpl parentEntryNode = new ParentEntryNodeImpl(name);
        this.child.add(parentEntryNode);
        parentEntryNode.setParentEntryNode(this);
        return parentEntryNode;
    }

    @Override
    public ParentEntryNode returnParentEntryNode() {
        return this.getParentEntryNode();
    }

    @Override
    public List<EntryNode> getChildren() {
        return Collections.unmodifiableList(this.child);
    }

    @Override
    public boolean hasNext() {
        if(nextIndex==child.size()){
            nextIndex=0;//重置
            return false;
        }
        return true;
    }

    @Override
    public EntryNode next() {
        return child.get(nextIndex++);
    }
}
