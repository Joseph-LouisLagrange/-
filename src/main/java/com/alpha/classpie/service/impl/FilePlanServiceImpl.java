package com.alpha.classpie.service.impl;

import cn.hutool.core.io.FileUtil;
import com.alpha.classpie.dao.FilePlanMapper;
import com.alpha.classpie.example.FilePlanExample;
import com.alpha.classpie.pojo.file.FilePlan;
import com.alpha.classpie.service.inf.FilePlanService;
import com.alpha.classpie.util.Office2PDF;
import org.apache.commons.io.FileUtils;
import org.jodconverter.core.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 杨能
 * @create 2020/11/15
 * 数据中定义的文件模型与操作系统文件系统对应操作的类
 * 缺点：对于并未实现文件系统级别的回滚
 */
@Service("defaultFilePlanService")
@Transactional
public class FilePlanServiceImpl implements FilePlanService {

    @Value("${fileplan.basePath}")
    public String filePlanBasePath =null;

    @Autowired
    Office2PDF office2PDF;

    private AtomicReference<String> currentSubPath=null;

    @Value("${fileplan.chunkThreshold}")
    private long chunkThreshold;

    {
        //随机随机一个当前的子路径
        String subPath = FileUtil.cleanInvalid(createSubPath());
        currentSubPath=new AtomicReference<>(subPath);
    }

    private String getAbsolutePath(String relativePath){
        return this.filePlanBasePath+"/"+relativePath;
    }

    //随机文件分块的文件夹名
    private String createSubPath(){
        return String.valueOf(System.currentTimeMillis());
    }

    //获取当前的可以使用的块文件夹
    protected String getParentPath(){
        File file=new File(filePlanBasePath +"/"+currentSubPath.get());
        if(!file.exists() && file.mkdirs()){
            return file.getAbsolutePath();
        }
        if(Objects.requireNonNull(file.list()).length > chunkThreshold){
            //开始计算下一个子路径
            String except = currentSubPath.get();
            while (!currentSubPath.compareAndSet(except,createSubPath()));//这里使用CAS算法
            return getParentPath();
        }
        return file.getAbsolutePath();
    }
    @Autowired
    FilePlanMapper filePlanMapper;

    //过滤文件名中不安全的字符等
    protected String safeFileName(String fileName){
        return FileUtil.cleanInvalid(UUID.randomUUID().toString()+fileName);
    }

    protected FilePlan toFilePlan(MultipartFile multipartFile) throws IOException {
        FilePlan filePlan=new FilePlan();
        filePlan.setName(multipartFile.getOriginalFilename());
        File file=new File(getParentPath(),safeFileName(filePlan.getName()));
        multipartFile.transferTo(file);
        filePlan.setSize(multipartFile.getSize());
        filePlan.setType(multipartFile.getContentType());
        //存入相对地址
        filePlan.setStoragePath(currentSubPath.get()+"/"+file.getName());
        return filePlan;
    }

    @Override
    public FilePlan[] addMultipartFiles(MultipartFile[] multipartFiles) throws IOException {
        if(multipartFiles==null||multipartFiles.length==0) return new FilePlan[0];
        List<FilePlan> filePlans=new ArrayList<>();
        for(MultipartFile multipartFile:multipartFiles){
            FilePlan filePlan = toFilePlan(multipartFile);
            filePlanMapper.insert(filePlan);
            filePlans.add(filePlan);
        }
        return filePlans.toArray(new FilePlan[0]);
    }

    @Override
    public boolean deleteFilePlan(int fileId) {
        FilePlan filePlan = filePlanMapper.selectByPrimaryKey(fileId);
        return filePlan!=null && filePlanMapper.deleteByPrimaryKey(fileId) > 0 && FileUtil.del(getAbsolutePath(filePlan.getStoragePath()));
    }


    /**
     * 这个方法批量删除，但是这是非常危险的方法
     * @param fileIds
     * @return
     * @throws IOException
     */
    @Override
    public boolean deleteFilePlan(List<Integer> fileIds) throws IOException {
        if(fileIds.isEmpty()) return true;
        FilePlanExample filePlanExample = new FilePlanExample();
        filePlanExample.createCriteria().andIdIn(fileIds);
        List<FilePlan> filePlans = filePlanMapper.selectByExample(filePlanExample);
        filePlanMapper.deleteByExample(filePlanExample);
        for(FilePlan filePlan :filePlans){
            FileUtils.forceDeleteOnExit(new File(getAbsolutePath(filePlan.getStoragePath())));
        }
        return true;
    }

    private void out2In(OutputStream outputStream,InputStream inputStream) throws IOException {
        int length=-1;
        byte[] buf=new byte[1024];
        while ((length=inputStream.read(buf))!=-1){
            outputStream.write(buf,0,length);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
    }

    @Override
    public void transferTo(OutputStream outputStream, int fileId) throws IOException {
        FilePlan filePlan = filePlanMapper.selectByPrimaryKey(fileId);
       transferTo(outputStream,filePlan);
    }

    @Override
    public void transferTo(HttpServletResponse response, int fileId) throws IOException {
        FilePlan filePlan = getFilePlan(fileId);
        transferTo(response,filePlan);
    }

    @Override
    public void transferToPDF(HttpServletResponse response, int fileId) throws IOException, OfficeException {
        FilePlan filePlan = getFilePlan(fileId);
        transferToPDF(response,filePlan);
    }

    @Override
    public void transferTo(OutputStream outputStream, FilePlan filePlan) throws IOException {
        if(filePlan!=null){
            //开始文件传输
            out2In(outputStream,FileUtil.getInputStream(getFile(filePlan.getStoragePath())));
        }
    }

    @Override
    public void transferTo(HttpServletResponse response, FilePlan filePlan) throws IOException {
        transferTo(response.getOutputStream(),filePlan);
        response.flushBuffer();
    }

    @Override
    public void transferToPDF(HttpServletResponse response, FilePlan filePlan) throws IOException, OfficeException {
        office2PDF.toPdf(getFile(filePlan),filePlan.getType(),response.getOutputStream());
        response.flushBuffer();
    }

    @Override
    public FilePlan getFilePlan(int fileId) {
        return filePlanMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public File getFile(FilePlan filePlan) {
        return getFile(filePlan.getStoragePath());
    }

    @Override
    public File getFile(int fileId) {
        return getFile(getFilePlan(fileId));
    }

    @Override
    public File getFile(String path) {
        return new File(getAbsolutePath(path));
    }

    public AtomicReference<String> getCurrentSubPath() {
        return currentSubPath;
    }

    public void setCurrentSubPath(AtomicReference<String> currentSubPath) {
        this.currentSubPath = currentSubPath;
    }
}
