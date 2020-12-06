package com.alpha.classpie.service.impl.notice;

import com.alpha.classpie.service.inf.notice.MailService;
import com.alpha.classpie.service.inf.notice.SendNoticeService;
import lombok.Data;

import javax.validation.constraints.Email;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/21
 * email的实现
 */
@Data
public class SendNoticeServiceImpl implements SendNoticeService {
    MailService mailService;

    public SendNoticeServiceImpl(MailService mailService){
        this.mailService=mailService;
    }

    String subject;

    String content;

    @Override
    public void sendNotice(@Email String to, Object... ele) {
        String format = getCompliedContent(ele);
        mailService.sendSimpleMail(to,subject,format);
    }

    @Override
    public void sendNotice(List<String> to, Object... ele) {
        for(String t:to){
            this.sendNotice(t,ele);
        }
    }

    public String getSubject() {
        return subject;
    }

    public String getCompliedContent(Object... ele) {
        return MessageFormat.format(content, ele);
    }
}
