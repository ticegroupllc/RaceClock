package com.example.myapplication.Utilities;

import com.example.myapplication.TypeClasses.JsonRace;
import com.example.myapplication.TypeClasses.JsonRaceEvent;
import com.example.myapplication.TypeClasses.JsonRaceSeries;
import com.example.myapplication.TypeClasses.JsonRaceSeriesUserSettings;
import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceEvent;
import com.example.myapplication.TypeClasses.RaceSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class TestValues {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US);

    public static JsonRace jsonRaceTest = new JsonRace(
            "Miami",
            "Miami",
            "2024-01-01T16:00:00Z",
            "miami",
            "Autodromo miami",
            "United States",
            23.23,
            40.7,
            "miami.png",
            Arrays.asList(new JsonRaceEvent("Practice 1", "Practice", "2024-01-01T16:00:00Z"))
    );

    public static Race defaultRaceValue = new Race(
            "Miami",
            "Miami",
            parseDate("2024-01-01T16:00:00Z"),
            "miami",
            "Autodromo miami",
            "United States",
            23.23,
            40.7,
            "miami.png",
            Arrays.asList(new RaceEvent("Practice 1", RaceEvent.EventType.PRACTICE, "2024-01-01T16:00:00Z"
            )
            )
    );//parseDate("2024-01-01T16:00:00Z"))));



    public static Race raceTest = new Race(
            "Miami",
            "Miami",
            parseDate("2024-01-01T16:00:00Z"),
            "miami",
            "Autodromo miami",
            "United States",
            23.23,
            40.7,
            "miami.png",
            Arrays.asList(new RaceEvent("Practice 1", RaceEvent.EventType.PRACTICE, "2024-01-01T16:00:00Z"
            ))
    );//parseDate("2024-01-01T16:00:00Z"))));

    public static Race emptyRace = new Race(
            "",
            "",
            new Date(),
            "",
            "",
            "",
            0,
            0,
            "",
            Arrays.asList(new RaceEvent("", RaceEvent.EventType.PRACTICE, "2024-01-01T16:00:00Z"
            )));//new Date())));

    public static RaceEvent defaultRaceEventValue = new RaceEvent(
            "Race",
            RaceEvent.EventType.RACE,
            "2024-12-17T16:00:00Z"
    );
    public static RaceEvent defaultPracticeEventValue = new RaceEvent(
            "Practice 1",
            RaceEvent.EventType.PRACTICE,
            "2024-12-16T16:00:00Z"
    );

    public static RaceSeries emptySeries = new RaceSeries(
            "",
            "",
            "",
            "",
            "",
            "",
            Arrays.asList()
    );

    public static JsonRace emptyJsonRace = new JsonRace(
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            0,
            "",
            Arrays.asList(new JsonRaceEvent("Practice 1", "Practice", "2024-01-01T16:00:00Z"))
    );

    public static JsonRaceSeries emptyJsonRaceSeries = new JsonRaceSeries(
            "",
            "",
            "",
            "",
            "",
            "",
            Arrays.asList()
    );

    public static JsonRaceSeriesUserSettings emptyJsonRaceSeriesUserSettings = new JsonRaceSeriesUserSettings(
            "",
            "",
            "",
            "",
            "",
            "",
            Arrays.asList()
    );

    public static JsonRaceSeriesUserSettings jsonRaceSeriesUserSettingsTest = new JsonRaceSeriesUserSettings(
            "",
            "",
            "",
            "The IndyCar Series, currently known as the NTT IndyCar Series under sponsorship, is the highest class of regional North American open-wheel racing in the United States, which has been conducted under the auspices of various sanctioning bodies since 1920 after two initial attempts in 1905 and 1916.",
            "WRC",
            "wombat",
            Arrays.asList(new JsonRace(
                    "Miami",
                    "Miami",
                    "2024-01-01T16:00:00Z",
                    "miami",
                    "Autodromo miami",
                    "United States",
                    23.23,
                    40.7,
                    "miami.png",
                    Arrays.asList(new JsonRaceEvent("Practice 1", "Practice", "2024-01-01T16:00:00Z"))
            ))
    );

    public static JsonRaceSeries jsonRaceSeriesTest = new JsonRaceSeries(
            "",
            "",
            "",
            "The IndyCar Series, currently known as the NTT IndyCar Series under sponsorship, is the highest class of regional North American open-wheel racing in the United States, which has been conducted under the auspices of various sanctioning bodies since 1920 after two initial attempts in 1905 and 1916.",
            "WRC",
            "wombat",
            Arrays.asList(new JsonRace(
                    "Miami",
                    "Miami",
                    "2024-01-01T16:00:00Z",
                    "miami",
                    "Autodromo miami",
                    "United States",
                    23.23,
                    40.7,
                    "miami.png",
                    Arrays.asList(new JsonRaceEvent("Practice 1", "Practice", "2024-01-01T16:00:00Z"))
            ))
    );

    public static RaceSeries raceSeriesTest = new RaceSeries(
            "",
            "",
            "",
            "The IndyCar Series, currently known as the NTT IndyCar Series under sponsorship, is the highest class of regional North American open-wheel racing in the United States, which has been conducted under the auspices of various sanctioning bodies since 1920 after two initial attempts in 1905 and 1916.",
            "WRC",
            "wombat",
            Arrays.asList(raceTest)
    );

    private static Date parseDate(String dateString) {
        try {
            return dateFormatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static RaceSeries emptySeries() {
        return emptySeries;
    }

    public static Race defaultRaceValue() {
        return  defaultRaceValue;
    }

    public static Race emptyRace() {
        return emptyRace;
    }
}