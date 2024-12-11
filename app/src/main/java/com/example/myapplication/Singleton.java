package com.example.myapplication;

import com.example.myapplication.TypeClasses.Meeting;
import com.example.myapplication.TypeClasses.RaceSeries;

import java.util.List;

public class Singleton {

        // Static variable to hold the single instance
        private static Singleton instance;
        private static List<RaceSeries> raceLibrary;
        private static List<Meeting> meetingList;
        // Private constructor to prevent instantiation
        private Singleton() {
            //raceLibrary = new Singleton().getRaceLibrary();
        }

        // Public method to provide access to the instance
        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }

        public List<Meeting> getMeetingList(){
            return meetingList;
        }

        public void setMeetingList(List<Meeting> meetingList){
            this.meetingList = meetingList;
        }

        public List<RaceSeries> getRaceLibrary(){
            return  raceLibrary;
        }

    public void setRaceLibrary(List<RaceSeries> raceLibrary){
        this.raceLibrary = raceLibrary;
    }
}
