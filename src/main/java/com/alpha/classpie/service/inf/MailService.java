package com.alpha.classpie.service.inf;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@Service
@Validated
public interface MailService {
    public void sendCaptcha(@Email String to,int code);

    public void sendSimpleMail(@Email String to, String subject, String content);

    public void sendHtmlMail(@Email String to, String subject, String content);

    public void sendAttachmentsMail(@Email String to, String subject, String content, String filePath);

    public void sendInlineResourceMail(@Email String to, String subject, String content, String rscPath, String rscId);

    public void sendAttachmentsMailInProject(@Email String to, String subject, String content, String fileRelativePath);
}
