package com.projects.android.data.mapper;

public interface Mapper <E, D> {

    /**
     *
     * @param e taskEntity
     * @return  d task
     */

    D mapFromEntity(E e);

    E mapToEntity(D d);
}
