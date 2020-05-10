package com.projects.android.domain.interactors;

import com.projects.android.domain.interactors.base.Interactor;
import com.projects.android.domain.model.Task;

public interface GetTaskByIdInteractor extends Interactor {

    interface Callback{
        void onTaskRetrieved(Task task);

        void noTaskFound();
    }
}
