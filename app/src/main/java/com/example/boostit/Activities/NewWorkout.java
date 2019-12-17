package com.example.boostit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.WorkSource;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boostit.Objects.ObjWorkout;
import com.example.boostit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewWorkout extends AppCompatActivity {

    Button      btnCreateWorkout;
    EditText    txtDate, txtBegTime, txtEndTime, txtCategory, txtLimit, txtDescription;
    FirebaseAuth myAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    static int workoutNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout);

        btnCreateWorkout    =   findViewById(R.id.btnCreateWorkout);
        txtDate             =   findViewById(R.id.txtDate);
        txtBegTime          =   findViewById(R.id.txtBeginningTime);
        txtEndTime          =   findViewById(R.id.txtEndingTime);
        txtCategory         =   findViewById(R.id.txtCategory);
        txtLimit            =   findViewById(R.id.txtLimit);
        txtDescription      =   findViewById(R.id.txtDescription);

        myAuth               =   FirebaseAuth.getInstance();

        btnCreateWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  strDate         =   txtDate.getText().toString();
                String  strBegTime      =   txtBegTime.getText().toString();
                String  strEndTime      =   txtEndTime.getText().toString();
                String  strCategory     =   txtCategory.getText().toString();
                String  strLimit        =   txtLimit.getText().toString();
                String  strDescription  =   txtDescription.getText().toString();

                if(strDate.isEmpty()){
                    txtDate.setError("Date is required");
                    return;
                }
                if(strBegTime.isEmpty()){
                    txtBegTime.setError("Beginning time is required");
                    return;
                }
                if(strEndTime.isEmpty()){
                    txtEndTime.setError("Ending time is required");
                    return;
                }
                if(strCategory.isEmpty()){
                    txtCategory.setError("Category is required");
                    return;
                }
                if(strLimit.isEmpty()){
                    txtLimit.setError("Limit number of trainees is required");
                    return;
                }
                if(strDescription.isEmpty()){
                    txtDescription.setError("Description is required");
                    return;
                }



                createWorkout(strDate, strBegTime, strEndTime, strCategory, strLimit, strDescription);

            }
        });

    }

    public void createWorkout(String strDate, String strBegTime, String strEndTime, String strCategory, String strLimit, String strDescription){
        ObjWorkout workout = new ObjWorkout(strDate, strBegTime, strEndTime, strCategory, strLimit, strDescription);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("WORKOUTS");
        myRef.child(myAuth.getCurrentUser().getUid()).child(String.valueOf(workoutNumber++)).setValue(workout);
        startActivity(new Intent(getApplicationContext(), HomeCoach.class));
        Toast.makeText(getApplicationContext(), "Workout created!", Toast.LENGTH_LONG).show();
    }


}
