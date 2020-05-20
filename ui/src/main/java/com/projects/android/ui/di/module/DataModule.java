package com.projects.android.ui.di.module;

import com.projects.android.data.TaskDataRepository;
import com.projects.android.domain.repository.TaskRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {

    @Binds
    abstract TaskRepository bindTaskRepository(TaskDataRepository taskDataRepository);
}
