package com.projects.android.ui.di.module;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.projects.android.ui.di.FragmentKey;
import com.projects.android.ui.userInterface.InjectingFragmentFactory;
import com.projects.android.ui.userInterface.fragment.AddTaskFragment;
import com.projects.android.ui.userInterface.fragment.TaskDetailsFragment;
import com.projects.android.ui.userInterface.fragment.TaskListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class FragmentBindingModule {


    @Binds
    @IntoMap
    @FragmentKey(TaskListFragment.class)
    abstract Fragment bindTaskListFragment(TaskListFragment taskListFragment);

    @Binds
    @IntoMap
    @FragmentKey(AddTaskFragment.class)
    abstract Fragment bindAddTaskFragment(AddTaskFragment addTaskFragment);








}
