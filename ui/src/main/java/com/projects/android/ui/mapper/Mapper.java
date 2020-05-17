package com.projects.android.ui.mapper;

public interface Mapper<V, D> {

    /**
     *
     * @param d presentationTask
     * @return  v taskView
     */
    V mapToTaskView(D d);

    D mapFromTaskView(V v);
}
