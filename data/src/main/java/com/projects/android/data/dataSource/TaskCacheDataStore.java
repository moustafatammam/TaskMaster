package com.projects.android.data.dataSource;

import com.projects.android.data.entity.TaskEntity;
import com.projects.android.data.repository.TaskCache;
import com.projects.android.data.repository.TaskDataStore;
import com.projects.android.domain.model.Task;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class TaskCacheDataStore implements TaskDataStore {

    private TaskCache mTaskCache;

    @Inject
    public TaskCacheDataStore(TaskCache mTaskCache){

        this.mTaskCache = mTaskCache;
    }

    @Override
    public Completable insert(TaskEntity taskEntity) {
        return mTaskCache.saveTask(taskEntity);
    }

    @Override
    public Completable delete(TaskEntity taskEntity) {
        return mTaskCache.deleteTask(taskEntity);
    }

    @Override
    public Observable<TaskEntity> getTaskById(long id) {
        return mTaskCache.getTaskById(id);
    }

    @Override
    public Observable<List<TaskEntity>> getAllTasks() {
        return mTaskCache.getAllTasks();
    }
}
