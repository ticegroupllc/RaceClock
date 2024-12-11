package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.TypeClasses.Driver;
import com.example.myapplication.TypeClasses.DriverPosition;
import com.example.myapplication.TypeClasses.JsonRace;
import com.example.myapplication.TypeClasses.Meeting;
import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceEvent;
import com.example.myapplication.TypeClasses.RaceSeries;
import com.example.myapplication.TypeClasses.Session;
import com.example.myapplication.TypeClasses.Stint;
import com.example.myapplication.Utilities.DownloadJsonTask;
import com.example.myapplication.Utilities.LiveDataConverter;
import com.example.myapplication.Utilities.PreferencesManager;
import com.example.myapplication.Utilities.TestValues;
import com.example.myapplication.placeholder.MyApplication;

import java.text.ParseException;
import java.time.ZonedDateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//import java.util.function.Consumer;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selectedItem = new MutableLiveData<>();
    private final MutableLiveData<List<RaceSeries>> globalRaceSeries = new MutableLiveData<>();
    private final MutableLiveData<RaceSeries> preferedRaceSeries = new MutableLiveData<>();
    private final MutableLiveData<Race> selectedRace = new MutableLiveData<>();
    PreferencesManager preferencesManager = new PreferencesManager(MyApplication.getAppContext());
    private Race race = TestValues.emptyRace;
    private List<Session> sessionList ;
    private List<Driver> driverList ;
    private List<Stint> stintList ;
    private List<DriverPosition> driverPositionList ;
    private List<Meeting> meetingList ;

    public void setGlobalRaceSeries(List<RaceSeries> raceSeriesList) {

        globalRaceSeries.setValue(raceSeriesList);
    }

    public List<RaceSeries> getGlobalRaceSeries() {

        return globalRaceSeries.getValue();
    }

    public void selectRace(Race race) {

        selectedRace.setValue(race);
    }

    public Race getSelectedRace() {
        return
                selectedRace.getValue();
    }

    public void setPreferedRaceSeries(RaceSeries raceSeries) {
        preferedRaceSeries.setValue(raceSeries);
    }

    public RaceSeries getPreferedRaceSeries(){
        return preferedRaceSeries.getValue();
    }



    public void getLiveTimingData(){
        //preferencesManager.setRaceSeriesList(getJsonStringUsingHTTP("http://www.ticegroupllc.com/raceday/V3_2024FIASeries.json" + "?" + UUID.randomUUID().toString()));
        //preferencesManager.setMeetingList(getJsonStringUsingHTTP("https://api.openf1.org/v1/meetings"));
        //preferencesManager.setSessionList ( getJsonStringUsingHTTP("https://api.openf1.org/v1/sessions?country_name=Belgium&session_name=Sprint&year=2024"));
        //preferencesManager.setDriverList( getJsonStringUsingHTTP("https://api.openf1.org/v1/drivers?driver_number=1&session_key=9158"));
        //preferencesManager.setStintList( getJsonStringUsingHTTP("https://api.openf1.org/v1/stints?session_key=9165&tyre_age_at_start>=3"));
        //preferencesManager.setDriverPositionList(getJsonStringUsingHTTP("https://api.openf1.org/v1/drivers?driver_number=1&session_key=9158"));
        //preferencesManager.setMeetingList(getJsonStringUsingHTTP("https://api.openf1.org/v1/meetings?year=2024"));
        System.out.println("\n\nCompleted getLIveTimingData\n\n" + preferencesManager.getMeetingList());
        //preferencesManager.getMeetingList();
        setGlobalRaceSeries(parseJsonRaceSeriesArray(getJsonStringUsingHTTP("http://www.ticegroupllc.com/raceday/V3_2024FIASeries.json" + "?" + UUID.randomUUID().toString())));
        //preferencesManager.getDriverList();
    }



    public static List<Session> getSessionList(int meeting_key){
        return parseJsonSessionArray(getJsonStringUsingHTTP("https://api.openf1.org/v1/sessions?meeting_key="+meeting_key));
        //return sessionList;
    }

    /*
    public List<Driver> getDriverList(){
        return driverList;
    }
    public List<DriverPosition> getDriverPositionList(){
        return driverPositionList;
    }*/



    public static String getJsonStringUsingHTTP(String urlString) {
        String json = "";

        //Setup http fetch task
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        DownloadJsonTask downloadJsonTask = new DownloadJsonTask(urlString);

        Future<String> future = executorService.submit(downloadJsonTask);
        executorService.shutdown();

        try {
            json = future.get();
            //System.out.println("\n\n\n\nPreParsed Data\n\n\n\n");
            //System.out.println(json);
            //System.out.println("\n\n\nEnd PreParsed Data\n\n\n");


        } catch (Exception e) {
            System.out.println("Error downloading data: " + e.getMessage());
        }
        return json;
    }

    public static List<Driver> parseJsonDriverArray(String jsonArrayString) {
        List<Driver> driverList = new ArrayList<>();
        try { JSONArray jsonArray = new JSONArray(jsonArrayString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String broadcastName = jsonObject.getString("broadcast_name");
                String countryCode = jsonObject.getString("country_code");
                int driverNumber = jsonObject.getInt("driver_number");
                String firstName = jsonObject.getString("first_name");
                String fullName = jsonObject.getString("full_name");
                String headshotUrl = jsonObject.getString("headshot_url");
                String lastName = jsonObject.getString("last_name");
                int meetingKey = jsonObject.getInt("meeting_key");
                String nameAcronym = jsonObject.getString("name_acronym");
                int sessionKey = jsonObject.getInt("session_key");
                String teamColour = jsonObject.getString("team_colour");
                String teamName = jsonObject.getString("team_name");
                Driver driver = new Driver(broadcastName, countryCode, driverNumber, firstName, fullName, headshotUrl, lastName, meetingKey, nameAcronym, sessionKey, teamColour, teamName);
                driverList.add(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return driverList;
    }

    public static List<Session> parseJsonSessionArray(String jsonArrayString) {
        List<Session> sessionList = new ArrayList<>();
        try { JSONArray jsonArray = new JSONArray(jsonArrayString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int circuitKey = jsonObject.getInt("circuit_key");
                String circuitShortName = jsonObject.getString("circuit_short_name");
                String countryCode = jsonObject.getString("country_code"); int countryKey = jsonObject.getInt("country_key");
                String countryName = jsonObject.getString("country_name"); String dateEnd = jsonObject.getString("date_end");
                String dateStart = jsonObject.getString("date_start"); String gmtOffset = jsonObject.getString("gmt_offset");
                String location = jsonObject.getString("location"); int meetingKey = jsonObject.getInt("meeting_key");
                int sessionKey = jsonObject.getInt("session_key"); String sessionName = jsonObject.getString("session_name");
                String sessionType = jsonObject.getString("session_type"); int year = jsonObject.getInt("year");
                Session session = new Session(circuitKey, circuitShortName, countryCode, countryKey, countryName, dateEnd, dateStart, gmtOffset, location, meetingKey, sessionKey, sessionName, sessionType, year);
                sessionList.add(session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return sessionList;
    }

    public static List<DriverPosition> parseJsonDriverPositionArray(String jsonArrayString) {
        List<DriverPosition> driverPositionList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String date = jsonObject.getString("date");
                int driverNumber = jsonObject.getInt("driver_number");
                int meetingKey = jsonObject.getInt("meeting_key");
                int position = jsonObject.getInt("position");
                int sessionKey = jsonObject.getInt("session_key");
                DriverPosition driverPosition = new DriverPosition(date, driverNumber, meetingKey, position, sessionKey);
                driverPositionList.add(driverPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return driverPositionList;
    }

    public static List<Stint> parseJsonStintArray(String jsonArrayString) {
        List<Stint> StintList = new ArrayList<>();
        try { JSONArray jsonArray = new JSONArray(jsonArrayString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String compound = jsonObject.getString("compound");
                int driverNumber = jsonObject.getInt("driver_number");
                int lapEnd = jsonObject.getInt("lap_end");
                int lapStart = jsonObject.getInt("lap_start");
                int meetingKey = jsonObject.getInt("meeting_key");
                int sessionKey = jsonObject.getInt("session_key");
                int stintNumber = jsonObject.getInt("stint_number");
                int tyreAgeAtStart = jsonObject.getInt("tyre_age_at_start");
                Stint stint = new Stint(compound, driverNumber, lapEnd, lapStart, meetingKey, sessionKey, stintNumber, tyreAgeAtStart); StintList.add(stint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return StintList;
    }

    public static List<Meeting> parseJsonMeetingArray(String jsonArrayString) {
        List<Meeting> circuitList = new ArrayList<>();
        //System.out.println(new String ("Content of JsonArrayString = " + jsonArrayString));
        try { JSONArray jsonArray = new JSONArray(jsonArrayString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //System.out.println(new String("Content of jsonObject" + jsonObject.getString("circuit_short_name")));
                int circuitKey = jsonObject.getInt("circuit_key");
                String circuitShortName = jsonObject.getString("circuit_short_name");
                String countryCode = jsonObject.getString("country_code");
                int countryKey = jsonObject.getInt("country_key");
                String countryName = jsonObject.getString("country_name");
                String dateStart = new String(jsonObject.getString("date_start").substring(0,jsonObject.getString("date_start").indexOf('+')) + "Z");
                String gmtOffset = jsonObject.getString("gmt_offset");
                String location = jsonObject.getString("location");
                int meetingKey = jsonObject.getInt("meeting_key");
                String meetingName = jsonObject.getString("meeting_name");
                String meetingOfficialName = jsonObject.getString("meeting_official_name");
                int year = jsonObject.getInt("year");

                Meeting circuit = new Meeting(circuitKey, circuitShortName, countryCode, countryKey, countryName, dateStart, gmtOffset, location, meetingKey, meetingName, meetingOfficialName, year); circuitList.add(circuit);
                //System.out.println(new String("Contents of cirucit: " + circuit.getCircuitShortName()));
                circuitList.add(circuit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Results of parseJsonMeetingArray");
        //for(Meeting meeting:circuitList){
        //    System.out.println(meeting.getCircuitShortName());
        //}
        return circuitList;
    }

    public static List<RaceSeries> parseJsonRaceSeriesArray(String jsonArrayString) {
        // Implement a simple JSON parser or use a library like org.json
        // This is a placeholder for the actual parsing logic
        System.out.println("This is the value of the jsaon string given to parseJsonRaceSeriesArray: " + jsonArrayString);

        List<RaceSeries> newRaceSeriesList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            //System.out.println(jsonArray);
            //String pattern = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern();
            //DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

            for (int i = 0; i < jsonArray.length(); i++) {
                /*JSONObject raceSeriesObject = jsonArray.getJSONObject(i);
                String name = raceSeriesObject.getString("name");
                String url = raceSeriesObject.getString("url");
                String type = raceSeriesObject.getString("type");
                String description = raceSeriesObject.getString("description");
                String abbreviation = raceSeriesObject.getString("abbreviation");
                String seriesLogo = raceSeriesObject.getString("seriesLogo");

                JSONArray racesArray = raceSeriesObject.getJSONArray("racelist");
                List<Race> races = new ArrayList<>();

                System.out.println(new String("\n\nAbbreviation: " + abbreviation + "\n\n"));
                /*if (abbreviation.equalsIgnoreCase("F1")){
                    //races = parseF1RaceListToRaceArray();
                }else {
                    System.out.println("This is the value of the racearray: " + racesArray);
                    races = parseRaceListToRaceArray(racesArray);
                //}*/
                newRaceSeriesList.add(parseJsonRaceSeries(jsonArray.getJSONObject(i).toString()));
                 //newRaceSeriesList.add(new RaceSeries(name, url, type, description, abbreviation, seriesLogo,races));
                // System.out.println(raceSeriesObject.);
            }



        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return newRaceSeriesList;
    }

    public static RaceSeries parseJsonRaceSeries(String json) {
        RaceSeries raceSeries = TestValues.emptySeries();

        try{
            JSONObject raceSeriesObject =  new JSONObject(json);
            String name = raceSeriesObject.getString("name");
            String url = raceSeriesObject.getString("url");
            String type = raceSeriesObject.getString("type");
            String description = raceSeriesObject.getString("description");
            String abbreviation = raceSeriesObject.getString("abbreviation");
            String seriesLogo = raceSeriesObject.getString("seriesLogo");

            JSONArray racesArray = raceSeriesObject.getJSONArray("racelist");
            List<Race> races = new ArrayList<>();

            System.out.println(new String("\n\nAbbreviation: " + abbreviation + "\n\n"));
                /*if (abbreviation.equalsIgnoreCase("F1")){
                    //races = parseF1RaceListToRaceArray();
                }else {*/
            //System.out.println("This is the value of the racearray: " + racesArray);
            races = parseRaceListToRaceArray(racesArray);
            raceSeries = new RaceSeries(name, url, type, description, abbreviation, seriesLogo,races);
        }catch ( Exception e){
            System.out.println("Error converting json to raceseries");
        }

        return raceSeries;

    }


    public static List<Race> parseRaceListToRaceArray(JSONArray jsonRaceList) {
        System.out.println("\n\nParsing non-F1 Data\n\n" );
        List<Race> races = new ArrayList<>();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");//"yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        //System.out.println(jsonRaceList);
        Date date = new Date();
        Date dateTried = new Date();

        try {
            //System.out.println(("Content of race in jasonracelist: " + jsonRaceList.get(0).toString()));
            for (int i = 0; i < jsonRaceList.length(); i++) {
                JSONObject raceObject = jsonRaceList.getJSONObject(i);
                List<RaceEvent> raceEventArray = new ArrayList<>();

                //System.out.println("\n\nThis is the contents of racedate");
                //System.out.println(raceObject.getString("raceTime"));
                //try{
                    if (!raceObject.getString("raceTime").isEmpty()) {
                        //System.out.println("This is the value of raceTime: " + raceObject.getString("raceTime"));
                        //dateTried = isoFormat.parse(raceObject.getString("raceTime"));
                        ZonedDateTime dateTime = ZonedDateTime.parse(raceObject.getString("raceTime"), formatter);
                        String isoDate = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                        //System.out.println("Parsed ISO 8601 Date: " + isoDate);
                        dateTried = Date.from(dateTime.toInstant());
                        //System.out.println("This is the value of dateTried from the raceTime string: " + dateTried.toString());
                    }else{
                        System.out.println("\n\nThere was an issue accessing raceTime value");
                        System.out.println(raceObject.getString("raceTime"));
                    }
                //} //catch (ParseException e){
                   // e.printStackTrace();
                //}
                date = dateTried;


                String raceName = raceObject.getString("raceName");
                String shortName = raceObject.getString("shortName");
                String raceTrack = raceObject.getString("raceTrack");
                String circuiteName = raceObject.getString("circuit_name");
                String country = raceObject.getString("country");
                double raceLat =    Double.parseDouble(raceObject.getString("latitude"));
                double raceLon =     Double.parseDouble(raceObject.getString("longitude"));
                String trackLayoutImage =    raceObject.getString("image");
                String raceEventsString = raceObject.getString("raceEvents");

                //System.out.println("\n\n Race Name: ");
                //System.out.println(raceName);
                //System.out.println("\n\n");
               // System.out.println("Size of the events list is:");
                //System.out.println(raceObject.getJSONArray("raceEvents").length());
                for (int k=0; k < raceObject.getJSONArray("raceEvents").length();k++){

                    JSONObject  raceEventsObject = raceObject.getJSONArray("raceEvents").getJSONObject(k);
                    String eventName = raceEventsObject.getString("eventName");
                    String eventDate = raceEventsObject.getString("eventDate");
                    String eventTypeString = raceEventsObject.getString("eventType");

                    RaceEvent newRaceEvent = new RaceEvent(eventName,RaceEvent.getTypeFromString(eventTypeString),eventDate);
                    raceEventArray.add(newRaceEvent);
                }

                //System.out.println("This is the value of date just prior to creating new race in parseRaceListToRaceArray: " + date.toString());
                //raceEventArray
                races.add(new Race(raceName, shortName, date, raceTrack, circuiteName, country, raceLat, raceLon, trackLayoutImage, raceEventArray));

            }
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return races;
    }

    public List<Race> parseF1RaceListToRaceArray() {
        System.out.println("\n\nParsing F1 Data\n\n");
        //System.out.println(new String("\n\nMeeting list size\n\n" + meetingList.size()));
        List<Race> races = new ArrayList<>();
        List<RaceEvent> newRaceEvents = new ArrayList<>();
        if(!meetingList.isEmpty()) {
            System.out.println("\n\nMeetingList is not empty\n\n");
            for (Meeting meeting : meetingList) {
                //System.out.println(new String("Values returned to meeting in parseF1 method: " + meeting.getCircuitShortName()));
                Race newRace = (LiveDataConverter.convertMeetingToRace(meeting));
                races.add(newRace);
                sessionList = parseJsonSessionArray(getJsonStringUsingHTTP("https://api.openf1.org/v1/sessions?meeting_key=" + race.getMeeting_key()));

                for (Race race : races) {
                    //System.out.println(new String("Values returned to meeting in parseF1 method test races: " + race.getCircuitName()));
                    int sessionID = race.getMeeting_key();
                    sessionList = parseJsonSessionArray(getJsonStringUsingHTTP("https://api.openf1.org/v1/sessions?meeting_key=" + race.getMeeting_key()));
                    for (Session session : sessionList) {
                        //System.out.println(new String("Values of sessionlist: " + session.getMeetingKey()));
                        if (race.getMeeting_key() == session.getMeetingKey()) {
                            RaceEvent newEvent = LiveDataConverter.convertSessionToRaceEvent(session);
                            newRaceEvents.add(newEvent);
                        }
                    }
                    race.setRaceEvents(newRaceEvents);
                }
                List<RaceEvent> raceEventCheck = races.get(0).getRaceEvents();
               // System.out.println(new String("RaceEventCheck = " + raceEventCheck.get(0).getEventName()));

            }
        }else{
            System.out.println("\n\nMeetingList is empty\n\n");
            meetingList =  parseJsonMeetingArray(getJsonStringUsingHTTP("https://api.openf1.org/v1/meetings"));
            for (Meeting meeting : meetingList) {
                //System.out.println(new String("Values returned to meeting in parseF1 method: " + meeting.getCircuitShortName()));
                Race newRace = (LiveDataConverter.convertMeetingToRace(meeting));
                races.add(newRace);
                sessionList = parseJsonSessionArray(getJsonStringUsingHTTP("https://api.openf1.org/v1/sessions?meeting_key=" + race.getMeeting_key()));
                for (Race race : races) {
                    //System.out.println(new String("Values returned to meeting in parseF1 method test races: " + race.getCircuitName()));
                    int sessionID = race.getMeeting_key();
                    sessionList = parseJsonSessionArray(getJsonStringUsingHTTP("https://api.openf1.org/v1/sessions?meeting_key=" + race.getMeeting_key()));
                    for (Session session : sessionList) {
                        //System.out.println(new String("Values of sessionlist: " + session.getMeetingKey()));
                        if (race.getMeeting_key() == session.getMeetingKey()) {
                            RaceEvent newEvent = LiveDataConverter.convertSessionToRaceEvent(session);
                            newRaceEvents.add(newEvent);
                        }
                    }
                    race.setRaceEvents(newRaceEvents);
                }

            }

        }
        List<RaceEvent> raceEventCheck = races.get(0).getRaceEvents();
        //System.out.println(new String("RaceCheck = " + races.get(0).getRaceName()));
        //System.out.println(new String("RaceEventCheck = " + raceEventCheck.get(0).getEventName()));
        return races;
    }

    public List<JsonRace> parseJsonRaceArray(String json) {
        // Implement a simple JSON parser or use a library like org.json
        // This is a placeholder for the actual parsing logic
        return new ArrayList<>();
    }

    public static Race parseRace(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String raceName = jsonObject.optString("raceName");
            String shortName = jsonObject.optString("shortName");
            String raceTimeStr = jsonObject.optString("raceTime");
            String raceTrack = jsonObject.optString("raceTrack");
            String circuitName = jsonObject.optString("circuit_name");
            String country = jsonObject.optString("country");
            double latitude = jsonObject.optDouble("latitude");
            double longitude = jsonObject.optDouble("longitude");
            String image = jsonObject.optString("image");
            // Parse race time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            Date raceTime = sdf.parse(raceTimeStr); // Parse race events
            String raceEventsStr = jsonObject.optString("raceEvents");
            JSONArray raceEventsJsonArray = new JSONArray(raceEventsStr);
            List<RaceEvent> raceEvents = new ArrayList<>();
            for (int i = 0; i < raceEventsJsonArray.length(); i++) {
                JSONObject eventJson = raceEventsJsonArray.getJSONObject(i);
                String eventName = eventJson.optString("eventName");
                String eventType = eventJson.optString("eventType");
                String eventDate = eventJson.optString("eventDate");
                raceEvents.add(new RaceEvent(eventName, RaceEvent.getTypeFromString(eventType), eventDate));
            }
            return new Race(raceName, shortName, raceTime, raceTrack, circuitName, country, latitude, longitude, image, raceEvents);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Race parseJsonRace(String json) throws JSONException {
        System.out.println("\n\nParsing non-F1 Data\n\n" );
        Race race = TestValues.emptyRace();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");//"yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        //System.out.println(jsonRaceList);
        Date date = new Date();
        Date dateTried = new Date();
        //try {
            JSONObject raceObject = new JSONObject(json);
            List<RaceEvent> raceEventArray = new ArrayList<>();

            //System.out.println("\n\nThis is the contents of racedate");
            //System.out.println(raceObject.getString("raceTime"));
            //try{
            if (!raceObject.getString("raceTime").isEmpty()) {
                //System.out.println("This is the value of raceTime: " + raceObject.getString("raceTime"));
                //dateTried = isoFormat.parse(raceObject.getString("raceTime"));
                ZonedDateTime dateTime = ZonedDateTime.parse(raceObject.getString("raceTime"), formatter);
                String isoDate = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                //System.out.println("Parsed ISO 8601 Date: " + isoDate);
                dateTried = Date.from(dateTime.toInstant());
                //System.out.println("This is the value of dateTried from the raceTime string: " + dateTried.toString());
            } else {
                System.out.println("\n\nThere was an issue accessing raceTime value");
                System.out.println(raceObject.getString("raceTime"));
            }
            //} //catch (ParseException e){
            // e.printStackTrace();
            //}
            date = dateTried;


            String raceName = raceObject.getString("raceName");
            String shortName = raceObject.getString("shortName");
            String raceTrack = raceObject.getString("raceTrack");
            String circuiteName = raceObject.getString("circuit_name");
            String country = raceObject.getString("country");
            double raceLat = Double.parseDouble(raceObject.getString("latitude"));
            double raceLon = Double.parseDouble(raceObject.getString("longitude"));
            String trackLayoutImage = raceObject.getString("image");
            String raceEventsString = raceObject.getString("raceEvents");

            //System.out.println("\n\n Race Name: ");
            //System.out.println(raceName);
            //System.out.println("\n\n");
            // System.out.println("Size of the events list is:");
            //System.out.println(raceObject.getJSONArray("raceEvents").length());
            for (int k = 0; k < raceObject.getJSONArray("raceEvents").length(); k++) {

                JSONObject raceEventsObject = raceObject.getJSONArray("raceEvents").getJSONObject(k);
                String eventName = raceEventsObject.getString("eventName");
                String eventDate = raceEventsObject.getString("eventDate");
                String eventTypeString = raceEventsObject.getString("eventType");

                RaceEvent newRaceEvent = new RaceEvent(eventName, RaceEvent.getTypeFromString(eventTypeString), eventDate);
                raceEventArray.add(newRaceEvent);
            }

            //System.out.println("This is the value of date just prior to creating new race in parseRaceListToRaceArray: " + date.toString());
            //raceEventArray
            race =new Race(raceName, shortName, date, raceTrack, circuiteName, country, raceLat, raceLon, trackLayoutImage, raceEventArray);
       // }catch(Exception e){
       //     System.out.println("Error converting jsonRace to race" + json);
        //}
        return race;
    }




    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public List<Stint> getStintList() {
        return stintList;
    }

    public void setStintList(List<Stint> stintList) {
        this.stintList = stintList;
    }

    public void setDriverPositionList(List<DriverPosition> driverPositionList) {
        this.driverPositionList = driverPositionList;
    }


}





