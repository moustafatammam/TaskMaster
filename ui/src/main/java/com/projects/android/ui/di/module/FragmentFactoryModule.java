package com.projects.android.ui.di.module;



import androidx.fragment.app.FragmentFactory;

import com.projects.android.ui.userInterface.InjectingFragmentFactory;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentFactoryModule {

    @Binds
    abstract FragmentFactory bindFragmentFactory(InjectingFragmentFactory injectingFragmentFactory);
}
