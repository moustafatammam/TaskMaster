package com.projects.android.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.projects.android.domain.interactors.impl.GetAllTasksInteractorImp;
import com.projects.android.domain.model.Task;
import com.projects.android.presentation.mapper.TaskMapper;
import com.projects.android.presentation.model.PresentationTask;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

public class TaskListViewModel extends ViewModel {

    private GetAllTasksInteractorImp mGetAllTasks;
    private TaskMapper mTaskMapper;

    private MutableLiveData<Resource<List<PresentationTask>>> tasksLiveData = new MutableLiveData<>();


    @Inject
    public TaskListViewModel(GetAllTasksInteractorImp mGetAllTasks, TaskMapper mTaskMapper) {
        this.mGetAllTasks = mGetAllTasks;
        this.mTaskMapper = mTaskMapper;
        fetchTasks();
    }


    @Override
    protected void onCleared() {
        mGetAllTasks.dispose();
        super.onCleared();
    }

    public void fetchTasks(){
        tasksLiveData.postValue(new Resource<>(State.LOADING, null, null));
        mGetAllTasks.execute(new TasksObserver(), null);
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
}
