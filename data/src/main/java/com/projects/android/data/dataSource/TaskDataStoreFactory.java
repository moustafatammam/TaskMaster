package com.projects.android.data.dataSource;

import javax.inject.Inject;

public class TaskDataStoreFactory {

    private TaskCacheDataStore mTaskCacheDataStore;

    @Inject
    public TaskDataStoreFactory(TaskCacheDataStore mTaskCacheDataStore){
        this.mTaskCacheDataStore = mTaskCacheDataStore;
    }


    public TaskCacheDataStore retrieveCacheDataStore(){
        return mTaskCacheDataStore;
    }
}
