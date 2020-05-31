package com.projects.android.ui.userInterface;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class InjectingFragmentFactory extends FragmentFactory {


    private Map<Class<? extends Fragment>, Provider<Fragment>> fragmentMap;

    @Inject
    public InjectingFragmentFactory(Map<Class<? extends Fragment>, Provider<Fragment>> fragmentMap) {
        this.fragmentMap = fragmentMap;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Provider<? extends Fragment> creator = fragmentMap.get(loadFragmentClass(classLoader, className));
        if (creator == null) {
            for (Map.Entry<Class<? extends Fragment> , Provider<Fragment>> entry : fragmentMap.entrySet()){
                if (loadFragmentClass(classLoader, className).isAssignableFrom(entry.getKey())){
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if(creator == null){
            return super.instantiate(classLoader, className);
        }
        try{
            return creator.get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
