package com.projects.android.ui.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule {


    @Binds
    public abstract Context bindContext(Application application);


}
