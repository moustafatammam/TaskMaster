package com.projects.android.data.executor;

import com.projects.android.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class JobExecutor implements ThreadExecutor {

    private final int INITIAL_POOL_SIZE = 3;
    private final int MAX_POOL_SIZE = 5;

    private final int KEEP_ALIVE_TIME = 10;

    private final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;


    private LinkedBlockingQueue<Runnable> workQueue;
    private ThreadPoolExecutor threadPoolExecutor;
    private ThreadFactory threadFactory;

    @Inject
    public JobExecutor() {
    }

    {
        workQueue  = new LinkedBlockingQueue<>();
        threadFactory = new JObThreadFactory();
        threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, this.workQueue, this.threadFactory);
    }

    @Override
    public void execute(Runnable runnable) {
        if(runnable == null){
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        this.threadPoolExecutor.execute(runnable);
    }

    private class JObThreadFactory implements ThreadFactory{

        private final String THREAD_NAME = "android_";
        private int counter = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, THREAD_NAME + counter++);
        }
    }
}
