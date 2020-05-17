package com.projects.android.presentation.mapper;

public interface Mapper<D, V> {

    /**
     *
     * @param d domainTask
     * @return  v presentationTask
     */
    V mapToPreModel(D d);

    D mapFromPreModel(V v);
}
