package com.github.bubinimara.preferencemanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by davide on 20/09/16.
 * Wrapper of {@link PreferenceLoader} class
 *
 */

public class PreferenceManager<T> {
    private static final HashMap<String,PreferenceManager<?>> MANAGERS = new HashMap<>();

    private PreferenceLoader<T> preferenceLoader;
    private T t;

    public static synchronized   <T> PreferenceManager<T> getPreferenceManager(@NonNull Context context,@NonNull Class<T> tClass){
        PreferenceManager<T> instance;

        instance = (PreferenceManager<T>) MANAGERS.get(tClass.getSimpleName());
        if(instance == null){
            instance = new PreferenceManager<>(context,tClass);
            MANAGERS.put(tClass.getSimpleName(),instance);
        }

        return instance;
    }

    private PreferenceManager(Context context,Class<T> tClass) {
        preferenceLoader = new PreferenceLoader<>(context,tClass);
    }

    @Nullable
    public  T load(){
        if(t==null) {
            t = preferenceLoader.load();
        }
        return t;
    }

    public void store(@NonNull T t){
        this.t = t;
        preferenceLoader.store(this.t);
    }

    public void clear() {
        t = null;
        preferenceLoader.clear();
    }
}
