package com.example.myapplication.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.PreferencesManager;
import com.example.myapplication.placeholder.MyApplication;
import com.example.myapplication.TypeClasses.*;


import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of App Widget functionality.
 */
public class CountdownAppWidget extends AppWidgetProvider {
    private static Timer timer;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static final long INTERVAL = 1000; // Update every second
    private long timeLeftInMillis  = 840000;;
    int days , hours, minutes, seconds;
    private TextView widget_textView_race_day;
    private TextView widget_textView_race_hour;
    private TextView widget_textView_race_min;
    private TextView widget_textView_race_sec;
    PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
    SharedViewModel sharedViewModel = new SharedViewModel();
    LocalDateTime eventDate = LocalDateTime.now();
    //RemoteViews views;
    int widgetIDStored;
    RemoteViews views;

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        System.out.println("\n\nWidget is running\n\n");
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        //RemoteViews
                views = new RemoteViews(context.getPackageName(), R.layout.countdown_app_widget);
        //timeLeftInMillis = 840000;

        //Race race = null;

        if(preferences != null) {
            System.out.println("\nPreferences is not null on startpage in On resume\n");
            System.out.println(preferences.getRace());
            if (preferences.getRace() != null && !preferences.getRace().isEmpty()) {
                Race race = HelperFunctions.convertJsonRaceToRace(preferences.getRace());
                RaceEvent raceEvent = null;
                raceEvent = HelperFunctions.getNextRaceEvent(race);

                views.setTextViewText(R.id.widget_textViewCountdownTitle, race.getRaceName());
                views.setTextViewText(R.id.widget_textViewEventName, raceEvent.getEventName());
                views.setTextViewText(R.id.widget_textView_race_hour, String.valueOf(hours));
                views.setTextViewText(R.id.widget_textView_race_min, String.valueOf(minutes));
                views.setTextViewText(R.id.widget_textView_race_sec, String.valueOf(seconds));
                views.setTextViewText(R.id.widget_textView_race_day, String.valueOf(days));


                int days = (int) (timeLeftInMillis / 1000) / (3600 * 24);
                int hours = (int) (timeLeftInMillis / 1000) / 3600;
                int minutes = (int) (timeLeftInMillis / 1000) / 60;
                int seconds = (int) (timeLeftInMillis / 1000) % 60;

                views.setTextViewText(R.id.widget_textViewCountdownTitle, race.getRaceName());
                views.setTextViewText(R.id.widget_textViewEventName, raceEvent.getEventName());
                views.setTextViewText(R.id.widget_textViewEventName, raceEvent.getEventDate());
                views.setTextViewText(R.id.widget_textView_race_hour, String.valueOf(hours));
                views.setTextViewText(R.id.widget_textView_race_min, String.valueOf(minutes));
                views.setTextViewText(R.id.widget_textView_race_sec, String.valueOf(seconds));
                views.setTextViewText(R.id.widget_textView_race_day, String.valueOf(days));
            }
        }
        widgetIDStored = appWidgetId;
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        // Start the timer
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    handler.post(() -> updateAppWidget(context, appWidgetManager,widgetIDStored));
                }
            }, 1, INTERVAL);
        }

        seconds = seconds -1;
        int days = (int) (timeLeftInMillis / 1000) / (3600*24);
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        views.setTextViewText(R.id.widget_textViewCountdownTitle, "");
        views.setTextViewText(R.id.widget_textViewEventName, "");
        views.setTextViewText(R.id.widget_textView_race_hour, String.valueOf(hours));
        views.setTextViewText(R.id.widget_textView_race_min, String.valueOf(minutes));
        views.setTextViewText(R.id.widget_textView_race_sec, String.valueOf(seconds));
        views.setTextViewText(R.id.widget_textView_race_day, String.valueOf(days));

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}