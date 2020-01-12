package com.example.boostit.ui.myworkouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyWorkoutsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyWorkoutsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my workouts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}