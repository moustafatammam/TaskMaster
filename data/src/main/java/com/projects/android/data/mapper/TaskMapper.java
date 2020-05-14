package com.projects.android.data.mapper;


import com.projects.android.data.entity.TaskEntity;
import com.projects.android.domain.model.Task;


import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TaskMapper implements Mapper<TaskEntity, Task> {

    @Inject
    TaskMapper(){}


    @Override
    public Task mapFromEntity(TaskEntity taskEntity) {
        return new Task(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getPriority(), taskEntity.getDate(),
                taskEntity.getComment(), taskEntity.getLabel(), taskEntity.getStatus());
    }

    @Override
    public TaskEntity mapToEntity(Task task) {
        return new TaskEntity(task.getId(), task.getTitle(), task.getPriority(), task.getDate(),
                task.getComment(), task.getLabel(), task.getStatus());
    }
}
