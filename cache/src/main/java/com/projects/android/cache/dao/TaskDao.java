package com.projects.android.cache.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.projects.android.cache.model.CachedTask;
import com.projects.android.data.entity.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void saveTask(CachedTask cachedTask);

    @Query("SELECT * FROM task ORDER BY id ASC")
    List<CachedTask> getAllTasks();

    @Query("SELECT * FROM  task WHERE task.id = :taskId")
    CachedTask getTaskById(long taskId);

    @Delete
    void deleteTask(CachedTask cachedTask);
}
