package com.example.myapplication.TypeClasses;


import java.util.List;

public class JsonRaceSeriesUserSettings {
    private String name; // "FIA Formula One World Championship"
    private String url; // "https://www.fia.com/formula-one-world-championship"
    private String type; // "Open-wheel"
    private String description; // "The pinnacle of motorsport, featuring the world's fastest and most technologically advanced racing cars."
    private String abbreviation; // "F1"
    private String seriesLogo;
    private List<JsonRace> racelist;

    // Constructor
    public JsonRaceSeriesUserSettings(String name, String url, String type, String description, String abbreviation, String seriesLogo, List<JsonRace> racelist) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.description = description;
        this.abbreviation = abbreviation;
        this.seriesLogo = seriesLogo;
        this.racelist = racelist;
    }

    // Getters and setters for each field
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

    public List<JsonRace> getRacelist() {
        return racelist;
    }

    public void setRacelist(List<JsonRace> racelist) {
        this.racelist = racelist;
    }
}
