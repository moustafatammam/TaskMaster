package com.projects.android.presentation.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projects.android.domain.interactors.impl.DeleteTaskInteractorImp;
import com.projects.android.domain.interactors.impl.GetAllTasksInteractorImp;
import com.projects.android.domain.model.Task;
import com.projects.android.presentation.mapper.TaskMapper;
import com.projects.android.presentation.model.PresentationTask;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;

public class TaskListViewModel extends ViewModel {

    private GetAllTasksInteractorImp mGetAllTasks;
    private DeleteTaskInteractorImp mDeleteTask;
    private TaskMapper mTaskMapper;

    private MutableLiveData<Resource<List<PresentationTask>>> tasksLiveData = new MutableLiveData<>();
    private MutableLiveData<Resource<String>> deleteTaskCompletableLiveData = new MutableLiveData<>();




    @Inject
    public TaskListViewModel(GetAllTasksInteractorImp mGetAllTasks,DeleteTaskInteractorImp mDeleteTask, TaskMapper mTaskMapper) {
        this.mGetAllTasks = mGetAllTasks;
        this.mDeleteTask = mDeleteTask;
        this.mTaskMapper = mTaskMapper;
        fetchTasks();
    }



    @Override
    protected void onCleared() {
        mGetAllTasks.dispose();
        super.onCleared();
    }

    private void fetchTasks(){
        tasksLiveData.postValue(new Resource<>(State.LOADING, null, null));
        mGetAllTasks.execute(new TasksObserver(), null);
    }

    public LiveData<Resource<String>> deleteTask(PresentationTask preTask) {
        deleteTaskCompletableLiveData.postValue(new Resource<>(State.LOADING, null, null));
        mDeleteTask.execute(new DeleteTaskCompletable(), mTaskMapper.mapFromPreModel(preTask));
        return deleteTaskCompletableLiveData;
    }

    public MutableLiveData<Resource<List<PresentationTask>>> getTasksLiveData() {
        return tasksLiveData;
    }

    class TasksObserver extends DisposableObserver<List<Task>> {

        @Override
        public void onNext(List<Task> tasks) {
            List<PresentationTask> preList = new ArrayList<>();
            for(Task task : tasks){
                preList.add(mTaskMapper.mapToPreModel(task));
            }
            tasksLiveData.postValue(new Resource<>(State.SUCCESS, preList, null));
        }

        @Override
        public void onError(Throwable e) {
            tasksLiveData.postValue(new Resource<>(State.ERROR, null, e.getMessage()));
        }

        @Override
        public void onComplete() {
        }
    }

    class DeleteTaskCompletable extends DisposableCompletableObserver{

        @Override
        public void onComplete() {
            String toastMsg = "Task Deleted Successfully";
            deleteTaskCompletableLiveData.postValue(new Resource<>(State.SUCCESS, toastMsg, null));
        }

        @Override
        public void onError(Throwable e) {
            deleteTaskCompletableLiveData.postValue(new Resource<>(State.ERROR, null, e.getMessage()));
        }
    }
}
