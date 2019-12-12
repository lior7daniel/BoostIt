package com.example.boostit.Objects;

public class ObjCouch {

    String email, password, fullName, phoneNumber,
            studioName, studioCity, studioAdress;

    public ObjCouch(String email, String password, String fullName, String phoneNumber,
                    String studioName, String studioCity, String studioAdress){
        this.email          =   email;
        this.password       =   password;
        this.fullName       =   fullName;
        this.phoneNumber    =   phoneNumber;
        this.studioName     =   studioName;
        this.studioCity     =   studioCity;
        studioAdress        =   studioAdress;
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

    public String getStudioAdress() {
        return studioAdress;
    }

    public void setStudioAdress(String studioAdress) {
        this.studioAdress = studioAdress;
    }
}
