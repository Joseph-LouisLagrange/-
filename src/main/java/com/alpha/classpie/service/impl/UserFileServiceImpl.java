package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.UserFileMapper;
import com.alpha.classpie.example.UserFileExample;
import com.alpha.classpie.pojo.file.FilePlan;
import com.alpha.classpie.pojo.file.UserFile;
import com.alpha.classpie.service.inf.FilePlanService;
import com.alpha.classpie.service.inf.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("defaultUserFileService")
@Transactional
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    UserFileMapper userFileMapper;

    @Autowired
    FilePlanService filePlanService;

    public UserFile wrap(UserFile userFile){
        if(!userFile.getIsFolder()){
            userFile.setFilePlan(filePlanService.getFilePlan(userFile.getFileId()));
        }
        return userFile;
    }

    /**
     * 打印树结构
     * @param id
     */
    @Override
    public String display(Integer id) {
        UserFile userFile = getUserFile(id);
        StringBuffer stringBuffer=new StringBuffer();
        recursionDisplay(userFile,"",stringBuffer);
        return stringBuffer.toString();
    }

    public void recursionDisplay(UserFile userFile,String space,StringBuffer stringBuffer){
        if(userFile.getIsFolder()){
            stringBuffer.append(space).append(userFile).append('\n');
            List<UserFile> children = getChildren(userFile.getId());
            for(UserFile u:children){
                recursionDisplay(u,space+"      ",stringBuffer);
            }
        }else {
            stringBuffer.append(space).append(userFile).append('\n');
        }
    }

    @Override
    public UserFile getUserFile(int id) {
        UserFile userFile = userFileMapper.selectByPrimaryKey(id);
        if(userFile==null) return null;
        return wrap(userFile);
    }

    @Override
    public UserFile createFolder(String name, Integer superiorFolderId) {
        UserFile userFile = new UserFile(null, name, true, superiorFolderId, null,new Date());
        userFileMapper.insert(userFile);
        return wrap(userFile);
    }

    private boolean deleteFolder(int id){
        List<UserFile> userFiles = getChildren(id);
        for (UserFile userFile : userFiles) {
            if (userFile.getIsFolder())
                //递归删除
                deleteUserFile(userFile.getId());
            else {
                //表示为单文件,进行删除
                filePlanService.deleteFilePlan(userFile.getFileId());
            }
        }
        return userFileMapper.deleteByPrimaryKey(id)>0;
    }

    public boolean deleteNormalFile(UserFile userFile){
        return filePlanService.deleteFilePlan(userFile.getFileId());
    }


    @Override
    public boolean deleteUserFile(int id) {
        UserFile userFile = getUserFile(id);
        if(userFile==null) return false;
        if(userFile.getIsFolder()){
            return deleteFolder(id);
        }else {
            return deleteNormalFile(userFile);
        }
    }

    @Override
    public boolean deleteBatchUserFile(Integer[] ids) {
        boolean result=true;
        for(int id:ids){
            result=result&&deleteUserFile(id);
        }
        return result;
    }

    @Override
    public boolean renameFolder(int id, String newName) {
        UserFile userFile = new UserFile(id);
        userFile.setName(newName);
        return userFileMapper.updateByPrimaryKeySelective(userFile)>0;
    }

    @Override
    public long getSize(int userFileId) {
        UserFile userFile = getUserFile(userFileId);
        if(userFile.getIsFolder()){
            long result=0;
            List<UserFile> children = getChildren(userFileId);
            for(UserFile u:children){
                result+=getSize(u.getId());
            }
            return result;
        }else {
            return userFile.getFilePlan().getSize();
        }
    }

    @Override
    public List<UserFile> getChildren(int userFileId) {
        UserFile userFile = getUserFile(userFileId);
        if(userFile==null || !userFile.getIsFolder()) return null;//保证为文件夹
        UserFileExample userFileExample = new UserFileExample();
        userFileExample.createCriteria().andSuperiorFolderIdEqualTo(userFileId);
        return userFileMapper.selectByExample(userFileExample).stream().map(this::wrap).collect(Collectors.toList());
    }

    @Override
    public UserFile[] addFiles(MultipartFile[] multipartFiles, Integer superiorFolderId) throws IOException {
        FilePlan[] filePlans = filePlanService.addMultipartFiles(multipartFiles);
        UserFile[] userFiles=new UserFile[filePlans.length];
        for(int i=0;i<filePlans.length;i++){
            UserFile userFile = new UserFile(filePlans[i].getName(), false, superiorFolderId, filePlans[i].getId());
            userFileMapper.insert(userFile);//插入数据
            userFiles[i]=wrap(userFile);
        }
        return userFiles;
    }
}
