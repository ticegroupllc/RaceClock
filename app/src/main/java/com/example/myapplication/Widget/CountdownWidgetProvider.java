package com.example.myapplication.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceEvent;
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.PreferencesManager;
import com.example.myapplication.Utilities.TestValues;
import com.example.myapplication.placeholder.MyApplication;

import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownWidgetProvider extends AppWidgetProvider {
    private static Timer timer;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static final long INTERVAL = 1000; // Update every second
    private long timeLeftInMillis;
    int days , hours, minutes, seconds;
    private TextView widget_textView_race_day;
    private TextView widget_textView_race_hour;
    private TextView widget_textView_race_min;
    private TextView widget_textView_race_sec;
    PreferencesManager preferences = new PreferencesManager(MyApplication.getAppContext());
    RemoteViews views;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Start the timer
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    handler.post(() -> updateWidgets(context, appWidgetManager, appWidgetIds));
                }
            }, 1, INTERVAL);
        }

        seconds = seconds -1;
        updateTextViews();
    }

    private void updateWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {

            // Update the countdown
            //RemoteViews
            views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            //setRemoteAdapter(context, views);
            long currentTime = System.currentTimeMillis();
            long targetTime = System.currentTimeMillis() + 86400000; // 24 hours from now
// Set your target time here in milliseconds
            /*long countdown = targetTime - currentTime;
            int seconds = (int) (countdown / 1000) % 60;
            int minutes = (int) ((countdown / (1000 * 60)) % 60);
            int hours = (int) ((countdown / (1000 * 60 * 60)) % 24);
            int days = (int) (countdown / (1000 * 60 * 60 * 24));

            String countdownText = days + "d " + hours + "h " + minutes + "m " + seconds + "s";
            views.setTextViewText(R.id.widget_countdown_text, countdownText);*/

            updateTextViews();

            // Update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }

    @Override
    public void onDisabled(Context context) {
        // Cancel the timer when the widget is disabled
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void startCountdown() {
        // textViewTimer.setText("Time's up!");
        CountDownTimer countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                //updateCountdownText();
            }

            @Override
            public void onFinish() {
                // textViewTimer.setText("Time's up!");
            }
        }.start();
    }

    private void updateTextViews(){
        int days = (int) (timeLeftInMillis / 1000) / (3600*24);
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        if(preferences != null) {
            System.out.println("\nPreferences is not null on startpage in On resume\n");
            System.out.println(preferences.getRace());
            if(preferences.getRace() != null && !preferences.getRace().isEmpty()) {
                Race race = HelperFunctions.convertJsonRaceToRace(preferences.getRace());
                RaceEvent raceEvent = null;
                raceEvent = HelperFunctions.getNextRaceEvent2(race);

                views.setTextViewText(R.id.widget_textViewCountdownTitle, race.getRaceName());
                views.setTextViewText(R.id.widget_textViewEventName, raceEvent.getEventName());
                views.setTextViewText(R.id.textViewEventDate, raceEvent.getEventName());
                views.setTextViewText(R.id.widget_textView_race_hour, String.valueOf(hours));
                views.setTextViewText(R.id.widget_textView_race_min, String.valueOf(minutes));
                views.setTextViewText(R.id.widget_textView_race_sec, String.valueOf(seconds));
                views.setTextViewText(R.id.widget_textView_race_day, String.valueOf(days));
            }
        }
    }




}
