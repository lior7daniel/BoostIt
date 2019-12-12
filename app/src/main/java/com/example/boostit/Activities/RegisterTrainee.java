package com.example.boostit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.boostit.R;

public class RegisterTrainee extends AppCompatActivity {

    EditText txtEmail, txtPassword, txtPassword2, txtFullName, txtPhoneNumber;
    Button btnLetsGo;
//    FirebaseAuth    mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_trainee);

        txtEmail        =   findViewById(R.id.txtEmail);
        txtPassword     =   findViewById(R.id.txtPassword);
        txtFullName     =   findViewById(R.id.txtFullName);
        txtPhoneNumber  =   findViewById(R.id.txtPhoneNumber);
        btnLetsGo       =   findViewById(R.id.btnLetsGo);

//        mAuth           =   FirebaseAuth.getInstance();

        btnLetsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String    strEmail        =   txtEmail.getText().toString();
                final String    strPassword     =   txtPassword.getText().toString();
                final String    strPassword2     =   txtPassword2.getText().toString();
                final String    strFullName     =   txtFullName.getText().toString();
                final String    strPhoneNumber  =   txtPhoneNumber.getText().toString();

                if(TextUtils.isEmpty(strEmail)){
                    txtEmail.setError("email is required");
                    return;
                }
                if(strPassword.length()<6){
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
                else {
                    CreateUserAccount(strEmail, strPassword, strFullName, strPhoneNumber);
                }
            }
        });
    }

    private void CreateUserAccount(final String strEmail,final String strPassword,final String strFullName,final String strPhoneNumber) {
//        mAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), "Trainee account created!", Toast.LENGTH_LONG).show();
//                    ObjTrainee trainee = new ObjTrainee(strEmail, strPassword, strFullName, strPhoneNumber);
//                    finish();
//                    return;
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Account creation failed : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
}
