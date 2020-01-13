package com.example.boostit.ui.coach_workout_history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.boostit.R;

public class CoachWorkoutHistoryFragment extends Fragment {

    private CoachWorkoutHistoryViewModel coachWorkoutHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        coachWorkoutHistoryViewModel =
                ViewModelProviders.of(this).get(CoachWorkoutHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_coach_workout_history, container, false);
        final TextView textView = root.findViewById(R.id.text_coach_workout_history);
        coachWorkoutHistoryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}