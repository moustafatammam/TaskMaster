package com.projects.android.presentation.mapper;

import android.util.Log;

import com.projects.android.domain.model.Task;
import com.projects.android.presentation.model.PresentationTask;

import javax.inject.Inject;

public class TaskMapper implements Mapper<Task, PresentationTask> {

    @Inject
    public TaskMapper() {}

    @Override
    public PresentationTask mapToPreModel(Task task) {
        return new PresentationTask(task.getId(), task.getTitle(), task.getPriority(), task.getDate(),
                task.getComment(), task.getLabel(), task.getStatus());
    }

    @Override
    public Task mapFromPreModel(PresentationTask presentationTask) {
        Log.e("mapper pres", "pres");

        return new Task(presentationTask.getId(), presentationTask.getTitle(), presentationTask.getPriority(), presentationTask.getDate(),
                presentationTask.getComment(), presentationTask.getLabel(), presentationTask.getStatus());
    }
}
