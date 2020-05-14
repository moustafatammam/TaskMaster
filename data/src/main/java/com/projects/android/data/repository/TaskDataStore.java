package com.projects.android.data.repository;

import com.projects.android.data.entity.TaskEntity;



import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface TaskDataStore {

    Completable insert(TaskEntity taskEntity);

    Completable delete(TaskEntity taskEntity);

    Observable<TaskEntity> getTaskById(long id);

    Observable<List<TaskEntity>> getAllTasks();
}
