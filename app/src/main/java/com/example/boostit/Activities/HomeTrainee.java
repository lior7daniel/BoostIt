package com.example.boostit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.boostit.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeTrainee extends AppCompatActivity {

//    FirebaseAuth                    fAuth;
//    FirebaseAuth.AuthStateListener  mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_trainee);

        findViewById(R.id.btnTraineeAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TraineeAccount.class));
            }
        });

        findViewById(R.id.btnChooseWorkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChooseWorkout.class));
            }
        });

    }
}
