package com.projects.android.presentation.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
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

    private MutableLiveData<Resource<String>> addTaskCompletableLiveData = new MutableLiveData<>();

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


    public LiveData<Resource<String>> addTask(PresentationTask preTask) {
        addTaskCompletableLiveData.postValue(new Resource<>(State.LOADING, null, null));
        mAddTaskInteractorImp.execute(new AddTaskCompletable(), mTaskMapper.mapFromPreModel(preTask));
        return addTaskCompletableLiveData;
    }

    class AddTaskCompletable extends DisposableCompletableObserver {

        @Override
        public void onComplete() {
            String toastMsg = "Task Added Successfully";
            addTaskCompletableLiveData.postValue(new Resource<>(State.SUCCESS, toastMsg, null));
        }

        @Override
        public void onError(Throwable e) {
            addTaskCompletableLiveData.postValue(new Resource<>(State.ERROR, null, e.getMessage()));
        }
    }
}
