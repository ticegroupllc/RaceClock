package com.example.myapplication.Utilities;


//import android.content.Context;
import android.content.Context;
//import android.content.Context;
import java.util.Date;

import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceSeries;
import com.example.myapplication.placeholder.MyApplication;

//import com.example.myapplication.placeholder.MyApplication;


public class EntitlementManager {
        PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());


       //private static final Preferences userDefaults = Preferences.userRoot().node("/group/com/ticegroupllc/raceday");

        private boolean hasPro = true;
        private Date purchaseDate = new Date();
        private Date expirationDate = new Date();
        private boolean raceDataIsSet = false;
        private boolean raceSeriesIsSet = false;
        //private JsonRaceSeriesUserSettings storedJsonRaceSeriesUserSettings = new JsonRaceSeriesUserSettings("", "", "F1", "", "", "", new JsonRace[] {});
        //private JsonRace userSettingsRace = new JsonRace("", "", "", "", "", "", 0, 0, "", new JsonRaceEvent[] { new JsonRaceEvent("Practice 1", "Practice", "2024-01-01T16:00:00Z") });
        private boolean showRaceTimes = true;
        private boolean showPracticeTimes = false;
        private boolean showQualifyingTimes = false;
        private boolean showSprintTimes = false;

       public  EntitlementManager() {
          Context context = MyApplication.getAppContext();
            preferences = new PreferencesManager(MyApplication.context);
            PreferencesManager preferences = new PreferencesManager(MyApplication.context);
            //this.purchaseDate = new Date(preferences.getLong("PurchaseDate", new Date().getTime()));
            //this.expirationDate = new Date(preferences.getLong("ExpirationDate", new Date().getTime()));

            // Deserialize JSON objects
            String raceSeriesJson = preferences.getJsonRaceSeriesString();
            if (raceSeriesJson != null) {
                //this.storedJsonRaceSeriesUserSettings = HelperFunctions.dataToJsonSeriesConverter(raceSeriesJson);
                this.raceSeriesIsSet = true;
            }

            String raceJson = preferences.getJsonRaceString();
            if (raceJson != null) {
                //this.userSettingsRace = HelperFunctions.dataToJsonRaceConverter(raceJson);
                this.raceDataIsSet = true;
            }

            this.showRaceTimes = preferences.getBoolean("ShowRaceTimes", true);
            this.showPracticeTimes = preferences.getBoolean("ShowPracticeTimes", false);
            this.showQualifyingTimes = preferences.getBoolean("ShowQualifyingTimes", false);
            this.showSprintTimes = preferences.getBoolean("ShowSprintTimes", false);


        }

        public void reInit() {
            // Re-initialize logic if necessary
        }

        public void setShowRaceTimes(boolean value1) {
            preferences.setBoolean("ShowRaceTimes", value1);
        }

        public void setShowPracticeTimes(boolean value) {
            preferences.setBoolean("ShowPracticeTimes", value);
        }

        public void setShowQualifyingTimes(boolean value) {
            preferences.setBoolean("ShowQualifyingTimes", value);
        }

        public void setShowSprintTimes(boolean value) {
            preferences.setBoolean("ShowSprintTimes", value);
        }

        /*public void setPurchaseTrue(String productID, Date productPurchaseDate, Date productExpirationDate) {
            preferences.setBoolean(productID, true);
            preferences.setDate("PurchaseDate", productPurchaseDate);
            preferences.setDate("ExpirationDate", productExpirationDate);
        }

        public void setPurchaseFalse(String productID) {
            preferences.setBoolean(productID, false);
        }*/

        public void setRacingSeries(RaceSeries raceSeries) {

            //preferences.setRaceSeries(HelperFunctions.convertRaceSeriestoJSONRaceSeries(raceSeries).toString());
            preferences.setBoolean("raceSeriesIsSet", true);
            preferences.setBoolean("raceDataIsSet", false);
        }

        public void setRace(Race race) {

            preferences.setRace(race);
            preferences.setBoolean("raceDataIsSet", true);
        }

}
