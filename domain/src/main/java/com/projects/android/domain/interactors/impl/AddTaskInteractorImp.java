package com.projects.android.domain.interactors.impl;
import com.projects.android.domain.executor.PostExecutionThread;
import com.projects.android.domain.executor.ThreadExecutor;
import com.projects.android.domain.interactors.base.CompletableAbstractInteractor;
import com.projects.android.domain.model.Task;
import com.projects.android.domain.repository.TaskRepository;

import javax.inject.Inject;

import io.reactivex.Completable;


public class AddTaskInteractorImp extends CompletableAbstractInteractor<Task> {

    private final TaskRepository mTaskRepository;

    @Inject
    public AddTaskInteractorImp(TaskRepository mTaskRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.mTaskRepository = mTaskRepository;
    }


    @Override
    public Completable buildInteractorCompletable(Task task) {
        return mTaskRepository.insert(task);
    }
}
