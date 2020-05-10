package com.projects.android.TaskMaster.di;

import android.app.Application;

import com.projects.android.core.di.CoreComponent;
import com.projects.android.core.di.CoreInjector;

public class AppInjector {

    public static void initialize(Application application){
         initializeCore(application);
    }

    private static void initializeCore(Application application) {
         CoreInjector.buildComponent(application);
    }
}
