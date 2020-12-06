package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.file.AttachmentResources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/26
 */
@Service
@Transactional
public interface AttachmentResourcesService {
    public AttachmentResources getAttachmentResources(Integer id);
    public List<AttachmentResources> getChildren(Integer courseId,Integer attachmentResourcesId);
    public AttachmentResources createAttachmentResourcesFolder(Integer courseId,String name,Integer parentId);
    public boolean deleteAttachmentResources(Integer id);
    public boolean renameAttachmentResourcesFolder(String name,Integer id);
    public boolean batchDeleteAttachmentResources(Integer[] ids);
    public long getSize(Integer id);
    public AttachmentResources[] uploadFile(Integer courseId,MultipartFile[] multipartFiles,Integer parentId) throws IOException;
    public void downloadAttachmentResources(Integer id, HttpServletResponse response) throws IOException;
}
