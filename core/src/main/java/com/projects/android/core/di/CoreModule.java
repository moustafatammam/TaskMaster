package com.projects.android.core.di;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CoreModule {

    private Application appContext;

    public CoreModule(@NonNull Application appContext){
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return appContext;
    }
    @Provides
    @Singleton
    Application provideApplicationContext(){
        return appContext;
    }
}
