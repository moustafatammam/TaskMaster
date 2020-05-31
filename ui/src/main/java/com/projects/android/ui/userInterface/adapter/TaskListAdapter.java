package com.projects.android.ui.userInterface.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.projects.android.ui.R;
import com.projects.android.ui.databinding.TaskItemBinding;
import com.projects.android.ui.model.TaskView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {

    private List<TaskView> taskViewList = new ArrayList<>();


    @Inject
    public TaskListAdapter() {
    }

    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TaskItemBinding taskItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.task_item, parent, false);
        return new TaskListViewHolder(taskItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {
        holder.taskItemBinding.setTaskView(taskViewList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskViewList == null ? 0 : taskViewList.size();
    }

    public class TaskListViewHolder extends RecyclerView.ViewHolder{

        TaskItemBinding taskItemBinding;

        public TaskListViewHolder(@NonNull TaskItemBinding taskItemBinding) {
            super(taskItemBinding.getRoot());
            this.taskItemBinding = taskItemBinding;
        }
    }

    public void submitList(List<TaskView> taskViewList){
        this.taskViewList = taskViewList;
        notifyDataSetChanged();
    }
}
