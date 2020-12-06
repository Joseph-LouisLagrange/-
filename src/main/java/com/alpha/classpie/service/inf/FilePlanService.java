package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.file.FilePlan;
import org.jodconverter.core.office.OfficeException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/15
 */
public interface FilePlanService {
    public FilePlan[] addMultipartFiles(MultipartFile[] multipartFile) throws IOException;
    public boolean deleteFilePlan(int fileId);
    public boolean deleteFilePlan(List<Integer> fileIds) throws IOException;
    public void transferTo(OutputStream outputStream, int fileId) throws IOException;
    public void transferTo(HttpServletResponse response,int fileId) throws IOException;
    public void transferToPDF(HttpServletResponse response,int fileId) throws IOException, OfficeException;
    public void transferTo(OutputStream outputStream, FilePlan filePlan) throws IOException;
    public void transferTo(HttpServletResponse response, FilePlan filePlan) throws IOException;
    public void transferToPDF(HttpServletResponse response,FilePlan filePlan) throws IOException, OfficeException;
    public FilePlan getFilePlan(int fileId);
    public File getFile(FilePlan filePlan);
    public File getFile(int fileId);
    public File getFile(String path);
}
