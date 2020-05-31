package com.projects.android.ui;

import android.app.Activity;
import android.app.Application;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.navigation.fragment.NavHostFragment;

import com.projects.android.ui.di.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

import dagger.android.HasAndroidInjector;


public class TaskApplication extends Application implements HasAndroidInjector {


    @Inject
     DispatchingAndroidInjector<Object> activityDispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }




    @Override
    public AndroidInjector androidInjector() {
        return activityDispatchingAndroidInjector;
    }
}
