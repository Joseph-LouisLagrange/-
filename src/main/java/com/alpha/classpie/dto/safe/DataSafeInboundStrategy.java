package com.alpha.classpie.dto.safe;


public interface DataSafeInboundStrategy<T> extends DataSafeStrategy<T> {
    public T safeIn(T t);
}
