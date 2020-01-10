package com.example.boostit.Objects;

import java.io.Serializable;

public class ObjCoach implements Serializable {

    String  cUID, email, password, fullName, phoneNumber,
            studioName, studioCity, studioAddress;

    public ObjCoach(){}

    public ObjCoach(String cUID, String email, String password, String fullName, String phoneNumber,
                    String studioName, String studioCity, String studioAddress){

        this.cUID           =   cUID;
        this.email          =   email;
        this.password       =   password;
        this.fullName       =   fullName;
        this.phoneNumber    =   phoneNumber;
        this.studioName     =   studioName;
        this.studioCity     =   studioCity;
        this.studioAddress  =   studioAddress;
    }

    public String getcUID() {
        return cUID;
    }

    public void setcUID(String cUID) {
        this.cUID = cUID;
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

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getStudioCity() {
        return studioCity;
    }

    public void setStudioCity(String studioCity) {
        this.studioCity = studioCity;
    }

    public String getStudioAddress() {
        return studioAddress;
    }

    public void setStudioAddress(String studioAddress) {
        this.studioAddress = studioAddress;
    }
}
