package com.alpha.classpie.service.inf;

import com.alpha.classpie.util.file.EntryNode;
import org.springframework.stereotype.Service;

/**
 * @author 杨能
 * @create 2020/11/23
 * 文件资源的输出服务，如：前台的在线浏览以及下载等
 */
@Service
public interface FileExportService {
    public EntryNode exportAllSubmittedTask(int taskId);
    public EntryNode exportSubmittedTask(int taskId,Integer[] userIds);
    public EntryNode exportUserFile(int id);
}
