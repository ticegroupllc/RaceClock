package com.example.myapplication.TypeClasses;

import java.util.List;
import java.util.UUID;

public class RaceSeries {
    private UUID id = UUID.randomUUID();
    private String name; // "FIA Formula One World Championship"
    private String url; // "https://www.fia.com/formula-one-world-championship"
    private String type; // "Open-wheel"
    private String description; // "The pinnacle of motorsport, featuring the world's fastest and most technologically advanced racing cars."
    private String abbreviation; // "F1"
    private String seriesLogo;
    private List<Race> raceArray;

    public RaceSeries(String name, String url, String type, String description, String abbreviation, String seriesLogo, List<Race> raceArray) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.description = description;
        this.abbreviation = abbreviation;
        this.seriesLogo = seriesLogo;
        this.raceArray = raceArray;
    }

    // Getters and setters for each field
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getSeriesLogo() {
        return seriesLogo;
    }

    public void setSeriesLogo(String seriesLogo) {
        this.seriesLogo = seriesLogo;
    }

    public List<Race> getRaceArray() {
        return raceArray;
    }

    public void setRaceArray(List<Race> raceArray) {
        this.raceArray = raceArray;
    }
}