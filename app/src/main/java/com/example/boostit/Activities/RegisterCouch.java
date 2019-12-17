package com.example.boostit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.boostit.Objects.ObjCouch;
import com.example.boostit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterCouch extends AppCompatActivity {

    EditText            txtEmail, txtPassword, txtPassword2, txtFullName, txtPhoneNumber,
                        txtStudioName, txtStudioCity, txtStudioAddress;
    Button              btnLetsGo;
    FirebaseAuth        myAuth;
    DatabaseReference   myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_couch);

        txtEmail            =   findViewById(R.id.txtEmail);
        txtPassword         =   findViewById(R.id.txtPassword);
        txtPassword2        =   findViewById(R.id.txtPassword2);
        txtFullName         =   findViewById(R.id.txtFullName);
        txtPhoneNumber      =   findViewById(R.id.txtPhoneNumber);
        txtStudioName       =   findViewById(R.id.txtStudioName);
        txtStudioCity       =   findViewById(R.id.txtStudioCity);
        txtStudioAddress    =   findViewById(R.id.txtStudioAddress);
        btnLetsGo           =   findViewById(R.id.btnLetsGo);

        myAuth               =   FirebaseAuth.getInstance();

        btnLetsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String    strEmail            =   txtEmail.getText().toString();
                final String    strPassword         =   txtPassword.getText().toString();
                final String    strPassword2        =   txtPassword2.getText().toString();
                final String    strFullName         =   txtFullName.getText().toString();
                final String    strPhoneNumber      =   txtPhoneNumber.getText().toString();
                final String    strStudioName       =   txtStudioName.getText().toString();
                final String    strStudioCity       =   txtStudioCity.getText().toString();
                final String    strStudioAddress    =   txtStudioAddress.getText().toString();

                if(TextUtils.isEmpty(strEmail)){
                    txtEmail.setError("email is required");
                    return;
                }
                if(strPassword.length() < 6){
                    txtPassword.setError("password must be >= 6 characters");
                    return;
                }

                if(!strPassword.equals(strPassword2)){
                    txtPassword2.setError("password and confirm Password has to be equal");
                    return;
                }
                if(TextUtils.isEmpty(strFullName)){
                    txtFullName.setError("full name is required");
                    return;
                }
                if(TextUtils.isEmpty(strPhoneNumber)){
                    txtPhoneNumber.setError("phone number is required");
                    return;
                }
                if(TextUtils.isEmpty(strStudioName)){
                    txtPhoneNumber.setError("studio name is required");
                    return;
                }
                if(TextUtils.isEmpty(strStudioCity)){
                    txtPhoneNumber.setError("studio city is required");
                    return;
                }
                if(TextUtils.isEmpty(strStudioAddress)){
                    txtPhoneNumber.setError("studio address is required");
                    return;
                }


                CreateUserAccount(strEmail, strPassword, strFullName, strPhoneNumber, strStudioName, strStudioCity, strStudioAddress);

            }
        });
    }

    private void CreateUserAccount(final String strEmail,final String strPassword,final String strFullName,final String strPhoneNumber,
                                   final String strStudioName, final String strStudioCity, final String strStudioAddress) {
        myAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Coaches account created!", Toast.LENGTH_LONG).show();
                    ObjCouch couch = new ObjCouch(strEmail, strPassword, strFullName, strPhoneNumber, strStudioName, strStudioCity, strStudioAddress);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("COACHES USERS");
                    myRef.child(myAuth.getCurrentUser().getUid()).setValue(couch);
                    startActivity(new Intent(RegisterCouch.this, LogIn.class));
                    return;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Account creation failed : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
