package com.example.boostit.UICoach.workouts_history;

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

public class WorkoutsHistoryFragment extends Fragment {

    private WorkoutHistoryViewModel workoutHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        workoutHistoryViewModel =
                ViewModelProviders.of(this).get(WorkoutHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_coach_workouts_history, container, false);
        final TextView textView = root.findViewById(R.id.text_workouts_history);
        workoutHistoryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}