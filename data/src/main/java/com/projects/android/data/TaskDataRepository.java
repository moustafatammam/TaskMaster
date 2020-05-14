package com.projects.android.data;

import com.projects.android.data.dataSource.TaskDataStoreFactory;
import com.projects.android.data.entity.TaskEntity;
import com.projects.android.data.mapper.TaskMapper;
import com.projects.android.domain.model.Task;
import com.projects.android.domain.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class TaskDataRepository implements TaskRepository {

    private TaskDataStoreFactory mTaskDataStoreFactory;
    private TaskMapper mTaskMapper;

    @Inject
    public TaskDataRepository(TaskDataStoreFactory mTaskDataStoreFactory, TaskMapper mTaskMapper) {
        this.mTaskDataStoreFactory = mTaskDataStoreFactory;
        this.mTaskMapper = mTaskMapper;
    }


    @Override
    public Completable insert(Task task) {
        return mTaskDataStoreFactory.retrieveCacheDataStore().insert(mTaskMapper.mapToEntity(task));
    }

    @Override
    public Completable delete(Task task) {
        return mTaskDataStoreFactory.retrieveCacheDataStore().delete(mTaskMapper.mapToEntity(task));
    }

    @Override
    public Observable<Task> getTaskById(long id) {
        return mTaskDataStoreFactory.retrieveCacheDataStore().getTaskById(id)
                .map(taskEntity -> mTaskMapper.mapFromEntity(taskEntity));
    }

    @Override
    public Observable<List<Task>> getAllTasks() {
        return mTaskDataStoreFactory.retrieveCacheDataStore().getAllTasks()
                .map(taskEntities -> {
                    List<Task> taskList = new ArrayList<>();
                    for(TaskEntity task : taskEntities){
                        taskList.add(mTaskMapper.mapFromEntity(task));
                    }
                    return taskList;
                });
    }
}
