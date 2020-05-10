package com.projects.android.core.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CoreModule.class})
public interface CoreComponent {

    Context getContext();
    Application getApplication();
}
