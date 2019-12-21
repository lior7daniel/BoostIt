package com.example.boostit.Objects;

import java.io.Serializable;

public class ObjWorkout implements Serializable {

    String  date;
    String  timeBegin;
    String  timeEnd;
    String  category;
    String  description;
    String  limitOfTrainees;

    public ObjWorkout(){}

    public ObjWorkout(String date, String timeBegin, String timeEnd,
                      String category, String limitOfTrainees, String description){
        this.date             =   date;
        this.timeBegin        =   timeBegin;
        this.timeEnd          =   timeEnd;
        this.category         =   category;
        this.limitOfTrainees  =   limitOfTrainees;
        this.description      =   description;
    }

    public ObjWorkout(ObjWorkout other){
        this.date             =   other.date;
        this.timeBegin        =   other.timeBegin;
        this.timeEnd          =   other.timeEnd;
        this.category         =   other.category;
        this.limitOfTrainees  =   other.limitOfTrainees;
        this.description      =   other.description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLimitOfTrainees() {
        return limitOfTrainees;
    }

    public void setLimitOfTrainees(String limitOfTrainees) {
        this.limitOfTrainees = limitOfTrainees;
    }

    public String toString(){
        String lineSep = System.lineSeparator();
        return  lineSep +
                "Date: " + date + lineSep +
                "Beginning time: " + timeBegin + lineSep +
                "Ending time: " + timeEnd + lineSep +
                "Category: " + category + lineSep +
                "Limit of trainees: " + limitOfTrainees + lineSep +
                "Description: " + description + lineSep;
    }
}
