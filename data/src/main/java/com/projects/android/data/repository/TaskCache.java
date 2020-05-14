package com.projects.android.data.repository;


import com.projects.android.data.entity.TaskEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface TaskCache {

    Completable saveTask(TaskEntity taskEntity);

    Completable deleteTask(TaskEntity taskEntity);

    Observable<List<TaskEntity>> getAllTasks();

    Observable<TaskEntity> getTaskById(long id);
}
