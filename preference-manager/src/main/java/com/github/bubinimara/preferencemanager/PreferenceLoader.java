package com.github.bubinimara.preferencemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


/**
 * Created by davide on 20/09/16.
 *
 * Load and store object in shared preferences
 * The objects are stored as String in the shared preferences,
 * the object is serialized with Gson library (Add see code)
 */

public class PreferenceLoader<T> {
    private static final String PREF_FILE_PREFIX = "com.github.bubinimara.preferencemanager_";

    /**
     * Serialize and deserialize objects
     */
    private Gson gson = new Gson();

    /**
     * Access to shared preferences
     */
    private final SharedPreferences sharedPreferences;


    /**
     * The class of T
     */
    private Class<T> classOfT;


    /**
     * Create new object of this class
     *
     * @param context the application context
     * @param classOfT th class of T
     */
    public PreferenceLoader(@NonNull Context context,@NonNull Class<T> classOfT) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_PREFIX+classOfT.getSimpleName(),Context.MODE_PRIVATE);
        this.classOfT = classOfT;
    }

    /**
     * Load the object T stored into the shared preference
     * @return new instance of the object T loaded or null if no objects was stored
     */
    @Nullable
    public T load(){
        String jsonString = sharedPreferences.getString(classOfT.getSimpleName(), null);
        try {
            return gson.fromJson(jsonString,classOfT);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    /**
     * Store the object T passed as parameter into the shared preferences
     * @param object the object to store
     */
    public void store(@NonNull T object){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(classOfT.getSimpleName(),gson.toJson(object));
        edit.apply();
    }

    /**
     * Clear the object stored, next call to {@link PreferenceLoader#load()}  return null
     */
    public void clear(){
        sharedPreferences.edit().clear().apply();
    }
}
