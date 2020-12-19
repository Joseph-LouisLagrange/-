package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.file.UserFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@Transactional
public interface UserFileService {
    public String display(Integer id);
    public UserFile getUserFile(int id);
   // public UserFile getUserFileDetail(int id);
    public UserFile createFolder(String name,Integer superiorFolderId);
    public boolean deleteUserFile(int id);
    public boolean deleteBatchUserFile(Integer[] ids);
    public boolean renameFolder(int id,String newName);
    public long getSize(int userFileId);
    public List<UserFile> getChildren(int userFileId);
    public UserFile[] addFiles(MultipartFile[] multipartFiles,Integer superiorFolderId) throws IOException;
}
