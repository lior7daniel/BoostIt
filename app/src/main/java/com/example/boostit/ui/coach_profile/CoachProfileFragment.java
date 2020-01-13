package com.example.boostit.ui.coach_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.boostit.Activities.LogIn;
import com.example.boostit.R;
import com.google.firebase.auth.FirebaseAuth;

public class CoachProfileFragment extends Fragment {

    Button logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_coach_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_coach_profile);

        logout      =   root.findViewById(R.id.btn_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LogIn.class));
            }
        });
        return root;
    }

}