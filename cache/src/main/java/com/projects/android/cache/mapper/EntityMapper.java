package com.projects.android.cache.mapper;

public interface EntityMapper<T, V> {

    /**
     *
     * @param t cachedTask
     * @return  v TaskEntity
     */

    V mapFromCached(T t);

    T mapToCached(V v);
}
