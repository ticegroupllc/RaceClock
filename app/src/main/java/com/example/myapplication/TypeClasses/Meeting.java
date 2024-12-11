package com.example.myapplication.TypeClasses;

public class Meeting {
    private int circuitKey;
    private String circuitShortName;
    private String countryCode;
    private int countryKey;
    private String countryName;
    private String dateStart;
    private String gmtOffset;
    private String location;
    private int meetingKey;
    private String meetingName;
    private String meetingOfficialName;
    private int year;
    public Meeting() {
        // Default constructor
    }

    public Meeting(int circuitKey, String circuitShortName, String countryCode, int countryKey, String countryName, String dateStart, String gmtOffset, String location, int meetingKey, String meetingName, String meetingOfficialName, int year) {
        this.circuitKey = circuitKey;
        this.circuitShortName = circuitShortName;
        this.countryCode = countryCode;
        this.countryKey = countryKey;
        this.countryName = countryName;
        this.dateStart = dateStart;
        this.gmtOffset = gmtOffset;
        this.location = location;
        this.meetingKey = meetingKey;
        this.meetingName = meetingName;
        this.meetingOfficialName = meetingOfficialName;
        this.year = year;
    }

    // Getters and setters for all fields // ...

    public int getCircuitKey(){
        return circuitKey;
    }
    public String getCircuitShortName(){
        return circuitShortName;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public int getCountryKey(){
        return countryKey;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getDateStart(){
        return dateStart;
    }
    public String getGmtOffset(){
        return gmtOffset;
    }
    public String getLocation(){
        return location;
    }
    public int getMeetingKey(){
        return meetingKey;
    }
    public String getMeetingName(){
        return meetingName;
    }
    public String getMeetingOfficialName(){
        return meetingOfficialName;
    }
    public  int getYear(){
        return year;
    }
}
