package com.alpha.classpie.service.inf.notice;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/21
 * 提供对上的发送与业务相关通知
 */
@Service
public interface SendNoticeService {
    public void sendNotice(String to,Object...ele);//单发
    public void sendNotice(List<String> to,Object...ele);//群发
    public String getSubject();
    public String getCompliedContent(Object... ele);
}
