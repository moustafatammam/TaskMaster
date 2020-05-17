package com.projects.android.presentation.resource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

public class Resource<T> {

    private State mStatus;
    private  String mMessage;
    private T mData;

    @Inject
    public Resource(State mStatus, T mData, String mMessage){
        this.mStatus = mStatus;
        this.mData = mData;
        this.mMessage = mMessage;
    }

    public <T> Resource<T> success(@NonNull T mData){
        return new Resource<>(State.SUCCESS, mData, null);
    }

    public <T> Resource<T> error(String mMessage, @Nullable T mData){
        return new Resource<>(State.ERROR, null, mMessage);
    }

    public <T> Resource<T> loading(){
        return new Resource<>(State.LOADING, null, null);
    }

}
