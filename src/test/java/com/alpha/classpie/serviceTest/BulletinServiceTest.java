package com.alpha.classpie.serviceTest;

import cn.hutool.core.util.RandomUtil;
import com.alpha.classpie.dto.DataWrapper;
import com.alpha.classpie.pojo.bulletin.Bulletin;
import com.alpha.classpie.pojo.comment.BulletinComment;
import com.alpha.classpie.pojo.comment.Comment;
import com.alpha.classpie.service.inf.BulletinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/24
 */
@SpringBootTest
public class BulletinServiceTest {

    @Autowired
    BulletinService bulletinService;

    @Autowired
    DataWrapper dataWrapper;

    ObjectMapper objectMapper=new ObjectMapper();

    @ParameterizedTest
    @ValueSource(ints = {1})
    public void getBulletinCommentsTest(int bulletinId){
        List<BulletinComment> bulletinComments = bulletinService.getBulletinComments(bulletinId);
        org.assertj.core.api.Assertions.assertThat(bulletinComments)
                .isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {16})
    public void deleteBulletinCommentsTest(int id){
        boolean b = bulletinService.deleteComment(id);
        Assertions.assertTrue(b);
    }

    @ParameterizedTest
    @CsvSource("1,新公告,公告简历型的,1,10")
    public void editBulletinTest(Integer id, String name, String resume, Integer publisherId, Integer courseId){
        Date datetime=new Date();
        bulletinService.editBulletin(new Bulletin(id,name,resume,publisherId,courseId,datetime),courseId,id,publisherId);
    }

    @ParameterizedTest
    @CsvSource("10,型的评论,1")
    public void addBulletinCommentTest(Integer userId, String content, Integer bulletinId) throws JsonProcessingException {
        Comment comment = bulletinService.addBulletinComment(new BulletinComment(null, userId, null, content, bulletinId), userId);
        Assertions.assertNotNull(comment);
        String s = objectMapper.writeValueAsString(comment);
        System.out.println(s);
    }

    @ParameterizedTest
    @CsvSource("1,10")
    public void markReadTest(int bulletinId,int userId){
        boolean b = bulletinService.markRead(bulletinId, userId);
        Assertions.assertTrue(b);
    }

    @ParameterizedTest
    @CsvSource("10,1")
    public void releaseBulletinTest(int courseId,int userId){
        Bulletin bulletin = new Bulletin(null,"新公告","公告简历",userId,courseId,new Date());
        System.out.println(bulletinService.releaseBulletin(bulletin, courseId, userId));
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    public void getBulletinsTest(int courseId) throws Exception{
        List<Bulletin> bulletins = bulletinService.getBulletins(courseId);
        bulletins.stream().map(dataWrapper::doBulletinDetailWrap)
                .map(bulletinDetailWrapper -> {
                    try {
                        return objectMapper.writeValueAsString(bulletinDetailWrapper);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .forEach(System.out::println);
    }
}
