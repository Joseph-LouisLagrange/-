package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.AttachmentResourcesMapper;
import com.alpha.classpie.example.AttachmentResourcesExample;
import com.alpha.classpie.pojo.file.AttachmentResources;
import com.alpha.classpie.pojo.file.UserFile;
import com.alpha.classpie.service.inf.AttachmentResourcesService;
import com.alpha.classpie.service.inf.FileExportService;
import com.alpha.classpie.service.inf.UserFileService;
import com.alpha.classpie.util.file.EntryNode;
import com.alpha.classpie.util.file.FilePackHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/11/26
 */
@Service("defaultAttachmentResourcesService")
@Transactional
public class AttachmentResourcesServiceImpl implements AttachmentResourcesService {

    @Resource(name = "defaultUserFileService")
    UserFileService userFileService;

    @Autowired
    AttachmentResourcesMapper attachmentResourcesMapper;

    @Autowired
    FilePackHelper filePackHelper;

    @Autowired
    FileExportService fileExportService;

    public AttachmentResources wrap(AttachmentResources attachmentResources){
        attachmentResources.setUserFile(userFileService.getUserFile(attachmentResources.getUserFileId()));
        return attachmentResources;
    }

    @Override
    public AttachmentResources getAttachmentResources(Integer id) {
        return wrap(attachmentResourcesMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<AttachmentResources> getChildren(Integer courseId, Integer attachmentResourcesId) {
        return attachmentResourcesMapper.getChildren(courseId,toUserFileId(attachmentResourcesId)).stream().map(this::wrap).collect(Collectors.toList());
    }

    private Integer toUserFileId(Integer attachmentResourcesId){
        if(attachmentResourcesId==null){
            return null;
        }else {
            AttachmentResources attachmentResources = getAttachmentResources(attachmentResourcesId);
           return attachmentResources.getUserFileId();
        }
    }

    @Override
    public AttachmentResources createAttachmentResourcesFolder(Integer courseId, String name, Integer parentId) {
        UserFile folder = userFileService.createFolder(name, toUserFileId(parentId));
        AttachmentResources attachmentResources = new AttachmentResources(courseId,folder.getId());
        attachmentResourcesMapper.insert(attachmentResources);
        return wrap(attachmentResources);
    }

    @Override
    public boolean deleteAttachmentResources(Integer id) {
        AttachmentResources attachmentResources = attachmentResourcesMapper.selectByPrimaryKey(id);
        return userFileService.deleteUserFile(attachmentResources.getUserFileId());
    }

    @Override
    public boolean renameAttachmentResourcesFolder(String name, Integer id) {
        AttachmentResources attachmentResources = getAttachmentResources(id);
        return userFileService.renameFolder(attachmentResources.getUserFileId(),name);
    }

    @Override
    public boolean batchDeleteAttachmentResources(Integer[] ids) {
        AttachmentResourcesExample attachmentResourcesExample = new AttachmentResourcesExample();
        attachmentResourcesExample.createCriteria().andIdIn(Arrays.asList(ids));
        List<AttachmentResources> attachmentResources = attachmentResourcesMapper.selectByExample(attachmentResourcesExample);
        Integer[] userFileIds = attachmentResources.stream().map(AttachmentResources::getUserFileId).toArray(Integer[]::new);
        return userFileService.deleteBatchUserFile(userFileIds);
    }

    @Override
    public long getSize(Integer id) {
        AttachmentResources attachmentResources = getAttachmentResources(id);
        return userFileService.getSize(attachmentResources.getUserFileId());
    }

    @Override
    public AttachmentResources[] uploadFile(Integer courseId, MultipartFile[] multipartFiles, Integer parentId) throws IOException {
        Integer userFileId=toUserFileId(parentId);
        AttachmentResources[] resources=new AttachmentResources[multipartFiles.length];
        UserFile[] userFiles = userFileService.addFiles(multipartFiles,userFileId);
        for(int i=0;i<resources.length;i++){
            AttachmentResources newResource = new AttachmentResources(courseId, userFiles[i].getId());
            attachmentResourcesMapper.insert(newResource);
            resources[i]=wrap(newResource);
        }
        return resources;
    }

    @Override
    public void downloadAttachmentResources(Integer id, HttpServletResponse response) throws IOException {
        Integer userFileId = toUserFileId(id);
        if(userFileId!=null){
            EntryNode root = fileExportService.exportUserFile(userFileId);
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(root.getName(), "UTF-8"));
            // 告知浏览器文件的大小
            //response.addHeader("Content-Length", "" + .getSize());
            filePackHelper.pack(response.getOutputStream(),root);
            //增加下载次数
            attachmentResourcesMapper.selfIncreasingDownloadCount(id);
        }
    }
}
