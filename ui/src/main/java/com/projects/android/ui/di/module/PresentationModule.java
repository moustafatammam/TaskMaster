package com.projects.android.ui.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.projects.android.presentation.ViewModelFactory;
import com.projects.android.presentation.viewModel.TaskListViewModel;
import com.projects.android.ui.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(TaskListViewModel.class)
    abstract ViewModel bindTaskListViewModel(TaskListViewModel taskListViewModel);

    @Binds
    abstract ViewModelProvider.Factory  bindViewModelFactory(ViewModelFactory viewModelFactory);
}
