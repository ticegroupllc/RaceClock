package com.example.myapplication.TypeClasses;

import java.util.Objects;
import java.util.UUID;

// RaceEvent class
public class RaceEvent implements Comparable<RaceEvent> {
    private UUID id = UUID.randomUUID();
    private String eventName;
    private EventType eventType;
    private String eventDate;
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


    public RaceEvent(String eventName, EventType eventType, String eventDate) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }

    public RaceEvent(String eventName, EventType eventType, String eventDate, int circuitKey, String circuitShortName, String countryCode, int countryKey, String countryName, String dateEnd,
                     String dateStart, String gmtOffset, String location, int meetingKey, int sessionKey, String sessionName, String sessionType, int year) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDate = eventDate;
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

    public enum EventType {
        PRACTICE,
        QUALIFYING,
        SPRINTRACE,
        RACE;
    }

    public static String getStringFromType(EventType type) {
        switch (type) {
            case PRACTICE:
                return "Practice";
            case QUALIFYING:
                return "Qualifying";
            case SPRINTRACE:
                return "Sprint Race";
            case RACE:
                return "Race";
            default:
                return "";
        }
    }

    public static EventType getTypeFromString(String type) {
        type = type.toUpperCase();
        switch (type) {

            case "PRACTICE":
                return EventType.PRACTICE;
            case "QUALIFYING":
                return EventType.QUALIFYING;
            case "SPRINT RACE":
                return EventType.SPRINTRACE;
            case "RACE":
                return EventType.RACE;
            default:
                return null;
        }
    }


    @Override
    public int compareTo(RaceEvent other) {
        return this.eventDate.compareTo(other.eventDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventName, eventType, eventDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RaceEvent raceEvent = (RaceEvent) obj;
        return id.equals(raceEvent.id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public EventType getEventType() {

        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getCircuitKey() {
        return circuitKey;
    }

    public void setCircuitKey(int circuitKey) {
        this.circuitKey = circuitKey;
    }

    public String getCircuitShortName() {
        return circuitShortName;
    }

    public void setCircuitShortName(String circuitShortName) {
        this.circuitShortName = circuitShortName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getCountryKey() {
        return countryKey;
    }

    public void setCountryKey(int countryKey) {
        this.countryKey = countryKey;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDateEnd() {
        return dateEnd;
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

    public String getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(String gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMeetingKey() {
        return meetingKey;
    }

    public void setMeetingKey(int meetingKey) {
        this.meetingKey = meetingKey;
    }

    public int getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(int sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}




