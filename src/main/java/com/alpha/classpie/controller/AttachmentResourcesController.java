package com.alpha.classpie.controller;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.file.AttachmentResources;
import com.alpha.classpie.service.inf.AttachmentResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/26
 */
@RestController
@RequestMapping("/attachmentResources")
public class AttachmentResourcesController {

    @Resource(name = "defaultAttachmentResourcesService")
    AttachmentResourcesService attachmentResourcesService;
    @Autowired
    DataWrapper dataWrapper;
    @RequestMapping("/getAttachmentResources")
    public List<AttachmentResources> getAttachmentResources(@RequestParam("courseId") Integer courseId,@RequestParam(value = "attachmentResourcesId",required = false) Integer attachmentResourcesId){
       return attachmentResourcesService.getChildren(courseId, attachmentResourcesId).stream().map(dataWrapper::doAttachmentResourcesWrap).collect(Collectors.toList());
    }

    @RequestMapping("/createAttachmentResourcesFolder")
    public AttachmentResources createAttachmentResourcesFolder(@RequestParam("courseId") Integer courseId
            ,@RequestParam("name") String name
            ,@RequestParam(value = "parentId",required = false) Integer parentId){
            return dataWrapper.doAttachmentResourcesWrap(attachmentResourcesService.createAttachmentResourcesFolder(courseId, name, parentId));
    }

    @RequestMapping("/deleteAttachmentResources")
    public boolean deleteAttachmentResources(@RequestParam("attachmentResourcesId") Integer attachmentResourcesId){
        return attachmentResourcesService.deleteAttachmentResources(attachmentResourcesId);
    }

    @RequestMapping("/renameAttachmentResourcesFolder")
    public boolean renameAttachmentResourcesFolder(@RequestParam("name") String name,@RequestParam("attachmentResourcesId") Integer id){
        return attachmentResourcesService.renameAttachmentResourcesFolder(name, id);
    }

    @RequestMapping("/batchDeleteAttachmentResources")
    public boolean batchDeleteAttachmentResources(@RequestBody Integer[] ids){
        return attachmentResourcesService.batchDeleteAttachmentResources(ids);
    }

    @RequestMapping("/uploadFile")
    public List<AttachmentResources> uploadFile(@RequestParam("courseId") Integer courseId
            ,@RequestParam("file") MultipartFile[] multipartFiles
            ,@RequestParam(value = "parentId",required = false) Integer parentId) throws IOException {
        return Arrays.stream(attachmentResourcesService.uploadFile(courseId, multipartFiles, parentId)).map(dataWrapper::doAttachmentResourcesWrap).collect(Collectors.toList());
    }

    @RequestMapping("/downloadAttachmentResources")
    public void downloadAttachmentResources(@RequestParam("attachmentResourcesId")int attachmentResourcesId, HttpServletResponse response) throws IOException {
        attachmentResourcesService.downloadAttachmentResources(attachmentResourcesId,response);
    }
}
