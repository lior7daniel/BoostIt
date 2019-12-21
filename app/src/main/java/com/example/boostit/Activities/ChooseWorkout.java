package com.example.boostit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boostit.Objects.ObjCoach;
import com.example.boostit.Objects.ObjWorkout;
import com.example.boostit.R;
import com.google.firebase.auth.FirebaseAuth;
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
    Button                  btnSelectCity, btnSelectCoach, btnBook;
    TextView                txtShowAddress, txtShowDate;
    Spinner                 spnSelectCities, spnSelectCoach;
    ArrayList<String>       citiesList, coachesNamesList, coachesUIDList;
    ArrayList<ObjWorkout>   workoutsList;
    ArrayAdapter<String>    citiesAdapter, coachesNamesAdapter;
    ArrayAdapter<ObjWorkout> workoutsAdapter;
    DatabaseReference       myRef;
    FirebaseAuth            myAuth;

    FirebaseDatabase        database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_workout);

        database            =   FirebaseDatabase.getInstance();
        myAuth              =   FirebaseAuth.getInstance();

        coachUID            =   "";
        cal                 =   Calendar.getInstance();
        day                 =   cal.get(Calendar.DAY_OF_MONTH);
        month               =   cal.get(Calendar.MONTH);
        year                =   cal.get(Calendar.YEAR);
        btnBook             =   findViewById(R.id.btnBook);
        btnSelectCity       =   findViewById(R.id.btnSelectCity);
        btnSelectCoach      =   findViewById(R.id.btnSelectCoach);
        txtShowAddress      =   findViewById(R.id.txtShowAddress);
        txtShowDate         =   findViewById(R.id.txtShowDate);
        myWorkoutListView   =   (ListView) findViewById(R.id.myWorkoutListView);
        spnSelectCities     =   (Spinner) findViewById(R.id.spnSelectCities);
        spnSelectCoach      =   (Spinner) findViewById(R.id.spnSelectCoaches);
        citiesList          =   new ArrayList<String>();
        coachesNamesList    =   new ArrayList<String>();
        coachesUIDList      =   new ArrayList<String>();
        workoutsList        =   new ArrayList<ObjWorkout>();
        citiesAdapter       =   new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, citiesList);
        coachesNamesAdapter =   new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, coachesNamesList);
        workoutsAdapter     =   new ArrayAdapter<ObjWorkout>(this, android.R.layout.simple_list_item_1, workoutsList);

        spnSelectCities.setAdapter(citiesAdapter);
        spnSelectCoach.setAdapter(coachesNamesAdapter);
        myWorkoutListView.setAdapter(workoutsAdapter);

        retrieveCities();

        spnSelectCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                retrieveCoaches();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spnSelectCoach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                retrieveStudioAddress();
                retrieveWorkouts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnSelectCities.performClick();
            }
        });

        btnSelectCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnSelectCoach.performClick();
            }
        });

        myWorkoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.BLUE);
                view.setSelected(true);
            }
        });

    }

    public void retrieveCities() {
        myRef = database.getReference("CITIES");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot city : dataSnapshot.getChildren()) {
                    citiesList.add(city.getKey().toString());
                }
                citiesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void retrieveCoaches(){
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
                coachesNamesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void retrieveStudioAddress(){
        coachUID = coachesUIDList.get(spnSelectCoach.getSelectedItemPosition());
        myRef = database.getReference("COACHES USERS").child(coachUID);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txtShowAddress.setText(dataSnapshot.getValue(ObjCoach.class).getStudioAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void retrieveWorkouts(){
        workoutsList.clear();
        myRef = database.getReference("WORKOUTS").child(coachUID);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot workout : dataSnapshot.getChildren()){
                    workoutsList.add(workout.getValue(ObjWorkout.class));
                }
                workoutsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
