package com.projects.android.ui.userInterface.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.projects.android.domain.model.Task;
import com.projects.android.presentation.ViewModelFactory;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;
import com.projects.android.presentation.viewModel.AddTaskViewModel;
import com.projects.android.ui.R;
import com.projects.android.ui.databinding.FragmentAddTaskBinding;
import com.projects.android.ui.mapper.TaskMapper;
import com.projects.android.ui.model.TaskView;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;


public class AddTaskFragment extends Fragment {

    private FragmentAddTaskBinding fragmentAddTaskBinding;
    private EditText titleEditText;
    private EditText labelEditText;
    private EditText commentEditText;

    private Button addTaskButton;
    private Button cancelButton;

    private RadioButton priorityHighButton;
    private RadioButton priorityMediumButton;
    private RadioButton priorityLowButton;

    private Date currentDate;


    private AddTaskViewModel addTaskViewModel;
    private ViewModelFactory viewModelFactory;
    private TaskMapper taskMapper;

    @Inject
    public AddTaskFragment(ViewModelFactory viewModelFactory, TaskMapper taskMapper) {
        this.viewModelFactory = viewModelFactory;
        this.taskMapper = taskMapper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAddTaskBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false);
        return fragmentAddTaskBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupTaskDetails();
        addTaskViewModel = new ViewModelProvider(this, viewModelFactory).get(AddTaskViewModel.class);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupTaskData();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(cancelButton).navigate(R.id.action_addTaskFragment_to_taskListFragment);
            }
        });
    }

    private void setupTaskData(){

        titleEditText.setError(null);
        labelEditText.setError(null);
        commentEditText.setError(null);
        boolean cancel = false;
        View focusView = null;

        String titleText = titleEditText.getText().toString();

        if(titleText.isEmpty()){
            titleEditText.setError("title can not be empty");
            focusView = titleEditText;
            cancel = true;
        }
        String labelText = labelEditText.getText().toString();
        if(labelText.isEmpty()){
            labelEditText.setError("label can not be empty");
            focusView = labelEditText;
            cancel = true;
        }
        String descriptionText = commentEditText.getText().toString();
        if(descriptionText.isEmpty()){
            commentEditText.setError("description can not be empty");
            focusView = commentEditText;
            cancel = true;
        }
        currentDate = new Date();

        int priority;
        if (priorityHighButton.isChecked()){
            priority = 1;
        }else if(priorityLowButton.isChecked()){
            priority = 2;
        }else{
            priority = 3;
        }
        if (cancel){
            focusView.requestFocus();

        }else{
           TaskView taskView = new TaskView(titleText, priority, currentDate, descriptionText, labelText, 0);
           addTask(taskView);
           Navigation.findNavController(addTaskButton).navigate(R.id.action_addTaskFragment_to_taskListFragment);
        }
    }

    public void addTask(TaskView task){

        addTaskViewModel.addTask(taskMapper.mapFromTaskView(task)).observe(this, new Observer<Resource<String>>() {
            @Override
            public void onChanged(Resource<String> stringResource) {
                if(stringResource.mStatus == State.SUCCESS){
                    Snackbar snackbar = Snackbar.make(fragmentAddTaskBinding.constraintLayout, stringResource.mMessage, BaseTransientBottomBar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    Toast toast = Toast.makeText(getContext(), stringResource.mMessage, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private void setupTaskDetails(){
        titleEditText = fragmentAddTaskBinding.editTextTitleContent;
        labelEditText = fragmentAddTaskBinding.editTextLabelContent;
        commentEditText = fragmentAddTaskBinding.editTextCommentContent;

        addTaskButton = fragmentAddTaskBinding.addTaskButton;
        cancelButton = fragmentAddTaskBinding.cancelButton;

        priorityHighButton = fragmentAddTaskBinding.highButton;
        priorityMediumButton = fragmentAddTaskBinding.mediumButton;
        priorityLowButton = fragmentAddTaskBinding.lowButton;
    }
}
