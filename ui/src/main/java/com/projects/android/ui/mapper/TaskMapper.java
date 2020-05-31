package com.projects.android.ui.mapper;


import android.util.Log;

import com.projects.android.presentation.model.PresentationTask;
import com.projects.android.ui.model.TaskView;

import javax.inject.Inject;

public class TaskMapper implements Mapper<TaskView, PresentationTask> {

    @Inject
    public TaskMapper() {
    }

    @Override
    public TaskView mapToTaskView(PresentationTask presentationTask) {
        return new TaskView(presentationTask.getId(), presentationTask.getTitle(), presentationTask.getPriority(), presentationTask.getDate(),
                presentationTask.getComment(), presentationTask.getLabel(), presentationTask.getStatus());
    }

    @Override
    public PresentationTask mapFromTaskView(TaskView taskView) {
        Log.e("mapper", "taskview");

        return new PresentationTask(taskView.getId(), taskView.getTitle(), taskView.getPriority(), taskView.getDate(),
                taskView.getComment(), taskView.getLabel(), taskView.getStatus());
    }
}
