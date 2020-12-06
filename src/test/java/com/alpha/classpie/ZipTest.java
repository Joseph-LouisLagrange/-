package com.alpha.classpie;

import com.alpha.classpie.service.inf.FileExportService;
import com.alpha.classpie.util.file.EntryNode;
import com.alpha.classpie.util.file.FilePackHelper;
import com.alpha.classpie.util.file.GeneralEntryNode;
import com.alpha.classpie.util.file.ParentEntryNode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;

/**
 * @author 杨能
 * @create 2020/11/23
 */
@SpringBootTest
public class ZipTest {

    @Autowired
    FilePackHelper filePackHelper;

    String basePath="C:\\Users\\ASUS\\Desktop\\测试文件";

    @Autowired
    FileExportService fileExportService;

    private File getFile(String sub){
        return new File(basePath,sub);
    }
    @Test
    public void test() throws IOException {
        File toFile = new File("xx.zip");
        EntryNode entryNode = fileExportService.exportAllSubmittedTask(5);
        //filePackHelper.pack(new FileOutputStream(toFile),entryNode);
        dfs(entryNode);
        //filePackHelper.pack(toFile,getFile("1.txt"),getFile("2.txt"));
        //filePackHelper.pack(new FileOutputStream(toFile),entryNode);
    }

    public static void dfs(EntryNode entryNode){
        if(entryNode.isFolder()){
            ParentEntryNode parentEntryNode= (ParentEntryNode) entryNode;
            System.out.println("父:"+parentEntryNode.getName());
            while (parentEntryNode.hasNext()){
                dfs(parentEntryNode.next());
            }
        }else {
            System.out.println("子:"+entryNode.getName()+"路径："+((GeneralEntryNode)entryNode).getFile().getAbsolutePath());
        }
    }
}
