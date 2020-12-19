package com.alpha.classpie.service.inf.notice;

import com.alpha.classpie.dao.NoticeMapper;
import com.alpha.classpie.dao.TaskNoticeMapper;
import com.alpha.classpie.example.NoticeExample;
import com.alpha.classpie.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;


@Service
public abstract class AbstractNoticeService implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;



    protected void checkBelonging(int noticeId, int userId){
        Notice notice = noticeMapper.selectByPrimaryKey(noticeId);
        Assert.isTrue(notice.getUserId().equals(userId),"权限不足");
    }


    @Override
    public List<Notice> getAllNoReadNotice(int userId) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andUserIdEqualTo(userId).andIsReadEqualTo(false);
        return noticeMapper.selectByExampleWithBLOBs(noticeExample);
    }

    @Override
    public List<Notice> getAllNotice(int userId) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andUserIdEqualTo(userId);
        return noticeMapper.selectByExampleWithBLOBs(noticeExample);
    }

    @Override
    public boolean addNotice(Notice notice) {
        return noticeMapper.insert(notice)>0;
    }

    @Override
    public boolean markReadNotice(int noticeId) {
        Notice notice = new Notice();
        notice.setId(noticeId);
        notice.setIsRead(true);
        return noticeMapper.updateByPrimaryKey(notice)>0;
    }

    @Override
    public boolean markAllBeReadNotice(int userId) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andUserIdEqualTo(userId).andIsReadEqualTo(false);
        Notice notice = new Notice();
        notice.setIsRead(true);
        return noticeMapper.updateByExampleSelective(notice,noticeExample)>0;
    }

    @Override
    public boolean deleteNotice(List<Integer> ids) {
        if(ids.isEmpty()) return true;
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andIdIn(ids);
        return noticeMapper.deleteByExample(noticeExample)>0;
    }
}
