package com.projects.android.ui.di.module;

import android.app.Application;

import androidx.room.Room;

import com.projects.android.cache.TaskCacheImpl;
import com.projects.android.cache.database.TaskDatabase;
import com.projects.android.data.repository.TaskCache;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CacheModule {

    @Provides
    public static TaskDatabase provideTaskDatabase(Application application){
        return Room.databaseBuilder(application.getApplicationContext(),
                TaskDatabase.class, "TasksDataBase").build();
    }

    @Binds
    public abstract TaskCache bindTaskCache(TaskCacheImpl taskCacheImp);
}
