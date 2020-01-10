package com.example.boostit.Objects;

import java.io.Serializable;

public class ObjTrainee implements Serializable{

    String tUID, email, password, fullName, phoneNumber;

    public ObjTrainee(){}

    public ObjTrainee(String tUID, String email, String password, String fullName, String phoneNumber){
        this.tUID           =   tUID;
        this.email          =   email;
        this.password       =   password;
        this.fullName       =   fullName;
        this.phoneNumber    =   phoneNumber;
    }

    public String gettUID() {
        return tUID;
    }

    public void settUID(String tUID) {
        this.tUID = tUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
