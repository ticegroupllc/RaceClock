package com.example.myapplication.TypeClasses;

public class Stint {
    private String compound;
    private int driverNumber;
    private int lapEnd;
    private int lapStart;
    private int meetingKey;
    private int sessionKey;
    private int stintNumber;
    private int tyreAgeAtStart;

    public Stint() {
        // Default constructor
    }

    public Stint(String compound, int driverNumber, int lapEnd, int lapStart, int meetingKey, int sessionKey, int stintNumber, int tyreAgeAtStart) {
        this.compound = compound;
        this.driverNumber = driverNumber;
        this.lapEnd = lapEnd;
        this.lapStart = lapStart;
        this.meetingKey = meetingKey;
        this.sessionKey = sessionKey;
        this.stintNumber = stintNumber;
        this.tyreAgeAtStart = tyreAgeAtStart;
    }

    // Getters and setters for all fields
    // ...
}
