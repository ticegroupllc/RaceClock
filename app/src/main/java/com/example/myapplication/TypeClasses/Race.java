package com.example.myapplication.TypeClasses;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// Race class
public class Race {
    private UUID id = UUID.randomUUID();
    private String raceName;
    private String shortName;
    private int meeting_key;
    private Date raceDate;
    private Date gmt_offset;
    private String raceTrack;
    private String circuitName;
    private int circuitKey;
    private String country;
    private int countryKey;
    private double raceLat;
    private double raceLon;
    private String trackLayoutImage;
    private List<RaceEvent> raceEvents;

    public Race(String raceName, String shortName, Date raceDate, String raceTrack, String circuitName, String country, double raceLat, double raceLon, String trackLayoutImage, List<RaceEvent> raceEvents) {
        this.raceName = raceName;
        this.shortName = shortName;
        this.raceDate = raceDate;
        this.raceTrack = raceTrack;
        this.circuitName = circuitName;
        this.country = country;
        this.raceLat = raceLat;
        this.raceLon = raceLon;
        this.trackLayoutImage = trackLayoutImage;
        this.raceEvents = raceEvents;
    }

    public Race(String raceName, String shortName, Date raceDate, String raceTrack, String circuitName, String country) {
        this.raceName = raceName;
        this.shortName = shortName;
        this.raceDate = raceDate;
        this.raceTrack = raceTrack;
        this.circuitName = circuitName;
        this.country = country;
    }

    public Race(String raceName, String shortName, int meeting_key, Date raceDate, Date gmt_offset, String raceTrack, String circuitName, int circuitKey, String country, int countryKey, double raceLat, double raceLon, String trackLayoutImage, List<RaceEvent> raceEvents) {
        this.raceName = raceName;
        this.shortName = shortName;
        this.meeting_key = meeting_key;
        this.raceDate = raceDate;
        this.gmt_offset = gmt_offset;
        this.raceTrack = raceTrack;
        this.circuitName = circuitName;
        this.circuitKey = circuitKey;
        this.country = country;
        this.countryKey = countryKey;
        this.raceLat = raceLat;
        this.raceLon = raceLon;
        this.trackLayoutImage = trackLayoutImage;
        this.raceEvents = raceEvents;
    }

    public UUID getId() {
        return id;
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

    public int getMeeting_key() {
        return meeting_key;
    }

    public void setMeeting_key(int meeting_key) {
        this.meeting_key = meeting_key;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }

    public Date getGmt_offset() {
        return gmt_offset;
    }

    public void setGmt_offset(Date offset) {
        this.gmt_offset = offset;
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

    public int getCircuitKey() {
        return circuitKey;
    }

    public void setCircuitKey(int circuitKey) {
        this.circuitKey = circuitKey;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCountryKey() {
        return countryKey;
    }

    public void setCountryKey(int countryKey) {
        this.countryKey = countryKey;
    }

    public double getRaceLat() {
        return raceLat;
    }

    public void setRaceLat(double raceLat) {
        this.raceLat = raceLat;
    }

    public double getRaceLon() {
        return raceLon;
    }

    public void setRaceLon(double raceLon) {
        this.raceLon = raceLon;
    }

    public String getTrackLayoutImage() {
        return trackLayoutImage;
    }

    public void setTrackLayoutImage(String trackLayoutImage) {
        this.trackLayoutImage = trackLayoutImage;
    }

    public List<RaceEvent> getRaceEvents() {
        return raceEvents;
    }

    public void setRaceEvents(List<RaceEvent> raceEvents) {
        this.raceEvents = raceEvents;
    }
}