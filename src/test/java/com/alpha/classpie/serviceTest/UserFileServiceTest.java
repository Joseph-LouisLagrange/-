package com.alpha.classpie.serviceTest;

import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.ZipTest;
import com.alpha.classpie.pojo.file.UserFile;
import com.alpha.classpie.service.inf.FileExportService;
import com.alpha.classpie.service.inf.UserFileService;
import com.alpha.classpie.util.file.EntryNode;
import com.alpha.classpie.util.file.FilePackHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author 杨能
 * @create 2020/11/25
 */
@SpringBootTest
public class UserFileServiceTest {
    @Autowired
    UserFileService userFileService;

    byte[] bytes=RandomUtil.randomBytes(1024*1024);

    MultipartFile multipartFile1=new MockMultipartFile("file", RandomUtil.randomString(6)+".txt","text/plain",bytes);
    MultipartFile multipartFile2=new MockMultipartFile("file", RandomUtil.randomString(6)+".txt","text/plain",bytes);


    @Test
    public void createFolderTest(){
        UserFile userFile = userFileService.createFolder("新建文件夹", null);
        Assertions.assertNotNull(userFile);
        System.out.println(userFile);
    }

    @Test
    public void addFilesTest() throws IOException {
        UserFile[] userFiles = userFileService.addFiles(new MultipartFile[]{multipartFile1, multipartFile2}, 11);
        Assertions.assertNotNull(userFiles);
        System.out.println(Arrays.toString(userFiles));
    }

    @ParameterizedTest
    @ValueSource(ints = {8})
    public void deleteFolderTest(int id){
        boolean b = userFileService.deleteUserFile(id);
        Assertions.assertTrue(b);
    }

    @Autowired
    FilePackHelper filePackHelper;

    @Autowired
    FileExportService fileExportService;

    @ParameterizedTest
    @ValueSource(ints = {11})
    public void exportUserFileTest(int id) throws IOException {
        File toFile = new File("xx.zip");
        EntryNode entryNode = fileExportService.exportUserFile(id);
        ZipTest.dfs(entryNode);
        filePackHelper.pack(new FileOutputStream(toFile),entryNode);
    }
}
