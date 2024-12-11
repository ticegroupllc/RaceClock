package com.example.myapplication.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceSeries;
import java.util.Map;


public class PreferencesManager {
    public static final String PREFERENCE_NAME = "PREFERENCE_DATA";
    private final SharedPreferences sharedpreferences;


    public PreferencesManager(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

   public void setBoolean(String key,boolean value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean b) {
        if (sharedpreferences.getBoolean(key, false) || sharedpreferences.getBoolean(key,false) ==false) {
            return sharedpreferences.getBoolean(key, false);
        }
        return false;
    }


    ///////////////////JsonRaceSeriesString Getter/Setter////////////////////////////////////////////////
    public String getJsonRaceSeriesString() {

        return sharedpreferences.getString("raceSeries", "");
    }

    public String getJsonRaceString() {
        return sharedpreferences.getString("race", "");
    }


    ///////////////////RaceSeriesList Getter/Setter////////////////////////////////////////////////

    public void setRaceSeriesList(String encoded) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("race_series_list", encoded);
        editor.apply();
    }
    public String getRaceSeriesList() {

        return sharedpreferences.getString("race_series_list", "");
    }


    ///////////////////RaceSeries Getter/Setter////////////////////////////////////////////////
    public void setRaceSeries(String encoded) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("raceseries", encoded);
        editor.apply();
    }

    public void setRaceSeries(RaceSeries raceSeries) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("stored_race_series", HelperFunctions.convertRaceSeriestoJSONRaceSeries(raceSeries).toString());
        editor.apply();
    }

    /*/////////////////// Getter/Setter////////////////////////////////////////////////
    public String getstored_JsonRaceSeriesString() {

        return sharedpreferences.getString("stored_raceSeries", "");
    }*/

    /////////////////// Getter/Setter////////////////////////////////////////////////
    public void setRace(Race race) {
        String raceString = HelperFunctions.convertRacetoJSONRace(race).toString();
        String modifiedRaceString = raceString;//.replace("\\\"", "\"");
        System.out.println("Racestring being save ro preferences = " + modifiedRaceString);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("race",modifiedRaceString );
        editor.putString("stored_raceSeries", modifiedRaceString);
        editor.apply();
    }

    /*public (Race, boolean) getRace(){
        if (sharedpreferences.getString("stored_raceSeries", "").isEmpty();){
            return HelperFunctions.convertJsonRaceToRace(Test
        }
        return HelperFunctions.convertJsonRaceToRace(sharedpreferences.getString("stored_raceSeries", ""));
    }*/

    public String getRace(){

        return sharedpreferences.getString("stored_raceSeries", "");
    }



    /*/////////////////// Getter/Setter////////////////////////////////////////////////
    public String getStored_preference_json_race_Series() {

        return sharedpreferences.getString("stored_preference_json_race_Series", "");
    }

    public void setStored_preference_json_race_Series(String json) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("stored_preference_json_race_Series", json);
        editor.apply();
    }*/

    /*/////////////////// Getter/Setter////////////////////////////////////////////////
    public String getStored_preference_json_openF1_race_list() {

        return sharedpreferences.getString("stored_preference_json_openF1_race_list", "");
    }

    public void setStored_preference_json_openF1_race_list(String json) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("stored_preference_json_openF1_race_Series", json);
        editor.apply();
    }*/


    /////////////////// Getter/Setter////////////////////////////////////////////////
    public void setStored_preference_race_Series(String json) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("stored_preference_race_series", json);
        editor.apply();
    }
    public String getStored_preference_race_Series() {

        return sharedpreferences.getString("stored_preference_race_series", "");
    }

    /////////////////// Getter/Setter////////////////////////////////////////////////
    public String getSessionList() {
        return sharedpreferences.getString("key_session_list", "");
    }

    public void setSessionList(String json) {

        if(!json.isEmpty()) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("key_session_list", json);
            editor.apply();
            //System.out.println("Sessionlist input is not null" + json);
        }else{
            //System.out.println("Stintlist input is null");

        }
    }
    /////////////////// Getter/Setter////////////////////////////////////////////////
    public String getDriverList() {
        return sharedpreferences.getString("key_driver_list", "");
    }

    public void setDriverList(String json) {

        if(!json.isEmpty()) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("key_driver_list", json);
            editor.apply();
            //System.out.println("Driverlist input is not null" + json);
        }else{
            //System.out.println("Stintlist input is null");

        }
    }
    /////////////////// Getter/Setter////////////////////////////////////////////////
    public String getStintList() {
        return sharedpreferences.getString("key_stint_list", "");
    }

    public void setStintList(String json) {
        if(!json.isEmpty()) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("key_stint_list", json);
            editor.apply();

            System.out.println("Stintlist input is not null" + json);
        }else{
            System.out.println("Stintlist input is null");

        }
    }
    /////////////////// Getter/Setter////////////////////////////////////////////////
    public String getDriverPositionList() {
        return sharedpreferences.getString("key_driver_position_list", "");
    }

    public void setDriverPositionList(String json) {

        if(!json.isEmpty()) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("key_driver_position_list", json);
            editor.apply();

            System.out.println("Meetinglist input is not null" + json);
        }else{
            System.out.println("Stintlist input is null");

        }


    }
    /////////////////// Getter/Setter////////////////////////////////////////////////
    public String getMeetingList() {
        return sharedpreferences.getString("key_meeting_list", "");
    }

    public void setMeetingList(String json) {

        if(!json.isEmpty()) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("key_meeting_list", json);
            editor.apply();

            System.out.println("Meetinglist input is not null" + json);
        }else{
            System.out.println("Stintlist input is null");

        }
    }
    /////////////////// Getter/Setter////////////////////////////////////////////////
    /*public void setImage(Uri selectedImageUri) {
        if(!selectedImageUri.toString().isEmpty()) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("key_", selectedImageUri);
            editor.apply();

            System.out.println("Meetinglist input is not null" + json);
        }else{
            System.out.println("Stintlist input is null");

        }
    }
    public void getImage(Uri selectedImageUri) {
    }*/

}
/////////////////// Getter/Setter////////////////////////////////////////////////