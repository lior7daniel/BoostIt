package com.example.boostit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.example.boostit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChooseWorkout extends AppCompatActivity {

    CalendarView            calendarView;
    Spinner                 spnFindCities, spnFindCoach;
    ArrayList<String>       citiesList, coachesList;
    ArrayAdapter<String>    adapterFindCities, adapterFindCoach;
    DatabaseReference       myRef;
    FirebaseDatabase        database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_workout);

        spnFindCities       =   (Spinner) findViewById(R.id.spnFindCities);
        spnFindCoach        =   (Spinner) findViewById(R.id.spnFindCoaches);
        citiesList          =   new ArrayList<String>();
        coachesList         =   new ArrayList<String>();
        adapterFindCities   =   new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, citiesList);
        adapterFindCoach    =   new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, coachesList);

        spnFindCities.setAdapter(adapterFindCities);
        spnFindCoach.setAdapter(adapterFindCoach);

        database            =   FirebaseDatabase.getInstance();

        retrieveCities();

        spnFindCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = spnFindCities.getSelectedItem().toString();
                coachesList.clear();
                myRef = database.getReference("CITIES").child(selectedCity);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot coach : dataSnapshot.getChildren()){
                            coachesList.add(coach.getValue().toString());
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


    }

    public void retrieveCities(){

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








}
