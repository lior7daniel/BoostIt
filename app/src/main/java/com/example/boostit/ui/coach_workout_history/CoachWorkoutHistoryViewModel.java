package com.example.boostit.ui.coach_workout_history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CoachWorkoutHistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CoachWorkoutHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}