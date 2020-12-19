package com.alpha.classpie.rdao.cache;


public interface DataCache<T> {
    void set(String key,T data);
    T get(String key);
    void remove(String key);
}
