package com.example.boostit.ui.coach_my_workouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CoachMyWorkoutsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CoachMyWorkoutsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my workouts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}