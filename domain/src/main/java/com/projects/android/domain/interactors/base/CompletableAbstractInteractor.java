package com.projects.android.domain.interactors.base;

import com.projects.android.domain.executor.PostExecutionThread;
import com.projects.android.domain.executor.ThreadExecutor;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class CompletableAbstractInteractor<Params> {

    private final ThreadExecutor mThreadThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private final CompositeDisposable mCompositeDisposable;

    public CompletableAbstractInteractor(ThreadExecutor mThreadThreadExecutor, PostExecutionThread mPostExecutionThread){
        this.mThreadThreadExecutor = mThreadThreadExecutor;
        this.mPostExecutionThread = mPostExecutionThread;
        this.mCompositeDisposable = new CompositeDisposable();
    }
    public abstract Completable buildInteractorCompletable(Params params);

    public void execute(DisposableCompletableObserver observer, Params params){
        if (observer == null){
            throw new NullPointerException();
        }
        final Completable observable = this.buildInteractorCompletable(params)
                .subscribeOn(Schedulers.from(mThreadThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose(){
        if(!mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
    }

    private void addDisposable(Disposable disposable){
        if(disposable == null){
            throw new NullPointerException();
        }else if (mCompositeDisposable == null){
            throw new NullPointerException();
        }
        mCompositeDisposable.add(disposable);

    }

}
