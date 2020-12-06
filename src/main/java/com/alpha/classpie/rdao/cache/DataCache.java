package com.alpha.classpie.rdao.cache;

/**
 * @author 杨能
 * @create 2020/12/2
 */
public interface DataCache<T> {
    void set(String key,T data);
    T get(String key);
    void remove(String key);
}
