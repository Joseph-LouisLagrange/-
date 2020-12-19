package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.BulletinCommentMapper;
import com.alpha.classpie.dao.BulletinMapper;
import com.alpha.classpie.dao.BulletinReadMapper;
import com.alpha.classpie.example.BulletinCommentExample;
import com.alpha.classpie.example.BulletinExample;
import com.alpha.classpie.example.BulletinReadExample;
import com.alpha.classpie.pojo.bulletin.Bulletin;
import com.alpha.classpie.pojo.bulletin.BulletinRead;
import com.alpha.classpie.pojo.comment.BulletinComment;
import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.service.inf.BulletinService;
import com.alpha.classpie.service.inf.comment.DefaultCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("defaultBulletinService")
public class BulletinServiceImpl extends DefaultCommentService implements BulletinService {

    @Autowired
    BulletinMapper bulletinMapper;

    @Autowired
    BulletinCommentMapper bulletinCommentMapper;

    @Autowired
    BulletinReadMapper bulletinReadMapper;


    public void safe(Bulletin bulletin,int courseId,int userId){
        bulletin.setCourseId(courseId);
        bulletin.setId(null);
        bulletin.setPublisherId(userId);
        bulletin.setDatetime(new Date());
    }

    @Override
    public Bulletin releaseBulletin(Bulletin bulletin, int courseId, int userId) {
        safe(bulletin, courseId, userId);
        bulletinMapper.insertSelective(bulletin);
        return bulletin;
    }

    @Override
    public boolean deleteBulletin(int bulletinId) {
        return bulletinMapper.deleteByPrimaryKey(bulletinId)>0;
    }

    @Override
    public Bulletin editBulletin(Bulletin bulletin, int courseId, int targetBulletinId, int userId) {
        safe(bulletin, courseId, userId);
        bulletin.setId(targetBulletinId);
        bulletinMapper.updateByPrimaryKeySelective(bulletin);
        return bulletin;
    }

    @Override
    public List<Bulletin> getBulletins(int courseId) {
        BulletinExample bulletinExample = new BulletinExample();
        bulletinExample.setOrderByClause("datetime DESC");
        bulletinExample.createCriteria().andCourseIdEqualTo(courseId);
        return bulletinMapper.selectByExample(bulletinExample);
    }

    @Override
    public Bulletin getBulletin(int bulletin) {
        return bulletinMapper.selectByPrimaryKey(bulletin);
    }

    @Override
    public long getReadCount(int bulletinId) {
        BulletinReadExample bulletinReadExample = new BulletinReadExample();
        bulletinReadExample.createCriteria().andBulletinIdEqualTo(bulletinId);
        return bulletinReadMapper.countByExample(bulletinReadExample);
    }

    @Override
    public long getCommentCount(int bulletinId) {
        BulletinCommentExample bulletinCommentExample = new BulletinCommentExample();
        bulletinCommentExample.createCriteria().andBulletinIdEqualTo(bulletinId);
        return bulletinCommentMapper.countByExample(bulletinCommentExample);
    }

    @Override
    public boolean markRead(int bulletinId, int userId) {
        BulletinRead bulletinRead = new BulletinRead(bulletinId, userId);
        return bulletinReadMapper.insert(bulletinRead)>0;
    }

    @Override
    public Comment addBulletinComment(BulletinComment bulletinComment, int userId) {
        bulletinComment.setId(null);
        bulletinComment.setUserId(userId);
        super.addComment(bulletinComment);
        bulletinCommentMapper.insert(bulletinComment);
        return bulletinComment;
    }

    public List<BulletinComment> getBulletinComments(int bulletinId) {
        BulletinCommentExample bulletinCommentExample = new BulletinCommentExample();
        bulletinCommentExample.createCriteria().andBulletinIdEqualTo(bulletinId);
        return bulletinCommentMapper.selectByExample(bulletinCommentExample);
    }
}
