package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.bulletin.Bulletin;
import com.alpha.classpie.service.inf.comment.BulletinCommentService;


import java.util.List;


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
