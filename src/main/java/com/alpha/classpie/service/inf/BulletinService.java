package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.bulletin.Bulletin;
import com.alpha.classpie.service.inf.comment.BulletinCommentService;


import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/24
 * 公告相关的服务
 */
public interface BulletinService extends BulletinCommentService {
    public Bulletin releaseBulletin(Bulletin bulletin,int courseId,int userId);

    public boolean deleteBulletin(int bulletinId);

    public Bulletin editBulletin(Bulletin bulletin,int courseId,int targetBulletinId,int userId);

    public List<Bulletin> getBulletins(int courseId);

    public Bulletin getBulletin(int bulletinId);

    public long getReadCount(int bulletinId);

    public long getCommentCount(int bulletinId);

    public boolean markRead(int bulletinId,int userId);
}
