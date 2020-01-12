package com.example.boostit.ui.workouts_history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkoutHistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WorkoutHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is workout history fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}