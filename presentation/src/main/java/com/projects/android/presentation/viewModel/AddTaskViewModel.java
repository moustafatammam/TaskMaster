package com.projects.android.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.projects.android.domain.interactors.impl.AddTaskInteractorImp;
import com.projects.android.presentation.mapper.TaskMapper;
import com.projects.android.presentation.model.PresentationTask;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;

public class AddTaskViewModel extends ViewModel {

    private AddTaskInteractorImp mAddTaskInteractorImp;
    private TaskMapper mTaskMapper;

    private MutableLiveData<Resource<String>> completableLiveData = new MutableLiveData<>();

    @Inject
    public AddTaskViewModel(AddTaskInteractorImp mAddTaskInteractorImp, TaskMapper mTaskMapper) {
        this.mAddTaskInteractorImp = mAddTaskInteractorImp;
        this.mTaskMapper = mTaskMapper;
    }

    @Override
    protected void onCleared() {
        mAddTaskInteractorImp.dispose();
        super.onCleared();
    }

    private MutableLiveData<Resource<String>> addTask(PresentationTask preTask) {
        completableLiveData.postValue(new Resource<>(State.LOADING, null, null));
        mAddTaskInteractorImp.execute(new AddTaskCompletable(), mTaskMapper.mapFromPreModel(preTask));
        return completableLiveData;
    }

    class AddTaskCompletable extends DisposableCompletableObserver {

        @Override
        public void onComplete() {
            String toastMsg = "Task Added Successfully";
            completableLiveData.postValue(new Resource<>(State.SUCCESS, toastMsg, null));
        }

        @Override
        public void onError(Throwable e) {
            completableLiveData.postValue(new Resource<>(State.ERROR, null, e.getMessage()));
        }
    }
}
