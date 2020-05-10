package com.projects.android.TaskMaster;

import android.app.Application;

import com.projects.android.TaskMaster.di.AppInjector;

public class TaskMasterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDagger();
    }

    private void initializeDagger(){
        AppInjector.initialize(this);
    }
}
