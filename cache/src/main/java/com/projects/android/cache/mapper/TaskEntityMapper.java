package com.projects.android.cache.mapper;

import com.projects.android.cache.model.CachedTask;
import com.projects.android.data.entity.TaskEntity;

import javax.inject.Inject;

public class TaskEntityMapper implements EntityMapper<CachedTask, TaskEntity> {

    @Inject
    TaskEntityMapper(){}


    @Override
    public TaskEntity mapFromCached(CachedTask cachedTask) {
        return new TaskEntity(cachedTask.getId(), cachedTask.getTitle(), cachedTask.getPriority(), cachedTask.getDate(),
                cachedTask.getComment(), cachedTask.getLabel(), cachedTask.getStatus());
    }

    @Override
    public CachedTask mapToCached(TaskEntity taskEntity) {
        return new CachedTask(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getPriority(), taskEntity.getDate(),
                taskEntity.getComment(), taskEntity.getLabel(), taskEntity.getStatus());
    }
}
