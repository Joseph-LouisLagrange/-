package com.alpha.classpie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableAsync
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 60 * 15)
@EnableCaching
@ServletComponentScan(basePackages = {"com.alpha.classpie.config.security"})
public class ClasspieApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ClasspieApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
