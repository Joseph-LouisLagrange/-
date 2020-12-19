package com.alpha.classpie.service.inf.notice;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SendNoticeService {
    public void sendNotice(String to,Object...ele);//单发
    public void sendNotice(List<String> to,Object...ele);//群发
    public String getSubject();
    public String getCompliedContent(Object... ele);
}
