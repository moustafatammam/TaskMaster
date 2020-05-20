package com.projects.android.ui.di.module;

import com.projects.android.domain.executor.PostExecutionThread;
import com.projects.android.ui.UiThread;
import com.projects.android.ui.userInterface.activity.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UiModule {

    @Binds
    abstract PostExecutionThread bindPostExecutionThread(UiThread uiThread);

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
