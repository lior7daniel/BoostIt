package com.example.boostit.ui.myworkouts;

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

public class MyWorkoutsFragment extends Fragment {

    private MyWorkoutsViewModel myWorkoutsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myWorkoutsViewModel =
                ViewModelProviders.of(this).get(MyWorkoutsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_workouts, container, false);
        final TextView textView = root.findViewById(R.id.text_my_workouts);
        myWorkoutsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}