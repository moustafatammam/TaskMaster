package com.projects.android.domain.threading;

import com.projects.android.domain.executor.PostExecutionThread;

import io.reactivex.Scheduler;

public class TestPostExecutionThread implements PostExecutionThread {

    @Override
    public Scheduler getScheduler() {
        return null;
    }
}
