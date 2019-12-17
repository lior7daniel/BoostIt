package com.example.boostit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boostit.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeCoach extends AppCompatActivity {

    Button btnLogOut;
//    FirebaseAuth                    fAuth;
//    FirebaseAuth.AuthStateListener  mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_coach);

        btnLogOut = findViewById(R.id.btnLogOutCoach);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LogIn.class));
            }
        });
    }
}
