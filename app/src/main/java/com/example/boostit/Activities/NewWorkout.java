package com.example.boostit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.WorkSource;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.boostit.Objects.ObjWorkout;
import com.example.boostit.R;

public class NewWorkout extends AppCompatActivity {

    Button      btnCreateWorkout;
    EditText    txtDate, txtBegTime, txtEndTime, txtCategory, txtLimit, txtDescription;



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

                ObjWorkout workout = new ObjWorkout(strDate, strBegTime, strEndTime, strCategory, strLimit, strDescription);



            }
        });

    }
}
