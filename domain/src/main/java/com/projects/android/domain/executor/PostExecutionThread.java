package com.projects.android.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {

    Scheduler getScheduler();
}
