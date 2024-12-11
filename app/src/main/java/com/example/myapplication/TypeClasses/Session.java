package com.example.myapplication.TypeClasses;



import java.util.UUID;

public class Session implements Comparable<Session> {
    private UUID id = UUID.randomUUID();
    private int circuitKey;
    private String circuitShortName;
    private String countryCode;
    private int countryKey;
    private String countryName;
    private String dateEnd;
    private String dateStart;
    private String gmtOffset;
    private String location;
    private int meetingKey;
    private int sessionKey;
    private String sessionName;
    private String sessionType;
    private int year;
    private static Session instance;

    // Public method to provide access to the instance
    public static Session getInstance() {

        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Session() {
        // Default constructor
        this.id = UUID.randomUUID();
    }

    public Session(int circuitKey, String circuitShortName, String countryCode, int countryKey, String countryName, String dateEnd, String dateStart, String gmtOffset, String location, int meetingKey, int sessionKey, String sessionName, String sessionType, int year) {
        //this.id = UUID.randomUUID();
        this.circuitKey = circuitKey;
        this.circuitShortName = circuitShortName;
        this.countryCode = countryCode;
        this.countryKey = countryKey;
        this.countryName = countryName;
        this.dateEnd = dateEnd;
        this.dateStart = dateStart;
        this.gmtOffset = gmtOffset;
        this.location = location;
        this.meetingKey = meetingKey;
        this.sessionKey = sessionKey;
        this.sessionName = sessionName;
        this.sessionType = sessionType;
        this.year = year;
    }

    // Getters and setters for all fields
    // ...


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
    public String getDateEnd(){
        return dateEnd;
    }
    public String getGmtOffset(){
        return  gmtOffset;
    }
    public String getLocation(){
        return location;
    }
    public int getMeetingKey(){
        return meetingKey;
    }
    public int getSessionKey(){
        return sessionKey;
    }
    public String getSessionName(){
        return sessionName;
    }
    public String getSessionType(){
        return sessionType;
    }
    public int getYear(){
        return year;

    }
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setGmtOffset(String gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMeetingKey(int meetingKey) {
        this.meetingKey = meetingKey;
    }
    public void setSessionKey(int sessionKey) {
        this.sessionKey = sessionKey;
    }
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Session other) {
        return this.dateStart.compareTo(other.dateStart);
    }


}
