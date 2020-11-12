package com.alpha.classpie.rdao;

import com.alpha.classpie.util.RedisDaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/12
 */
@Repository
public class VirtualSession {

    private String aggregation(String sessionId,String name){
        return sessionId+"-"+name;
    }

    public boolean isExist(String sessionId,String name){
        return RedisDaoUtil.hasKey(aggregation(sessionId, name));
    }

    public<T> T  getAttribute(String sessionId,String name,Class<T> tClass){
        return RedisDaoUtil.getObj(aggregation(sessionId, name),tClass);
    }

    public void setAttributeExpire(String sessionId, String name,Object attribute, long expireTimeout, TimeUnit timeUnit){
        RedisDaoUtil.setex(aggregation(sessionId, name),attribute,expireTimeout,timeUnit);
    }

    public void setAttributeForever(String sessionId, String name,Object attribute){
        RedisDaoUtil.setex(aggregation(sessionId, name),attribute);
    }
}
