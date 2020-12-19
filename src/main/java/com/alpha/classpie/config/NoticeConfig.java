package com.alpha.classpie.config;

import com.alpha.classpie.service.impl.notice.SendNoticeServiceImpl;
import com.alpha.classpie.service.inf.notice.MailService;
import com.alpha.classpie.service.inf.notice.SendNoticeService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
public class NoticeConfig {

    @Resource(name = "defaultMailService")
    MailService mailService;

    @ConfigurationProperties(prefix = "notice.release-task")
    @Bean("releaseTask")
    public SendNoticeService releaseTaskNoticeService(){
        return new SendNoticeServiceImpl(mailService);
    }

    @ConfigurationProperties(prefix = "notice.correct-task")
    @Bean("correctTask")
    public SendNoticeService correctTaskNoticeService(){
        return new SendNoticeServiceImpl(mailService);
    }

    @ConfigurationProperties(prefix = "notice.recall-task")
    @Bean("recallTask")
    public SendNoticeService recallTaskNoticeService(){
        return new SendNoticeServiceImpl(mailService);
    }

    @ConfigurationProperties(prefix = "notice.expedite-task")
    @Bean("expediteTask")
    public SendNoticeService expediteTaskNoticeService(){
        return new SendNoticeServiceImpl(mailService);
    }
}
