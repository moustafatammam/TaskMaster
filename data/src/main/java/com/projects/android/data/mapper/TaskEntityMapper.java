package com.projects.android.data.mapper;


import com.projects.android.data.entity.TaskEntity;
import com.projects.android.domain.model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TaskEntityMapper {

    @Inject
    TaskEntityMapper(){}

   public Task transform(TaskEntity taskEntity){
        Task task = null;
        if (taskEntity != null){
            task = new Task(taskEntity.getId());
            task.setTitle(taskEntity.getTitle());
            task.setComment(taskEntity.getComment());
            task.setDate(taskEntity.getDate());
            task.setLabel(taskEntity.getLabel());
            task.setPriority(taskEntity.getPriority());
            task.setStatus(taskEntity.getStatus());
        }
        return task;
   }

   public List<Task> transform(Collection<TaskEntity> listOfTaskEntity){
        final List<Task> taskList = new ArrayList<>();
        for(TaskEntity taskEntity : listOfTaskEntity){
             final Task task = transform(taskEntity);
             if(task != null){
                 taskList.add(task);
             }
        }
        return taskList;
   }


}
