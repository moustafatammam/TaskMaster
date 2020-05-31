package com.projects.android.ui.userInterface.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

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
                    Log.e("tasllistfragment", "onchanged");
                    handleDataState(listResource.mStatus, listResource.mData, listResource.mMessage);
                    Log.e("tasllistfragment", "after handling");
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
        Log.e("tasllistfragment", "success");
        mProgressBar.setVisibility(View.GONE);
        if(data != null && !data.isEmpty()){
            Log.e("tasllistfragment", "not empty");
            List<TaskView> taskViewList = new ArrayList<>();
            for (PresentationTask preTask : data){
                taskViewList.add(taskMapper.mapToTaskView(preTask));
            }
            mTaskListAdapter.submitList(taskViewList);
            mRecyclerView.setVisibility(View.VISIBLE);
        }else{
            Log.e("tasllistfragment", "empty");
            emptyView.setVisibility(View.VISIBLE);
        }

    }
    private void setupScreenForError(String message) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar.make(frameLayout, message, BaseTransientBottomBar.LENGTH_LONG);

    }

    private void setupScreenForLoadingState() {
        Log.e("tasllistfragment", "loading");
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

    }

    private void setupRecyclerView(){
        Log.e("tasllistfragment", "recyclerview");
        mRecyclerView = fragmentTaskListBinding.taskListRecyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTaskListAdapter);
    }

    private void setupStateViews(){
        Log.e("tasllistfragment", "stateview");
        mProgressBar = fragmentTaskListBinding.progressBar;
        emptyView = fragmentTaskListBinding.emptyView;
        mFloatingActionButton = fragmentTaskListBinding.addTaskButton;
        frameLayout = fragmentTaskListBinding.frameLayout;
    }
}
