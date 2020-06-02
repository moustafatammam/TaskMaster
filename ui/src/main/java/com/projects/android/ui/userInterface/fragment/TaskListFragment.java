package com.projects.android.ui.userInterface.fragment;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.projects.android.presentation.ViewModelFactory;
import com.projects.android.presentation.model.PresentationTask;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;
import com.projects.android.presentation.viewModel.TaskListViewModel;
import com.projects.android.ui.R;
import com.projects.android.ui.databinding.FragmentTaskListBinding;
import com.projects.android.ui.mapper.TaskMapper;
import com.projects.android.ui.model.TaskView;
import com.projects.android.ui.userInterface.adapter.TaskListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class TaskListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    @Inject  TaskListAdapter mTaskListAdapter;

    private FragmentTaskListBinding fragmentTaskListBinding;
    private FloatingActionButton mFloatingActionButton;
    private ProgressBar mProgressBar;
    private View emptyView;
    private FrameLayout frameLayout;

    private TaskListViewModel taskListViewModel;

    private ViewModelFactory viewModelFactory;
    private TaskMapper taskMapper;

    @Inject
    public TaskListFragment(ViewModelFactory viewModelFactory, TaskMapper taskMapper) {
        this.viewModelFactory = viewModelFactory;
        this.taskMapper = taskMapper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTaskListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false);
        return fragmentTaskListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupStateViews();
        taskListViewModel = new ViewModelProvider(this, viewModelFactory).get(TaskListViewModel.class);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(mFloatingActionButton).navigate(R.id.action_taskListFragment_to_addTaskFragment);
            }
        });

        taskListViewModel.getTasksLiveData().observe(this, new Observer<Resource<List<PresentationTask>>>() {
            @Override
            public void onChanged(Resource<List<PresentationTask>> listResource) {
                if (listResource != null){
                    handleDataState(listResource.mStatus, listResource.mData, listResource.mMessage);
                }
            }
        });
        setupRecyclerView();
    }

    private void handleDataState(State resourceState, List<PresentationTask> data, String message){

        switch (resourceState){
            case LOADING:
                setupScreenForLoadingState();
                break;
            case SUCCESS:
                setupScreenForSuccess(data);
                break;
            default:
                setupScreenForError(message);
        }
    }


    private void setupScreenForSuccess(List<PresentationTask> data){
        mProgressBar.setVisibility(View.GONE);
        if(data != null && !data.isEmpty()){
            List<TaskView> taskViewList = data.stream().map(new Function<PresentationTask, TaskView>() {
                @Override
                public TaskView apply(PresentationTask presentationTask) {
                    return taskMapper.mapToTaskView(presentationTask);
                }
            }).collect(Collectors.toList());
           /* List<TaskView> taskViewList = new ArrayList<>();
            for (PresentationTask preTask : data){
                taskViewList.add(taskMapper.mapToTaskView(preTask));
            }*/
            mTaskListAdapter.submitList(taskViewList);
            mRecyclerView.setVisibility(View.VISIBLE);
        }else{
            mRecyclerView.setVisibility(View.INVISIBLE);
            emptyView.setVisibility(View.VISIBLE);

        }

    }
    private void setupScreenForError(String message) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

    }

    private void setupScreenForLoadingState() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

    }

    private void setupRecyclerView(){
        mRecyclerView = fragmentTaskListBinding.taskListRecyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mTaskListAdapter);

    }

    private void setupStateViews(){
        mProgressBar = fragmentTaskListBinding.progressBar;
        emptyView = fragmentTaskListBinding.emptyView;
        mFloatingActionButton = fragmentTaskListBinding.addTaskButton;
        frameLayout = fragmentTaskListBinding.frameLayout;
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            List<TaskView> adapterList = mTaskListAdapter.getTaskViewList();
            TaskView taskToBeDeleted = adapterList.get(viewHolder.getAdapterPosition());
            adapterList.remove(viewHolder.getAdapterPosition());
            deleteTask(taskToBeDeleted);
            mTaskListAdapter.notifyDataSetChanged();
        }
    };
    private void deleteTask(TaskView task){
        taskListViewModel.deleteTask(taskMapper.mapFromTaskView(task)).observe(this, new Observer<Resource<String>>() {
            @Override
            public void onChanged(Resource<String> stringResource) {
                if(stringResource.mStatus == State.SUCCESS){
                    if (mTaskListAdapter.getTaskViewList().isEmpty()){
                        mRecyclerView.setVisibility(View.INVISIBLE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                    // do something after deleting a task
                }else{
                   // do something if deleting was not successful
                }
            }
        });
    }
}
