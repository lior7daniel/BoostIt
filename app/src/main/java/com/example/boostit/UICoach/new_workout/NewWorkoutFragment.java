package com.example.boostit.UICoach.new_workout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.boostit.CoachHome;
import com.example.boostit.Objects.ObjWorkout;
import com.example.boostit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class NewWorkoutFragment extends Fragment {

    int                     minute, hour, day, month, year;

    Calendar cal;
    Spinner spnCategory;
    Button btnDate, btnBeginTime, btnEndTime, btnCreateWorkout, btnCategory, btnTraineesLimit;
    TextView                txtDate, txtBeginTime, txtEndTime;
    EditText txtDescription, txtTraineesLimit;
    ArrayList<String> categoryList;
    ArrayAdapter<String> adapterCategory;

    FirebaseAuth myAuth;
    FirebaseDatabase        database;
    DatabaseReference myRef;

    private NewWorkoutViewModel newWorkoutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_coach_new_workout, container, false);

        minute = hour = day = month = year = 0;

        cal                 =   Calendar.getInstance();
        spnCategory         =   root.findViewById(R.id.spnCategory);
        btnDate             =   root.findViewById(R.id.btnDate);
        btnBeginTime        =   root.findViewById(R.id.btnBeginTime);
        btnEndTime          =   root.findViewById(R.id.btnEndTime);
        btnCategory         =   root.findViewById(R.id.btnCategory);
        btnTraineesLimit    =   root.findViewById(R.id.btnTraineesLimit);
        txtDate             =   root.findViewById(R.id.txtShowDate);
        txtBeginTime        =   root.findViewById(R.id.txtBeginTime);
        txtEndTime          =   root.findViewById(R.id.txtEndingTime);
        txtTraineesLimit    =   root.findViewById(R.id.txtTraineesLimit);
        txtDescription      =   root.findViewById(R.id.txtDescription);
        btnCreateWorkout    =   root.findViewById(R.id.btnCreateWorkout);

        categoryList        =   new ArrayList<String>();
        fillCategoryList();
        adapterCategory     =   new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, categoryList);
        spnCategory.setAdapter(adapterCategory);

        myAuth              =   FirebaseAuth.getInstance();

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog =   new DatePickerDialog(
                        getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                setDay(dayOfMonth);
                                setMonth(++month);
                                setYear(year);
                                txtDate.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        },
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        btnBeginTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                setHour(hourOfDay);
                                setMinute(minute);
                                txtBeginTime.setText(hourOfDay + ":" + minute);
                            }
                        },
                        cal.get(Calendar.HOUR),
                        cal.get(Calendar.MINUTE),
                        false);
                timePickerDialog.show();
            }
        });

        // what is the difrent between  false); and  android.text.format.DateFormat.is24HourFormat(NewWorkout.this));

        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                txtEndTime.setText(hourOfDay + ":" + minute);
                            }
                        },
                        cal.get(Calendar.HOUR),
                        cal.get(Calendar.MINUTE),
                        android.text.format.DateFormat.is24HourFormat(getContext()));
                timePickerDialog.show();
            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnCategory.performClick();
            }
        });

        btnCreateWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  strDate         =   txtDate.getText().toString();
                String  strBegTime      =   txtBeginTime.getText().toString();
                String  strEndTime      =   txtEndTime.getText().toString();
                String  strCategory     =   spnCategory.getSelectedItem().toString();
                String  strLimit        =   txtTraineesLimit.getText().toString();
                String  strDescription  =   txtDescription.getText().toString();

                if(strDate.isEmpty()){
                    txtDate.setError("Date is required");
                    return;
                }
                if(strBegTime.isEmpty()){
                    txtBeginTime.setError("Beginning time is required");
                    return;
                }
                if(strEndTime.isEmpty()){
                    txtEndTime.setError("Ending time is required");
                    return;
                }
                if(strLimit.isEmpty()){
                    txtTraineesLimit.setError("Limit number of trainees is required");
                    return;
                }
                if(strDescription.isEmpty()){
                    txtDescription.setError("Description is required");
                    return;
                }
                createWorkout(strDate, strBegTime, strEndTime, strCategory, strLimit, strDescription);
            }
        });

        return root;
    }

    public void createWorkout(String strDate, String strBegTime, String strEndTime, String strCategory, String strLimit, String strDescription){
        ObjWorkout workout = new ObjWorkout(strDate, strBegTime, strEndTime, strCategory, strLimit, strDescription);
        workout.setYear(String.valueOf(year));
        workout.setMonth(String.valueOf(month));
        workout.setDay(String.valueOf(month));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("WORKOUTS");
        myRef.child(myAuth.getCurrentUser().getUid()).child("Y : " + String.valueOf(year) + ", M : " + String.valueOf(month) + ", D : " + String.valueOf(day) +
                ", Time : " + strBegTime).setValue(workout);
        Toast.makeText(getContext(), workout.toString(), Toast.LENGTH_LONG).show();



        startActivity(new Intent(getContext(), CoachHome.class));
    }

    public void fillCategoryList(){
        categoryList.add("פיתוח ועיצוב הגוף");
        categoryList.add("TRX");
        categoryList.add("סיבולת לב ריאה");
        categoryList.add("גמישות");
        categoryList.add("כח שריר");
        categoryList.add("סיבולת שריר");
        categoryList.add("זריזות");
        categoryList.add("קואורדינציה");
        categoryList.add("מהירות תגובה");
        categoryList.add("מהירות");
        categoryList.add("שיווי משקל");
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

}