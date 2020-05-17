package com.projects.android.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private Map<Class<ViewModel> , Provider<ViewModel>> viewModelMap;

    @Inject
    public ViewModelFactory(Map<Class<ViewModel>, Provider<ViewModel>> viewModelMap) {
        this.viewModelMap = viewModelMap;
    }
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        @Nullable
        Provider<ViewModel> creator = viewModelMap.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<ViewModel> , Provider<ViewModel>> entry : viewModelMap.entrySet()){
                if (modelClass.isAssignableFrom(entry.getKey())){
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if(creator == null){
            throw new IllegalArgumentException("unknown model class" + modelClass);
        }

        try{
            return (T)creator.get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
