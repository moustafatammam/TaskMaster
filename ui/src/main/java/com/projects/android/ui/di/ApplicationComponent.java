package com.projects.android.ui.di;

import android.app.Application;
import android.content.Context;

import com.projects.android.ui.TaskApplication;
import com.projects.android.ui.di.module.ApplicationModule;
import com.projects.android.ui.di.module.CacheModule;
import com.projects.android.ui.di.module.DataModule;
import com.projects.android.ui.di.module.DomainModule;
import com.projects.android.ui.di.module.PresentationModule;
import com.projects.android.ui.di.module.UiModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApplicationModule.class, CacheModule.class,
        DataModule.class, DomainModule.class, PresentationModule.class, UiModule.class, AndroidSupportInjectionModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
         Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(TaskApplication app);


}
