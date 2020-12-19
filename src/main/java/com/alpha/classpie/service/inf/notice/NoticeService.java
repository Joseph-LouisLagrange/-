package com.alpha.classpie.service.inf.notice;

import com.alpha.classpie.pojo.Notice;
import com.alpha.classpie.pojo.task.TaskNotice;

import java.util.List;


public interface NoticeService {
    public List<Notice> getAllNoReadNotice(int userId);
    public List<Notice> getAllNotice(int userId);

    public boolean addNotice(Notice notice);
    public boolean markReadNotice(int noticeId);
    public boolean markAllBeReadNotice(int userId);
    public boolean deleteNotice(List<Integer> ids);
}
