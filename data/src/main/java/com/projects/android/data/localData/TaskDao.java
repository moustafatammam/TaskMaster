package com.projects.android.data.localData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.projects.android.data.entity.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(TaskEntity taskEntity);

    @Query("SELECT * FROM  task WHERE  task.id = :taskId")
    LiveData<TaskEntity> getTaskById(long taskId);

    @Query("SELECT * FROM TASK ORDER BY id ASC")
    LiveData<List<TaskEntity>> getAllTasks();
}
