package com.alpha.classpie.serviceTest;

import cn.hutool.core.util.ArrayUtil;
import com.alpha.classpie.pojo.file.AttachmentResources;
import com.alpha.classpie.service.inf.AttachmentResourcesService;
import com.alpha.classpie.service.inf.UserFileService;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
public class AttachmentResourcesServiceTest {
    @Autowired
    AttachmentResourcesService attachmentResourcesService;

    @Autowired
    UserFileService userFileService;

    MultipartFile multipartFile1=new MockMultipartFile("file","新建文件d.txt","text/plain",new byte[]{1,2,3});
    MultipartFile multipartFile2=new MockMultipartFile("file","新建文件c.txt","text/plain",new byte[]{1,2,3});


    @ParameterizedTest
    @CsvSource("10,新建文件夹内")
    public void createAttachmentResourcesFolderRootTest(Integer courseId,String name){
        AttachmentResources attachmentResourcesFolder = attachmentResourcesService.createAttachmentResourcesFolder(courseId, name, 7);
        Assertions.assertNotNull(attachmentResourcesFolder);
        System.out.println(attachmentResourcesFolder);
    }

    @ParameterizedTest
    @CsvSource("新444文件夹,4")
    public void renameAttachmentResourcesFolderTest(String name,int id){
        boolean b = attachmentResourcesService.renameAttachmentResourcesFolder(name, id);
        Assertions.assertTrue(b);
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void uploadFileTest(int courseId) throws IOException {
        AttachmentResources[] resources = attachmentResourcesService.uploadFile(courseId,new MultipartFile[]{multipartFile1, multipartFile2}, 20);
        Assertions.assertNotNull(resources);
        disPlay();
    }


    @ParameterizedTest
    @ValueSource(ints = {20})
    public void deleteAttachmentResourcesTest(int id){
        boolean b = attachmentResourcesService.deleteAttachmentResources(id);
        Assertions.assertTrue(b);
        disPlay();
    }


    @Test
    public void batchDeleteAttachmentResourcesTest(){
        boolean b = attachmentResourcesService.batchDeleteAttachmentResources(new Integer[]{10,11,12,13,14,15});
        Assertions.assertTrue(b);
        disPlay();
    }

    @ParameterizedTest
    @ValueSource(ints = {7})
    public void getChildrenTest(int id){
        List<AttachmentResources> children = attachmentResourcesService.getChildren(10, id);
        children.forEach(System.out::println);
    }

    @Test
    public void disPlay(){
        List<AttachmentResources> children = attachmentResourcesService.getChildren(10, null);
        List<String> collect = children.stream().map(AttachmentResources::getUserFileId).map(userFileService::display).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
