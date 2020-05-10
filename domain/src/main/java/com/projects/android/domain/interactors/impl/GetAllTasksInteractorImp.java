package com.projects.android.domain.interactors.impl;

import com.projects.android.domain.executor.PostExecutionThread;
import com.projects.android.domain.executor.ThreadExecutor;
import com.projects.android.domain.interactors.base.ObservableAbstractInteractor;
import com.projects.android.domain.model.Task;
import com.projects.android.domain.repository.TaskRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAllTasksInteractorImp extends ObservableAbstractInteractor<List<Task>, Void> {

    private final TaskRepository mTaskRepository;

    @Inject
    public GetAllTasksInteractorImp(TaskRepository mTaskRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.mTaskRepository = mTaskRepository;
    }

    @Override
    public Observable<List<Task>> buildInteractorObservable(Void aVoid) {
        return mTaskRepository.getAllTasks();
    }
}
