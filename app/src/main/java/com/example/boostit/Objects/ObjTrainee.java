package com.example.boostit.Objects;

public class ObjTrainee {

    String email, password, fullName, phoneNumber;

    public ObjTrainee(String email, String password, String fullName, String phoneNumber){
        this.email          =   email;
        this.password       =   password;
        this.fullName       =   fullName;
        this.phoneNumber    =   phoneNumber;
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
