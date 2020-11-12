package com.alpha.classpie.util;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨能
 * @create 2020/11/2
 */
public class ChineseCultureUtil {
    public static Map<TimeUnit,String> timeUnitMap=new Hashtable<>();
    static {
        timeUnitMap.put(TimeUnit.DAYS,"天");
        timeUnitMap.put(TimeUnit.HOURS,"小时");
        timeUnitMap.put(TimeUnit.MICROSECONDS,"微秒");
        timeUnitMap.put(TimeUnit.MILLISECONDS,"毫秒");
        timeUnitMap.put(TimeUnit.MINUTES,"分钟");
        timeUnitMap.put(TimeUnit.NANOSECONDS,"纳秒");
        timeUnitMap.put(TimeUnit.SECONDS,"秒");
    }
    public static String toChineseTimeUnit(TimeUnit timeUnit){
        return timeUnitMap.get(timeUnit);
    }
}
