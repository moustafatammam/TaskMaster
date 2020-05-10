package com.projects.android.domain.interactors.base;

import com.projects.android.domain.executor.ThreadExecutor;
import com.projects.android.domain.executor.PostExecutionThread;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class ObservableAbstractInteractor<T, Params>{

    private final ThreadExecutor mThreadThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private final CompositeDisposable mCompositeDisposable;


    public ObservableAbstractInteractor(ThreadExecutor mThreadThreadExecutor, PostExecutionThread mPostExecutionThread){
        this.mThreadThreadExecutor = mThreadThreadExecutor;
        this.mPostExecutionThread = mPostExecutionThread;
        this.mCompositeDisposable = new CompositeDisposable();
    }

     public abstract Observable<T> buildInteractorObservable(Params params);


    public void execute(DisposableObserver<T> observer, Params params){
        if (observer == null){
            throw new NullPointerException();
        }
        final Observable<T> observable = this.buildInteractorObservable(params)
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
