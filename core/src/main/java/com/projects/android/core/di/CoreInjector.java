package com.projects.android.core.di;

import android.app.Application;

public class CoreInjector{

    public static CoreComponent buildComponent(Application application) {
        return DaggerCoreComponent.builder()
                .coreModule(new CoreModule(application))
                .build();
    }
}
