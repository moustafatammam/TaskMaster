package com.projects.android.cache;

import com.projects.android.cache.database.TaskDatabase;
import com.projects.android.cache.mapper.TaskEntityMapper;
import com.projects.android.cache.model.CachedTask;
import com.projects.android.data.entity.TaskEntity;
import com.projects.android.data.repository.TaskCache;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class TaskCacheImpl implements TaskCache {

    private TaskDatabase mTaskDatabase;
    private TaskEntityMapper mTaskEntityMapper;

    @Inject
    public TaskCacheImpl(TaskDatabase mTaskDatabase, TaskEntityMapper mTaskEntityMapper) {
        this.mTaskDatabase = mTaskDatabase;
        this.mTaskEntityMapper = mTaskEntityMapper;
    }

    @Override
    public Completable saveTask(TaskEntity taskEntity) {
        return Completable.defer(() -> {
            mTaskDatabase.taskDao().saveTask(mTaskEntityMapper.mapToCached(taskEntity));
            return Completable.complete();
        });
    }

    @Override
    public Completable deleteTask(TaskEntity taskEntity) {
        return Completable.defer(() -> {
            mTaskDatabase.taskDao().deleteTask(mTaskEntityMapper.mapToCached(taskEntity));
            return Completable.complete();
        });
    }

    @Override
    public Observable<List<TaskEntity>> getAllTasks() {
        return Observable.defer(() -> Observable.just(mTaskDatabase.taskDao().getAllTasks())
                .map(cachedTasks -> {
                    List<TaskEntity> list = new ArrayList<>();
                    for(CachedTask task : cachedTasks){
                        list.add(mTaskEntityMapper.mapFromCached(task));
                    }
                    return list;
                }));
    }

    @Override
    public Observable<TaskEntity> getTaskById(long id) {
        return Observable.defer(() -> Observable.just(mTaskDatabase.taskDao().getTaskById(id))
        .map(cachedTask -> mTaskEntityMapper.mapFromCached(cachedTask)));
    }


}
