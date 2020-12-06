package com.alpha.classpie.service.impl.notice;


import com.alpha.classpie.service.inf.notice.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;
import java.io.File;
import java.text.MessageFormat;


@Async
@Service("defaultMailService")
public class DefaultMailService implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Value("${mail.captchaMail.template}")
    private String captchaTemplate;

    @Value("${mail.captchaMail.subject}")
    private String captchaSubject;

    @Override
    public void sendCaptcha(@Email String to,int code) {
        String content = MessageFormat.format(captchaTemplate, String.valueOf(code));
        this.sendSimpleMail(to, captchaSubject,content);
    }

    @Override
    public void sendSimpleMail(@Email String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            //简单邮件发送
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendHtmlMail(@Email String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            //Html邮件发送
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendAttachmentsMail(@Email String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator)+1);
            helper.addAttachment(fileName, file);
            //发送带附件的邮件
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void sendInlineResourceMail(@Email String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            //发送正文中有静态资源（图片）的邮件
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendAttachmentsMailInProject(@Email String to, String subject, String content, String fileRelativePath) {
        this.sendAttachmentsMail(to, subject, content, System.getProperty("user.dir")+"/"+fileRelativePath);
    }
}
