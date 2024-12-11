package com.example.myapplication;

import com.example.myapplication.TypeClasses.Meeting;
import com.example.myapplication.TypeClasses.RaceSeries;

import java.util.List;

public enum ModelEnumSingleton {
    INSTANCE;

    private List<RaceSeries> raceSeriesList;

    // Add any fields and methods you need private
    String value;
    private static List<RaceSeries> raceLibrary;
    private static List<Meeting> meetingList;

    ModelEnumSingleton() {
        ///this.raceSeriesList = raceSeriesList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Example method
    public void showMessage() {
        //System.out.println("Hello from ModelEnumSingleton!");
    }

    // Private constructor to prevent instantiation

    public List<RaceSeries> getInfo() {
        return raceSeriesList;
    }

    public void setInfo(String info) {
        //this.raceSeriesList = info;
    }

    public List<RaceSeries> getRaceLibrary(){
        return  raceLibrary;
    }

    public void setRaceLibrary(List<RaceSeries> raceLibrary){
        this.raceLibrary = raceLibrary;
    }


}