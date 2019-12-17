package com.example.boostit.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boostit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  LogIn extends AppCompatActivity {

    String                          uId;
    FirebaseAuth                    myAuth;
    EditText                        txtEmail, txtPassword;
    FirebaseAuth.AuthStateListener  myAuthStateListener;
    DatabaseReference               myRef;
    FirebaseDatabase                database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        txtEmail                    =   findViewById(R.id.txtEmail);
        txtPassword                 =   findViewById(R.id.txtPassword);

        myAuth                      =   FirebaseAuth.getInstance();
        database                    =   FirebaseDatabase.getInstance();

        myAuthStateListener         =   new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser myFireBaseUser = myAuth.getCurrentUser();
                if( myFireBaseUser != null ){
                    Toast.makeText(getApplicationContext(),"You are logged in",Toast.LENGTH_LONG);
                    uId =   myAuth.getCurrentUser().getUid();
                    myRef = database.getReference("COACHES USERS");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(uId)){
                                Toast.makeText(getApplicationContext(),"YOU ARE COACH", Toast.LENGTH_LONG);
                                startActivity(new Intent(getApplicationContext(), HomeCouch.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"YOU ARE TRAINEE", Toast.LENGTH_LONG);
                                startActivity(new Intent(getApplicationContext(), HomeTrainee.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });                }
                else {
                    Toast.makeText(getApplicationContext(),"Please log in",Toast.LENGTH_LONG);
                }

            }
        };


        findViewById(R.id.btnLogIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String strEmail       =   txtEmail.getText().toString();
                final String strPassword    =   txtPassword.getText().toString();

                if(strEmail.isEmpty()){
                    txtEmail.setError("Email is required");
                    return;
                }

                else if(strPassword.length() < 6){
                    txtPassword.setError("password must be >= 6 characters");
                    return;
                }
                else{
                    myAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"You are logged in", Toast.LENGTH_LONG);
                                uId =   myAuth.getCurrentUser().getUid();
                                myRef = database.getReference("COACHES USERS");
                                myRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.hasChild(uId)){
                                            Toast.makeText(getApplicationContext(),"YOU ARE COACH", Toast.LENGTH_LONG);
                                            startActivity(new Intent(getApplicationContext(), HomeCouch.class));
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"YOU ARE TRAINEE", Toast.LENGTH_LONG);

                                            startActivity(new Intent(getApplicationContext(), HomeTrainee.class));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    });
                }
            }
        });


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

    @Override
    protected void onStart() {
        super.onStart();
        myAuth.addAuthStateListener(myAuthStateListener);
    }
}




//uId     =   myAuth.getCurrentUser().getUid();
//        myRef   =   database.getReference("TRAINEES USERS");
//
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        if (dataSnapshot.hasChild("uId")){
//        Toast.makeText(getApplicationContext(),"trainees users",Toast.LENGTH_LONG);
//        }
//        else{
//        Toast.makeText(getApplicationContext(),"coaches users",Toast.LENGTH_LONG);
//        }
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//        });
