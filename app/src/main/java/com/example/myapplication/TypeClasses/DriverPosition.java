package com.example.myapplication.TypeClasses;

public class DriverPosition {
    private String date;
    private int driverNumber;
    private int meetingKey;
    private int position;
    private int sessionKey;

    public DriverPosition() {
        // Default constructor
    }

    public DriverPosition(String date, int driverNumber, int meetingKey, int position, int sessionKey) {
        this.date = date;
        this.driverNumber = driverNumber;
        this.meetingKey = meetingKey;
        this.position = position;
        this.sessionKey = sessionKey;
    }

    // Getters and setters for all fields
    // ...

    private String getDate(){
        return date;
    }
    private int getDriverNumber(){
        return driverNumber;
    }
    private int getMeetingKey(){
        return meetingKey;
    }
    private int getPosition(){
        return position;
    }
    private int getSessionKey(){
        return sessionKey;
    }
}
