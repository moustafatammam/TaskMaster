package com.projects.android.domain.repository;

import com.projects.android.domain.model.Task;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface TaskRepository {

    Completable insert(Task task);

    Completable delete(Task task);

    Completable update(Task task);

    Observable<Task> getTaskById(long id);

    Observable<List<Task>> getAllTasks();

}
