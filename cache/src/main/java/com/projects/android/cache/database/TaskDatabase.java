package com.projects.android.cache.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.projects.android.cache.dao.TaskDao;
import com.projects.android.cache.model.CachedTask;

import javax.inject.Inject;

@Database(entities = {CachedTask.class},
        version = 1,
        exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    private static final String LOG_TAG = TaskDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "taskList";

    private static TaskDatabase sDatabaseInstance;

    @Inject
    TaskDatabase(){}


    public static TaskDatabase getInstance(Context context){
        if(sDatabaseInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "creating a new database instance");
                sDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        TaskDatabase.class, TaskDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "getting the database instance");
        return sDatabaseInstance;
    }

    public abstract TaskDao taskDao();
}
