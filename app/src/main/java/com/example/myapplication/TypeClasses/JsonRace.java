package com.example.myapplication.TypeClasses;

import java.util.List;

// jsonRace class
public class JsonRace {
    private String raceName;
    private String shortName;
    private String raceTime;
    private String raceTrack;
    private String circuitName;
    private String country;
    private double latitude;
    private double longitude;
    private String image;
    private List<JsonRaceEvent> raceEvents;

    public JsonRace(String raceName, String shortName, String raceTime, String raceTrack, String circuitName, String country, double latitude, double longitude, String image, List<JsonRaceEvent> raceEvents) {
        this.raceName = raceName;
        this.shortName = shortName;
        this.raceTime = raceTime;
        this.raceTrack = raceTrack;
        this.circuitName = circuitName;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
        this.raceEvents = raceEvents;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(String raceTime) {
        this.raceTime = raceTime;
    }

    public String getRaceTrack() {
        return raceTrack;
    }

    public void setRaceTrack(String raceTrack) {
        this.raceTrack = raceTrack;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<JsonRaceEvent> getRaceEvents() {
        return raceEvents;
    }

    public void setRaceEvents(List<JsonRaceEvent> raceEvents) {
        this.raceEvents = raceEvents;
    }
}