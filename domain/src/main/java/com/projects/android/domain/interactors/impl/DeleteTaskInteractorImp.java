package com.projects.android.domain.interactors.impl;

import com.projects.android.domain.executor.PostExecutionThread;
import com.projects.android.domain.executor.ThreadExecutor;
import com.projects.android.domain.interactors.base.CompletableAbstractInteractor;
import com.projects.android.domain.repository.TaskRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class DeleteTaskInteractorImp extends CompletableAbstractInteractor<Long> {
    private final TaskRepository mTaskRepository;

    @Inject
    public DeleteTaskInteractorImp(TaskRepository mTaskRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.mTaskRepository = mTaskRepository;
    }


    @Override
    public Completable buildInteractorCompletable(Long id) {
        return mTaskRepository.delete(id);
    }
}
