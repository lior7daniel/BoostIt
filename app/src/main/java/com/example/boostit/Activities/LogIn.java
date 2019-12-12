package com.example.boostit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.boostit.R;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findViewById(R.id.btnNewCouch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNewCouch = new Intent(LogIn.this, RegisterCouch.class);
                startActivity(intentNewCouch);
            }
        });

        findViewById(R.id.btnNewTrainee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNewTrainee = new Intent(LogIn.this, RegisterTrainee.class);
                startActivity(intentNewTrainee);
            }
        });
    }
}
