package com.projects.android.domain.interactors;

import com.projects.android.domain.interactors.base.Interactor;

public interface AddTaskInteractor extends Interactor {

    interface Callback{
        void onTaskAdded();
    }
}
