package com.example.boostit.ui.new_workout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewWorkoutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewWorkoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is new workout fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}