package com.alpha.classpie.dto.safe;

/**
 * @author 杨能
 * @create 2020/11/9
 */
public interface DataSafeOutboundStrategy<T> extends DataSafeStrategy<T> {
    public T safeOut(T t);
}
