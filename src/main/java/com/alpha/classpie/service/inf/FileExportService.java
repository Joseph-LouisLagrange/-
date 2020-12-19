package com.alpha.classpie.service.inf;

import com.alpha.classpie.util.file.EntryNode;
import org.springframework.stereotype.Service;


@Service
public interface FileExportService {
    public EntryNode exportAllSubmittedTask(int taskId);
    public EntryNode exportSubmittedTask(int taskId,Integer[] userIds);
    public EntryNode exportUserFile(int id);
}
