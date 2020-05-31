package com.projects.android.ui.userInterface.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.android.presentation.ViewModelFactory;
import com.projects.android.presentation.viewModel.TaskListViewModel;
import com.projects.android.ui.R;
import com.projects.android.ui.databinding.FragmentTaskDetailsBinding;
import com.projects.android.ui.mapper.TaskMapper;


public class TaskDetailsFragment extends Fragment {

    FragmentTaskDetailsBinding fragmentTaskDetailsBinding;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTaskDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_details, container, false);
        return fragmentTaskDetailsBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
