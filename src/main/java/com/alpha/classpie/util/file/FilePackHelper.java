package com.alpha.classpie.util.file;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;


@Component
public class FilePackHelper {

    protected void pack(ZipArchiveOutputStream zipArchiveOutputStream,File file) throws IOException {
        ArchiveEntry archiveEntry = zipArchiveOutputStream.createArchiveEntry(file, file.getName());
        pack(zipArchiveOutputStream,archiveEntry,file);
    }

    protected void pack(ZipArchiveOutputStream zipArchiveOutputStream,GeneralEntryNode generalEntryNode) throws IOException {
        ArchiveEntry archiveEntry = zipArchiveOutputStream.createArchiveEntry(generalEntryNode.getFile(),generalEntryNode.getName());
        pack(zipArchiveOutputStream,archiveEntry,generalEntryNode.getFile());
    }

    //正式写出
    protected void pack(ZipArchiveOutputStream zipArchiveOutputStream,ArchiveEntry archiveEntry,File file) throws IOException {
        zipArchiveOutputStream.putArchiveEntry(archiveEntry);
        int length=-1;
        byte[] bytes=new byte[1024];
        FileInputStream fileInputStream=new FileInputStream(file);
        while ((length=fileInputStream.read(bytes))!=-1){
            zipArchiveOutputStream.write(bytes,0,length);
        }
        zipArchiveOutputStream.closeArchiveEntry();
    }


    private ZipArchiveOutputStream getZipArchiveOutputStream(OutputStream outputStream){
        ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(outputStream);
        zipArchiveOutputStream.setLevel(Deflater.DEFAULT_STRATEGY);
        zipArchiveOutputStream.setEncoding("GBK");
        return zipArchiveOutputStream;
    }

    private ZipArchiveOutputStream getZipArchiveOutputStream(File file) throws FileNotFoundException {
        return getZipArchiveOutputStream(new FileOutputStream(file));
    }

    //DFS实现的基本打包算法
    private void pack(ZipArchiveOutputStream zipArchiveOutputStream,EntryNode entryNode,String currentPos) throws IOException {
        if(entryNode.isFolder()){
            ParentEntryNode startEntryNode=(ParentEntryNode)entryNode;
            currentPos=currentPos+startEntryNode.getName()+"/";
            zipArchiveOutputStream.putArchiveEntry(new ZipArchiveEntry(currentPos));//文件夹
            while (startEntryNode.hasNext()) {
                //递归进入
                pack(zipArchiveOutputStream,startEntryNode.next(), currentPos);
            }
        }else {
            GeneralEntryNode startEntryNode=(GeneralEntryNode)entryNode;
            //写入具体文件
            ArchiveEntry archiveEntry = zipArchiveOutputStream.createArchiveEntry(startEntryNode.getFile(), currentPos + startEntryNode.getName());
            pack(zipArchiveOutputStream,archiveEntry,startEntryNode.getFile());
        }
    }

    private void finish(ZipArchiveOutputStream zipArchiveOutputStream) throws IOException {
        zipArchiveOutputStream.flush();
        zipArchiveOutputStream.close();
    }

    public void pack(OutputStream outputStream, File...files) throws IOException {
        ZipArchiveOutputStream zipArchiveOutputStream = getZipArchiveOutputStream(outputStream);
        for(File file:files){
           pack(zipArchiveOutputStream,file);
       }
        finish(zipArchiveOutputStream);
    }

    public void pack(File zipFile,File...files) throws IOException {
        ZipArchiveOutputStream zipArchiveOutputStream = getZipArchiveOutputStream(zipFile);
        for(File file:files){
            pack(zipArchiveOutputStream,file);
        }
        finish(zipArchiveOutputStream);
    }

    public void pack(OutputStream outputStream,EntryNode root) throws IOException {
        ZipArchiveOutputStream zipArchiveOutputStream = getZipArchiveOutputStream(outputStream);
        Assert.isTrue(root.isFolder(),"root EntryNode 不为Parent类型节点");
        String startName="";
        //ZipArchiveEntry rootEntry = new ZipArchiveEntry(startName);
        //rootEntry.setMethod(ZipEntry.DEFLATED);
        //zipArchiveOutputStream.putArchiveEntry(rootEntry);
        //zipArchiveOutputStream.closeArchiveEntry();
        List<EntryNode> children = ((ParentEntryNode) root).getChildren();
        pack(zipArchiveOutputStream,startName,children);
        finish(zipArchiveOutputStream);
    }



    private void pack(ZipArchiveOutputStream zipArchiveOutputStream,String startPos,List<EntryNode> entryNodes) throws IOException {
        for(EntryNode e:entryNodes){
            pack(zipArchiveOutputStream,e,startPos);//批量打包
        }
        finish(zipArchiveOutputStream);
    }


    public void pack(ZipArchiveOutputStream zipArchiveOutputStream,List<EntryNode> entryNodes) throws IOException {
        for(EntryNode e:entryNodes){
            pack(zipArchiveOutputStream,e,"");//批量打包
        }
        finish(zipArchiveOutputStream);
    }

}
