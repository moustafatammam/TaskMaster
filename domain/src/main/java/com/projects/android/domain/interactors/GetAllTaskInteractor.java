package com.projects.android.domain.interactors;

import com.projects.android.domain.interactors.base.Interactor;

public interface GetAllTaskInteractor extends Interactor {

    void onTasksRetrieved();
}
