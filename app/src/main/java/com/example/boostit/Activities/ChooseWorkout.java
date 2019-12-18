package com.example.boostit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.boostit.Objects.ObjWorkout;
import com.example.boostit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ChooseWorkout extends AppCompatActivity {

    int                     day, month, year;
    ListView                myWorkoutListView;
    Calendar                cal;
    String                  coachUID;
    Button                  btnSelectCity, btnSelectCoach, btnSelectDate, btnSelectTime;
    TextView                txtShowAddress, txtShowDate;
    Spinner                 spnSelectCities, spnSelectCoach, spnWorkoutTime;
    ArrayList<String>       citiesList, coachesNamesList, coachesUIDList, woTimeList;
    ArrayAdapter<String>    adapterFindCities, adapterFindCoach, adapterWOTime;
    DatabaseReference       myRef;
    FirebaseDatabase        database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_workout);

        day = month = year = 0;


        cal                 =   Calendar.getInstance();
        coachUID            =   "";
        myWorkoutListView   =   findViewById(R.id.myWorkoutListView);
        btnSelectCity       =   findViewById(R.id.btnSelectCity);
        btnSelectCoach      =   findViewById(R.id.btnSelectCoach);
        btnSelectDate       =   findViewById(R.id.btnSelectDate);
        btnSelectTime       =   findViewById(R.id.btnSelectTime);
        txtShowAddress      =   findViewById(R.id.txtShowAddress);
        txtShowDate         =   findViewById(R.id.txtShowDate);
        spnSelectCities     =   (Spinner) findViewById(R.id.spnSelectCities);
        spnSelectCoach      =   (Spinner) findViewById(R.id.spnSelectCoaches);
        spnWorkoutTime      =   (Spinner) findViewById(R.id.spnWorkoutTime);
        citiesList          =   new ArrayList<String>();
        coachesNamesList    =   new ArrayList<String>();
        coachesUIDList      =   new ArrayList<String>();
        woTimeList          =   new ArrayList<String>();
        adapterFindCities   =   new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, citiesList);
        adapterFindCoach    =   new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, coachesNamesList);
        adapterWOTime       =   new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, woTimeList);

        spnSelectCities.setAdapter(adapterFindCities);
        spnSelectCoach.setAdapter(adapterFindCoach);
        spnWorkoutTime.setAdapter(adapterWOTime);

        database            =   FirebaseDatabase.getInstance();

        retrieveCitiesToSpinnerFindCity();

        spnSelectCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = spnSelectCities.getSelectedItem().toString();
                coachesNamesList.clear();
                coachesUIDList.clear();
                myRef = database.getReference("CITIES").child(selectedCity);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot coach : dataSnapshot.getChildren()){
                            coachesNamesList.add(coach.getValue().toString());
                            coachesUIDList.add(coach.getKey());
                        }
                        adapterFindCoach.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnSelectCities.performClick();
            }
        });

        spnSelectCoach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coachUID = coachesUIDList.get(spnSelectCoach.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSelectCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnSelectCoach.performClick();
            }
        });

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog =   new DatePickerDialog(
                        ChooseWorkout.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                setDay(dayOfMonth);
                                setMonth(month);
                                setYear(year);
                                retrieveTimeToSpinnerWOTimeByCoachAndDate();
                                txtShowDate.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        },
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        spnWorkoutTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myRef = database.getReference("WORKOUTS").child(coachUID).child("Year: " + year).child("Month: " + month).child("Day: " + day).child(spnWorkoutTime.getSelectedItem().toString());
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> wo = new ArrayList<>();
                        wo.add("Date : " + dataSnapshot.getValue(ObjWorkout.class).getWoDate());
                        wo.add("Beginning time : " + dataSnapshot.getValue(ObjWorkout.class).getWoTimeBegin());
                        wo.add("Ending time : " + dataSnapshot.getValue(ObjWorkout.class).getWoTimeEnd());
                        wo.add("Category : " + dataSnapshot.getValue(ObjWorkout.class).getWoCategory());
                        wo.add("Limit : " + dataSnapshot.getValue(ObjWorkout.class).getLimitOfTrainees());
                        wo.add("Description : " + dataSnapshot.getValue(ObjWorkout.class).getWoDescription());
                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, wo);
                        myWorkoutListView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void retrieveCitiesToSpinnerFindCity(){
        myRef = database.getReference("CITIES");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot city : dataSnapshot.getChildren()){
                    citiesList.add(city.getKey().toString());
                }
                adapterFindCities.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void retrieveTimeToSpinnerWOTimeByCoachAndDate(){
        woTimeList.clear();
        myRef = database.getReference("WORKOUTS").child(coachUID).child("Year: " + String.valueOf(year)).child("Month: " + String.valueOf(month)).child("Day: " + String.valueOf(day));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot time : dataSnapshot.getChildren()){
                    woTimeList.add(time.getKey().toString());
                }
                adapterWOTime.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
