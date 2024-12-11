package com.example.myapplication.TypeClasses;
// jsonRaceEvent class
public class JsonRaceEvent {
    private String eventName;
    private String eventType;
    private String eventDate;

    public JsonRaceEvent(String eventName, String eventType, String eventDate) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}