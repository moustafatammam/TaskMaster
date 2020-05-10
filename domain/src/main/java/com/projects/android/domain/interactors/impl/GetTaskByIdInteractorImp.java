package com.projects.android.domain.interactors.impl;

import com.projects.android.domain.executor.PostExecutionThread;
import com.projects.android.domain.executor.ThreadExecutor;
import com.projects.android.domain.interactors.base.ObservableAbstractInteractor;
import com.projects.android.domain.model.Task;
import com.projects.android.domain.repository.TaskRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetTaskByIdInteractorImp extends ObservableAbstractInteractor<Task, Long> {

    private final TaskRepository mTaskRepository;

    @Inject
    public GetTaskByIdInteractorImp(TaskRepository mTaskRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.mTaskRepository = mTaskRepository;
    }

    @Override
    public Observable<Task> buildInteractorObservable(Long id) {
        return mTaskRepository.getTaskById(id);
    }
}
