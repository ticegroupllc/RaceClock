package com.example.myapplication.Utilities;



    //
//  HelperFunctions.swift
//  RaceDay
//
//  Created by David Tice on 12/31/23.
//




import android.content.Context;
import android.widget.Switch;

import com.example.myapplication.TypeClasses.JsonRace;
import com.example.myapplication.TypeClasses.JsonRaceEvent;
import com.example.myapplication.TypeClasses.JsonRaceSeries;
import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceEvent;
import com.example.myapplication.TypeClasses.RaceSeries;
import com.example.myapplication.placeholder.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class HelperFunctions {

    /*public static List<Race> convertJsonRaceArrayToRaceArray(List<JsonRace> jsonRaces) {
        List<Race> races = new ArrayList<>();
        for (JsonRace jsonRace : jsonRaces) {
            races.add(convertJsonRaceToRace(jsonRace));
        }
        return races;
    }*/

    /*public static List<Event> convertJsonEventArrayToEventArray(List<Event> jsonEvents) {
        List<Race> races = new ArrayList<>();
        for (JsonRace event : jsonEvents) {
            races.add(convertJsonEventToEvent(jsonEvents));
        }
        return races;
    }*/

    public static Date jsonRaceTimeConverter(String jsonDate) {
        Date parsedRaceTime = new Date();
        if (!jsonDate.isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
            try {
                parsedRaceTime = formatter.parse(jsonDate);
            } catch (ParseException e) {
                //System.out.println("While converting jsonRace to Race, the date string is not in a valid format. Received " + jsonDate);
            }
        }
        return parsedRaceTime;
    }

    /*public static Race convertJsonRaceToRace(JsonRace jsonRace) {
        Date parsedRaceTime = jsonRaceTimeConverter(jsonRace.getRaceTime());
        return new Race(
                jsonRace.getRaceName(),
                jsonRace.getShortName(),
                parsedRaceTime,
                jsonRace.getRaceTrack(),
                jsonRace.getCircuitName(),
                jsonRace.getCountry(),
                jsonRace.getLatitude(),
                jsonRace.getLongitude(),
                jsonRace.getImage(),
                convertJsonRaceEventsToRaceEvents(jsonRace.getRaceEvents())
        );
    }*/
    public static Race convertJsonRaceToRace(String jsonRaceString) {
        try {

            System.out.println("Value of stored jsonString passed to convertJasonRaceToRace" + jsonRaceString);
            JSONObject jsonObject = new JSONObject(jsonRaceString);
            System.out.println(jsonObject.toString());
            System.out.println("Starting date parsing. ");
            Date parsedRaceTime = jsonRaceTimeConverter(jsonObject.getString("raceTime"));
            List<JsonRaceEvent> events = new ArrayList<>();
            System.out.println("Value of parsed date = " + parsedRaceTime.toString());
            String jsonArray = jsonObject.get("raceEvents").toString();
            List<RaceEvent> newRaceEvents = convertJsonRaceEventsToRaceEvents(jsonArray);
            System.out.println("Returned raceEvents = " + newRaceEvents.toString());
            String raceName = jsonObject.getString("raceName");
            String shortname = jsonObject.getString("shortName");
            String raceTrack = jsonObject.getString("raceTrack");
            String circuitname = jsonObject.getString("circuit_name");
            String country = jsonObject.getString("country");
            double lat = jsonObject.getDouble("latitude");
            double lon = jsonObject.getDouble("longitude");
            String image = jsonObject.getString("image");
            System.out.println("Value of stored variables = " + raceName + shortname + parsedRaceTime + raceTrack + circuitname + country + lat + lon + image + newRaceEvents);
            return new Race(raceName, shortname, parsedRaceTime, raceTrack, circuitname, country, lat, lon, image, newRaceEvents);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //return TestValues.emptyRace();
    }

    public static List<RaceEvent> convertJsonRaceEventsToRaceEvents(String jsonRaceEventsString) throws JSONException {
        System.out.println("Value of stored jsonString passed to convertJasonRaceEventsToRaceEvents" + jsonRaceEventsString);
        List<RaceEvent> convertedRaceEvents = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonRaceEventsString);

            //JSONObject jsonEventObject = new JSONObject(jsonRaceEventsString);

            for (int y = 0; y < jsonArray.length(); y++) {
                JSONObject jsonEventObject = jsonArray.getJSONObject(y);
                convertedRaceEvents.add(new RaceEvent(
                        jsonEventObject.getString("eventName"),
                        RaceEvent.getTypeFromString(jsonEventObject.getString("eventType")),
                        jsonEventObject.getString("eventDate"))
                );

                //System.out.println(events.toString());

                //jsonRaceEvent.getEventDate()
                //));
            }
        /*for (JsonRaceEvent jsonRaceEvent : jsonRaceEvents) {
            Date parsedRaceTime = jsonRaceTimeConverter(jsonRaceEvent.getEventDate());
            convertedRaceEvents.add(new RaceEvent(
                    jsonRaceEvent.getEventName(),
                    RaceEvent.getTypeFromString(jsonRaceEvent.getEventType().toUpperCase()),
                    jsonRaceEvent.getEventDate()
            ));
        }*/
            System.out.println("\nList of converted raceEvents: \n" + convertedRaceEvents);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedRaceEvents;
    }

    /*public static RaceSeries convertJsonRaceSeriesToRaceSeries(JsonRaceSeries jsonRaceSeries) {
        return new RaceSeries(
                jsonRaceSeries.getName(),
                jsonRaceSeries.getUrl(),
                jsonRaceSeries.getType(),
                jsonRaceSeries.getDescription(),
                jsonRaceSeries.getAbbreviation(),
                jsonRaceSeries.getSeriesLogo(),
                convertJsonRaceArrayToRaceArray(jsonRaceSeries.getRacelist())
        );
    }*/

    // This method retrieves a drawable resource ID from its name
    public static int getResourceIdFromString(Context context, String resourceName) {
        // Use 'drawable' as the resource type, change as needed
        String resourceType = "drawable";
        //Make sure the string is all lowercase
        resourceName = resourceName.toLowerCase();
        //Remove any spaces in the string and replace with underscore
        resourceName = resourceName.replace(" ", "_");

        // Use getIdentifier() to retrieve the resource ID
        //int resourceId =
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    public static JSONObject convertRaceSeriestoJSONRaceSeries(RaceSeries raceSeries) {
        // Create an object with properties
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", raceSeries.getName());
            jsonObject.put("url", raceSeries.getUrl());
            jsonObject.put("type", raceSeries.getType());
            jsonObject.put("description", raceSeries.getDescription());
            jsonObject.put("abbreviation", raceSeries.getAbbreviation());
            jsonObject.put("seriesLogo", raceSeries.getSeriesLogo());
            jsonObject.put("racelist", convertRaceListToJSONRaceArray(raceSeries));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // Convert the object to a JSON string
        String jsonString = jsonObject.toString();

        // Print the JSON string
        //System.out.println("\n\nResults of converting raceSeries to json: " + jsonString);
        return jsonObject;
    }


    public static JSONArray convertRaceListToJSONRaceArray(RaceSeries raceSeries) {
        // Create an array of objects
        JSONObject[] objectsArray = new JSONObject[1];

        // Convert array of objects to JSON array using a for loop
        JSONArray jsonArray = new JSONArray();
        for (Race race : raceSeries.getRaceArray()) {
            jsonArray.put(convertRacetoJSONRace(race));
        }

        return jsonArray;
    }

    public static JSONObject convertRacetoJSONRace(Race race) {
        // Create an object with properties
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("raceName", race.getRaceName());
            jsonObject.put("shortName", race.getShortName());

            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Set time zone if needed
            String isoDateString = isoFormat.format(race.getRaceDate());
            System.out.println("When converting a race to JSONRace this is the value of time taken from race: " + isoDateString);
            jsonObject.put("raceTime", isoDateString);
            //System.out.println("Value of race start time: "+ race.getRaceDate());
            jsonObject.put("raceTrack", race.getRaceTrack());
            jsonObject.put("circuit_name", race.getCircuitName());
            jsonObject.put("country", race.getCountry());
            jsonObject.put("latitude", race.getRaceLat());
            jsonObject.put("longitude", race.getRaceLon());
            jsonObject.put("image", race.getTrackLayoutImage());

            String eventarrayString = convertEventArrayToJSONEventArray(race.getRaceEvents()).toString();
            //System.out.println("This is the value of the returned eventArray " + eventarrayString);


            jsonObject.put("raceEvents", eventarrayString);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // Convert the object to a JSON string
        String jsonString = jsonObject.toString();

        // Print the JSON string
        //System.out.println("Result of converting race to JSONRace= " + jsonString);
        return jsonObject;
    }

    public static String convertEventArrayToJSONEventArray(List<RaceEvent> raceEvents) {
        // Create an array of objects

        JSONObject[] objectsArray = new JSONObject[1];

        // Convert array of objects to JSON array using a for loop
        JSONArray jsonArray = new JSONArray();
        for (RaceEvent raceEvent : raceEvents) {
            jsonArray.put(convertEventToJSONObject(raceEvent));
        }
        /*if (jsonArray.length() > 0) {
            System.out.println("Result of converting eventArray to JSONArray= " + jsonArray.toString());
        }*/
        return jsonArray.toString();
    }


    public static String convertEventToJSONEvent(RaceEvent raceEvent) {
        // Create an object with properties
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("eventName", raceEvent.getEventName());
            jsonObject.put("eventType", raceEvent.getEventType());
            jsonObject.put("eventDate", raceEvent.getEventDate());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // Convert the object to a JSON string
        String jsonString = jsonObject.toString();

        // Print the JSON string
        System.out.println("Result of converting event to JSONEvent = " + jsonString);
        return jsonString;
    }

    public static JSONObject convertEventToJSONObject(RaceEvent raceEvent) {
        // Create an object with properties
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("eventName", raceEvent.getEventName());
            jsonObject.put("eventType", raceEvent.getEventType());
            jsonObject.put("eventDate", raceEvent.getEventDate());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    public static RaceEvent getNextRaceEvent(Race race) {
        PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
        boolean showRaceTimes = preferences.getBoolean("showRaceTimes", true);
        boolean showPracticeTimes = preferences.getBoolean("showPracticeTimes", true);
        boolean showQualifyingTimes = preferences.getBoolean("showQualifyingTimes", true);
        boolean showSprintTimes = preferences.getBoolean("showSprintRaceTimes", true);

        Date date = new Date();
        //Date newEventDate;
        Date existingEventDate;
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        RaceEvent someEvent = TestValues.defaultRaceValue().getRaceEvents().get(0);

        /*try {
            if (!race.getRaceEvents().isEmpty() && race.getRaceEvents() != null) {

                RaceEvent raceEvent = race.getRaceEvents().get(0);
                //for (RaceEvent raceEvent : race.getRaceEvents()) {
                    try {
                        System.out.println("Value of racedate in " + raceEvent.getEventDate());
                        Date newEventDate = isoFormat.parse(raceEvent.getEventDate());
                        existingEventDate = isoFormat.parse(TestValues.defaultRaceEventValue.getEventDate());
                        System.out.println("Event Type is:  " + raceEvent.getEventType().toString() + "\n\n ShowPractice = " + showPracticeTimes + "\n ShowQualifying = " + showQualifyingTimes + "\n ShowSprint = "+ showSprintTimes + "\n ShowRace = "+ showRaceTimes);
                        if (newEventDate != null && existingEventDate != null) {
                            System.out.println("newEventDate and existingEventDate are not null. newEventDate = "+newEventDate.toString() + "existingEventDate = "+ existingEventDate.toString());
                            System.out.println("eventdate comparison = " + newEventDate.compareTo(existingEventDate));
                            if (newEventDate.compareTo(existingEventDate) < 0) {


                                System.out.println("\n\n\nraceEvent.getEventType().equals(RaceEvent.EventType.PRACTICE = "+ raceEvent.getEventType().equals(RaceEvent.EventType.PRACTICE ));
                                System.out.println("\n\n\nraceEvent.getEventType().equals(RaceEvent.EventType.QUALIFYING) = "+ raceEvent.getEventType().equals(RaceEvent.EventType.QUALIFYING));
                                System.out.println("\n\n\nraceEvent.getEventType().equals(RaceEvent.EventType.SPRINTRACE) = "+ raceEvent.getEventType().equals(RaceEvent.EventType.SPRINTRACE));
                                System.out.println("\n\n\nraceEvent.getEventType().equals(RaceEvent.EventType.RACE) = "+ raceEvent.getEventType().equals(RaceEvent.EventType.RACE));
                                switch (raceEvent.getEventType()){
                                    case PRACTICE:
                                        System.out.println("Handling a practice session: " + raceEvent.getEventType());
                                        if(showPracticeTimes){
                                            return raceEvent;
                                        } else {
                                            if (race.getRaceEvents().size()> 1) {
                                                return getNextRaceEvent(race, raceEvent, 1);
                                            } else {
                                                return TestValues.defaultRaceEventValue;
                                            }
                                        }
                                    case QUALIFYING:
                                        System.out.println("Handling a qualifying session: " + raceEvent.getEventType());
                                        if(showQualifyingTimes){
                                            return raceEvent;
                                        }else {
                                            if (race.getRaceEvents().size()> 1) {
                                                return getNextRaceEvent(race, raceEvent, 1);
                                            } else {
                                                return TestValues.defaultRaceEventValue;
                                            }
                                        }
                                    case SPRINTRACE:
                                        System.out.println("Handling a sprint: " + raceEvent.getEventType());
                                        if(showSprintTimes){
                                            return raceEvent;
                                        }else {
                                            if (race.getRaceEvents().size()> 1) {
                                                return getNextRaceEvent(race, raceEvent, 1);
                                            } else {
                                                return TestValues.defaultRaceEventValue;
                                            }
                                        }
                                    case RACE:
                                        System.out.println("Handling a race: " + raceEvent.getEventType());
                                        if(showRaceTimes){
                                            return raceEvent;
                                        }else {
                                            if (race.getRaceEvents().size()> 1) {
                                                return getNextRaceEvent(race, raceEvent, 1);
                                            } else {
                                                return TestValues.defaultRaceEventValue;
                                            }
                                        }
                                    default:
                                        System.out.println("Unknown event type: " + raceEvent.getEventType()); // Handle unknown event types break;
                                }
                            } else  if (newEventDate.compareTo(existingEventDate) > 0) { {

                            }
                            else {
                                if ( race.getRaceEvents().size() > 1) {

                                    return getNextRaceEvent(race, raceEvent, 0);
                                } else {
                                    return TestValues.defaultRaceEventValue;
                                }
                            }
                        }
                    } catch (ParseException e) {
                        System.out.println("Unparsable date:");
                        System.out.println(raceEvent.getEventDate());
                        throw new RuntimeException(e);
                    }
                //}
            } else {
                System.out.println("The event array provided has no entries. ");

            }
        } catch (NullPointerException e) {
            System.out.println("RaceEvents has no value");
            return someEvent;
        }*/
    return someEvent;

    }


    public static RaceEvent getNextRaceEvent(Race race, RaceEvent currentRaceEvent, int iteration) {
        PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
        boolean showRaceTimes = preferences.getBoolean("showRaceTimes", true);
        boolean showPracticeTimes = preferences.getBoolean("showPracticeTimes", true);
        boolean showQualifyingTimes = preferences.getBoolean("showQualifyingTimes", true);
        boolean showSprintTimes = preferences.getBoolean("showSprintRaceTimes", true);

        Date date = new Date();
        //Date newEventDate;
        Date existingEventDate;
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        RaceEvent someEvent = TestValues.defaultRaceValue().getRaceEvents().get(0);

        try {
            if (!race.getRaceEvents().isEmpty() && race.getRaceEvents() != null && iteration < race.getRaceEvents().size() - 1) {
                //for (RaceEvent raceEvent : race.getRaceEvents()) {
                RaceEvent raceEvent = race.getRaceEvents().get(iteration);
                try {
                    System.out.println("Value of racedate in " + raceEvent.getEventDate());
                    Date newEventDate = isoFormat.parse(raceEvent.getEventDate());
                    existingEventDate = isoFormat.parse(currentRaceEvent.getEventDate());
                    System.out.println("Event Type is:  " + raceEvent.getEventType().toString() + "\n\n ShowPractice = " + showPracticeTimes + "\n ShowQualifying = " + showQualifyingTimes + "\n ShowSprint = " + showSprintTimes + "\n ShowRace = " + showRaceTimes);
                    if (newEventDate != null && existingEventDate != null) {
                        System.out.println("newEventDate and existingEventDate are not null. newEventDate = " + newEventDate.toString() + "existingEventDate = " + existingEventDate.toString());
                        System.out.println("eventdate comparison = " + newEventDate.compareTo(existingEventDate));
                        if (newEventDate.compareTo(existingEventDate) > 0) {

                            if(raceEvent.getEventType() == RaceEvent.EventType.PRACTICE){
                                System.out.println("Handling a practice session: " + raceEvent.getEventType());
                                if (showPracticeTimes) {
                                    return raceEvent;
                                } else {
                                    if (iteration < race.getRaceEvents().size() - 1) {
                                        iteration++;
                                        return getNextRaceEvent(race, currentRaceEvent, iteration);
                                    } else {
                                        return TestValues.defaultRaceEventValue;
                                    }
                                }
                            }
                            if(raceEvent.getEventType() == RaceEvent.EventType.QUALIFYING){
                                System.out.println("Handling a qualifying session: " + raceEvent.getEventType());
                                if (showQualifyingTimes) {
                                    return raceEvent;
                                } else {
                                    if (iteration < race.getRaceEvents().size() - 1) {
                                        iteration++;
                                        return getNextRaceEvent(race, currentRaceEvent, iteration);
                                    } else {
                                        return TestValues.defaultRaceEventValue;
                                    }
                                }
                            }
                            if(raceEvent.getEventType() == RaceEvent.EventType.SPRINTRACE){
                                System.out.println("Handling a sprint session: " + raceEvent.getEventType());
                                if (showSprintTimes) {
                                    System.out.println("Showsprint = " + showSprintTimes);
                                    return raceEvent;
                                } else {
                                    System.out.println("Showsprint = " + showSprintTimes);
                                    if (iteration < race.getRaceEvents().size() - 1) {
                                        iteration++;
                                        return getNextRaceEvent(race, currentRaceEvent, iteration);
                                    } else {
                                        return TestValues.defaultRaceEventValue;
                                    }
                                }
                            }
                            if(raceEvent.getEventType() == RaceEvent.EventType.RACE){
                                System.out.println("Handling a race session: " + raceEvent.getEventType());
                                if (showSprintTimes) {
                                    return raceEvent;
                                } else {
                                    if (iteration < race.getRaceEvents().size() - 1) {
                                        iteration++;
                                        return getNextRaceEvent(race, currentRaceEvent, iteration);
                                    } else {
                                        return TestValues.defaultRaceEventValue;
                                    }
                                }
                            }

                            /*switch (raceEvent.getEventType()) {
                                case PRACTICE:
                                    System.out.println("Handling a practice session: " + raceEvent.getEventType());
                                    if (showPracticeTimes) {
                                        return raceEvent;
                                    } else {
                                        if (iteration < race.getRaceEvents().size() - 1) {
                                            iteration++;
                                            return getNextRaceEvent(race, currentRaceEvent, iteration);
                                        } else {
                                            return TestValues.defaultRaceEventValue;
                                        }
                                    }
                                case QUALIFYING:
                                    System.out.println("Handling a qualifying session: " + raceEvent.getEventType());
                                    if (showQualifyingTimes) {
                                        return raceEvent;
                                    } else {
                                        if (iteration < race.getRaceEvents().size() - 1) {
                                            iteration++;
                                            return getNextRaceEvent(race, currentRaceEvent, iteration);
                                        } else {
                                            return TestValues.defaultRaceEventValue;
                                        }
                                    }
                                case SPRINTRACE:
                                    System.out.println("Handling a sprint: " + raceEvent.getEventType());
                                    if (showSprintTimes) {
                                        return raceEvent;
                                    } else {
                                        if (iteration < race.getRaceEvents().size() - 1) {
                                            iteration++;
                                            return getNextRaceEvent(race, currentRaceEvent, iteration);
                                        } else {
                                            return TestValues.defaultRaceEventValue;
                                        }
                                    }
                                case RACE:
                                    System.out.println("Handling a race: " + raceEvent.getEventType());
                                    if (showRaceTimes) {
                                        return raceEvent;
                                    } else {
                                        if (iteration < race.getRaceEvents().size() - 1) {
                                            iteration++;
                                            return getNextRaceEvent(race, currentRaceEvent, iteration);
                                        } else {
                                            return TestValues.defaultRaceEventValue;
                                        }
                                    }
                                default:
                                    System.out.println("Unknown event type: " + raceEvent.getEventType()); // Handle unknown event types break;
                            }*/

                                /*if (raceEvent.getEventType() == RaceEvent.EventType.PRACTICE && showPracticeTimes) {
                                    return raceEvent;
                                } else if (raceEvent.getEventType() == RaceEvent.EventType.QUALIFYING && showQualifyingTimes) {
                                    return raceEvent;
                                } else if (raceEvent.getEventType() == RaceEvent.EventType.SPRINTRACE && showSprintTimes) {
                                    return raceEvent;
                                } else if (raceEvent.getEventType() == RaceEvent.EventType.RACE && showRaceTimes) {
                                    return raceEvent;
                                } else {
                                    if (iteration < race.getRaceEvents().size() - 1) {
                                        iteration++;
                                        return getNextRaceEvent(race, currentRaceEvent, iteration);
                                    } else {
                                        return TestValues.defaultRaceEventValue;
                                    }
                                }*/
                        } else {
                            if (iteration < race.getRaceEvents().size() - 1) {
                                iteration++;
                                return getNextRaceEvent(race, currentRaceEvent, iteration);
                            } else {
                                return TestValues.defaultRaceEventValue;
                            }
                        }
                    }
                } catch (ParseException e) {
                    System.out.println("Unparsable date:");
                    System.out.println(raceEvent.getEventDate());
                    throw new RuntimeException(e);
                }
                //}
            } else {
                System.out.println("The event array provided has no entries. ");

            }
        } catch (NullPointerException e) {
            System.out.println("RaceEvents has no value");
            return someEvent;
        }
        return someEvent;

    }

    public static RaceEvent getNextRaceEvent2(Race race)  {
        PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
        boolean showRaceTimes = preferences.getBoolean("showRaceTimes", true);
        boolean showPracticeTimes = preferences.getBoolean("showPracticeTimes", true);
        boolean showQualifyingTimes = preferences.getBoolean("showQualifyingTimes", true);
        boolean showSprintTimes = preferences.getBoolean("showSprintRaceTimes", true);
        System.out.println(("1"));
        Date date = new Date();
        //Date newEventDate;
        Date existingEventDate;
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        RaceEvent someEvent = TestValues.defaultRaceValue().getRaceEvents().get(0);
        RaceEvent finalRaceEvent = null;
        List<RaceEvent> raceEventListFilteredForEventType = new ArrayList<>();
        for(RaceEvent raceEvent: race.getRaceEvents()){
            System.out.println(raceEvent.getEventName() +" , "+ raceEvent.getEventType()+" , "+ raceEvent.getEventDate().toString());
        }
        for (RaceEvent raceEvent : race.getRaceEvents()) {

            if (raceEvent.getEventType().equals(RaceEvent.EventType.PRACTICE)) {
                System.out.println("Handling a practice session: " + raceEvent.getEventType());
                if (showPracticeTimes) {
                    System.out.println("Showpractice = " + showPracticeTimes);
                    raceEventListFilteredForEventType.add(raceEvent);
                }
            } else if (raceEvent.getEventType() == RaceEvent.EventType.QUALIFYING) {
                System.out.println("Handling a qualifying session: " + raceEvent.getEventType());
                if (showQualifyingTimes) {
                    System.out.println("Showqualifying = " + showQualifyingTimes);
                    raceEventListFilteredForEventType.add(raceEvent);
                }
            } else if (raceEvent.getEventType() == RaceEvent.EventType.SPRINTRACE) {
                System.out.println("Handling a sprint session: " + raceEvent.getEventType());
                if (showSprintTimes) {
                    System.out.println("Showsprint = " + showSprintTimes);
                    raceEventListFilteredForEventType.add(raceEvent);
                }
            } else if (raceEvent.getEventType().equals(RaceEvent.EventType.RACE)) {
                System.out.println("Handling a race session: " + raceEvent.getEventType());
                if (showRaceTimes) {
                    System.out.println("Showsrace = " + showRaceTimes);
                    raceEventListFilteredForEventType.add(raceEvent);
                }
            } else {
                System.out.println("Unknown event type: " + raceEvent.getEventType()); // Handle unknown event types break;
            }


            /*switch (raceEvent.getEventType()) {
                case PRACTICE:
                    System.out.println("Handling a practice session: " + raceEvent.getEventType());
                    if (showPracticeTimes) {
                        raceEventListFilteredForEventType.add(raceEvent);
                    }
                case QUALIFYING:
                    System.out.println("Handling a qualifying session: " + raceEvent.getEventType());
                    if (showQualifyingTimes) {
                        raceEventListFilteredForEventType.add(raceEvent);
                    }
                case SPRINTRACE:
                    System.out.println("Handling a sprint: " + raceEvent.getEventType());
                    if (showSprintTimes) {
                        raceEventListFilteredForEventType.add(raceEvent);
                    }
                case RACE:
                    System.out.println("Handling a race: " + raceEvent.getEventType());
                    if (showRaceTimes) {
                        raceEventListFilteredForEventType.add(raceEvent);
                    }
                default:
                    System.out.println("Unknown event type: " + raceEvent.getEventType()); // Handle unknown event types break;
            }*/

        }
        System.out.println(("3"));
        List<RaceEvent> raceEventListFilteredForDate = new ArrayList<>();
        for (RaceEvent raceEvent : raceEventListFilteredForEventType) {

            try {
                if (isoFormat.parse(raceEvent.getEventDate()).compareTo(new Date()) > 0) {
                    raceEventListFilteredForDate.add(raceEvent);
                    //
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


        }
        System.out.println(("\nFinalValue of eventlist"));
        List<RaceEvent> finalRaceEventList = quickSort(raceEventListFilteredForDate, 0, raceEventListFilteredForDate.size() - 1);
        for(RaceEvent raceEvent: finalRaceEventList){
            System.out.println(raceEvent.getEventName() +" , "+ raceEvent.getEventType()+" , "+ raceEvent.getEventDate().toString());
        }


        if (!finalRaceEventList.isEmpty()){
            return  finalRaceEventList.get(0);
        }else{
            return TestValues.defaultRaceEventValue;
        }
    }

    public static List<RaceEvent> quickSort(List<RaceEvent> raceEventList, int low, int high) {
        List<RaceEvent> quickSortedRaceEventList;
        if (low < high) {
            int pi = partition(raceEventList, low, high);

            // Recursively sort elements before and after partition
            quickSort(raceEventList, low, pi - 1);
            quickSort(raceEventList, pi + 1, high);
        }
        quickSortedRaceEventList = raceEventList;
        return quickSortedRaceEventList;
    }

    public static int partition(List<RaceEvent> raceEventList, int low, int high) {
        RaceEvent pivot = raceEventList.get(high);
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (raceEventList.get(j).getEventDate().compareTo(pivot.getEventDate()) <= 0) {
                i++;

                // Swap dates[i] and dates[j]
                RaceEvent temp = raceEventList.get(i);
                raceEventList.set(i, raceEventList.get(j));
                raceEventList.set(j, temp);
            }
        }

        // Swap dates[i + 1] and dates[high] (or pivot)
            /*Date temp = dates[i + 1];
            dates[i + 1] = dates[high];
            dates[high] = temp;*/

        RaceEvent temp = raceEventList.get(i + 1);
        raceEventList.set(i + 1, raceEventList.get(high));
        raceEventList.set(high, temp);

        return i + 1;
    }
}



