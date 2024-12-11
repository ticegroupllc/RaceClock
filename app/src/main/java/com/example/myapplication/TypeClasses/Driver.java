package com.example.myapplication.TypeClasses;

public class Driver {
    private String broadcastName;
    private String countryCode;
    private int driverNumber;
    private String firstName;
    private String fullName;
    private String headshotUrl;
    private String lastName;
    private int meetingKey;
    private String nameAcronym;
    private int sessionKey;
    private String teamColour;
    private String teamName;

    public Driver() {
        // Default constructor
    }

    public Driver(String broadcastName, String countryCode, int driverNumber, String firstName, String fullName, String headshotUrl, String lastName, int meetingKey, String nameAcronym, int sessionKey, String teamColour, String teamName) {
        this.broadcastName = broadcastName;
        this.countryCode = countryCode;
        this.driverNumber = driverNumber;
        this.firstName = firstName;
        this.fullName = fullName;
        this.headshotUrl = headshotUrl;
        this.lastName = lastName;
        this.meetingKey = meetingKey;
        this.nameAcronym = nameAcronym;
        this.sessionKey = sessionKey;
        this.teamColour = teamColour;
        this.teamName = teamName;
    }
// Getters and setters for all fields // ...

    private String getBroadcastName(){
        return broadcastName;
    }
    private String getCountryCode(){
        return countryCode;
    }
    private int getDriverNumber(){
        return driverNumber;
    }
    private String getFirstName(){
        return firstName;
    }
    private String getFullName(){
        return fullName;
    }
    private String getHeadshotUrl(){
        return headshotUrl;
    }
    private String getLastName(){
        return lastName;
    }
    private int getMeetingKey(){
        return meetingKey;
    }
    private String getNameAcronym(){
        return nameAcronym;
    }
    private int getSessionKey(){
        return sessionKey;
    }
    private String getTeamColour(){
        return teamColour;
    }
    private String getTeamName(){
        return teamName;
    }

}