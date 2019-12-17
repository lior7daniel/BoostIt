package com.example.boostit.Objects;

import java.io.Serializable;

public class ObjWorkout implements Serializable {

    String amir = "";
    String  woName;
    String  woCategory;
    String  woDate;
    String  woTimeBegin;
    String  woTimeEnd;
    String  woDescription;
    int     limitOfTrainees;

    public ObjWorkout(){}

    public ObjWorkout(String woName, String woCategory, String woDate, String woTimeBegin,
                      String woTimeEnd, String woDescription, int limitOfTrainees){
        this.woName             =   woName;
        this.woCategory         =   woCategory;
        this.woDate             =   woDate;
        this.woTimeBegin        =   woTimeBegin;
        this.woTimeEnd          =   woTimeEnd;
        this.woDescription      =   woDescription;
        this.limitOfTrainees    =   limitOfTrainees;
    }

    public String getWoName() {
        return woName;
    }

    public void setWoName(String woName) {
        this.woName = woName;
    }

    public String getWoCategory() {
        return woCategory;
    }

    public void setWoCategory(String woCategory) {
        this.woCategory = woCategory;
    }

    public String getWoDate() {
        return woDate;
    }

    public void setWoDate(String woDate) {
        this.woDate = woDate;
    }

    public String getWoTimeBegin() {
        return woTimeBegin;
    }

    public void setWoTimeBegin(String woTimeBegin) {
        this.woTimeBegin = woTimeBegin;
    }

    public String getWoTimeEnd() {
        return woTimeEnd;
    }

    public void setWoTimeEnd(String woTimeEnd) {
        this.woTimeEnd = woTimeEnd;
    }

    public String getWoDescription() {
        return woDescription;
    }

    public void setWoDescription(String woDescription) {
        this.woDescription = woDescription;
    }

    public int getLimitOfTrainees() {
        return limitOfTrainees;
    }

    public void setLimitOfTrainees(int limitOfTrainees) {
        this.limitOfTrainees = limitOfTrainees;
    }
}
