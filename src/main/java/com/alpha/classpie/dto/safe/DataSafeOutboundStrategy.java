package com.alpha.classpie.dto.safe;


public interface DataSafeOutboundStrategy<T> extends DataSafeStrategy<T> {
    public T safeOut(T t);
}
