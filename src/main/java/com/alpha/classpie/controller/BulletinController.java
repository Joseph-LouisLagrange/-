package com.alpha.classpie.controller;

import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.bulletin.Bulletin;
import com.alpha.classpie.service.inf.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/24
 */
@RestController
@RequestMapping("/bulletin")
public class BulletinController {

    @Resource(name = "defaultBulletinService")
    BulletinService bulletinService;

    @Autowired
    DataWrapper dataWrapper;

    @RequestMapping("/release")
    public Bulletin releaseBulletin(@RequestBody Bulletin bulletin){
        return dataWrapper.doBulletinWrap(bulletinService.releaseBulletin(bulletin,bulletin.getCourseId(),UserController.getUserId()));
    }

    @RequestMapping("/delete")
    public boolean deleteBulletin(@RequestParam("bulletinId") int id){
        return bulletinService.deleteBulletin(id);
    }

    @RequestMapping("/reEdit")
    public Bulletin editBulletin(@RequestBody Bulletin bulletin){
        return dataWrapper.doBulletinWrap(bulletinService.editBulletin(bulletin, bulletin.getCourseId(),bulletin.getId(),UserController.getUserId()));
    }

    @RequestMapping("/getAll")
    public List<Bulletin> getAllBulletin(@RequestParam("courseId") int courseId){
        return bulletinService.getBulletins(courseId);
    }

    @RequestMapping("/getBulletinDetail")
    public Bulletin getBulletinDetail(@RequestParam("bulletinId")int id){
        //标识已读
        bulletinService.markRead(id,UserController.getUserId());
        return dataWrapper.doBulletinDetailWrap(bulletinService.getBulletin(id));
    }
}
