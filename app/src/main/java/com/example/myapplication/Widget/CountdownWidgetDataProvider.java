package com.example.myapplication.Widget;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.myapplication.R;
import com.example.myapplication.TypeClasses.Race;
import com.example.myapplication.TypeClasses.RaceEvent;
import com.example.myapplication.Utilities.HelperFunctions;
import com.example.myapplication.Utilities.PreferencesManager;
import com.example.myapplication.placeholder.MyApplication;

public class CountdownWidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private Cursor cursor;
    private Intent intent;
    PreferencesManager preferences  = new PreferencesManager(MyApplication.getAppContext());
    int days , hours, minutes, seconds;

    //For obtaining the activity's context and intent
    public CountdownWidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    private void initCursor(){
        if (cursor != null) {
            cursor.close();
        }
        final long identityToken = Binder.clearCallingIdentity();
        /**This is done because the widget runs as a separate thread
         when compared to the current app and hence the app's data won't be accessible to it
         because I'm using a content provided **/
        /*cursor = context.getContentResolver().query(preferences.getRace(),
                new String[]{HelperFunctions.convertJsonRaceToRace(preferences.getRace()).getRaceName(), HelperFunctions.convertJsonRaceToRace(preferences.getRace()).getRaceDate().toString(),HelperFunctions.convertJsonRaceToRace(preferences.getRace()).getCountry(),
                        HelperFunctions.convertJsonRaceToRace(preferences.getRace()).getCountry(), HelperFunctions.convertJsonRaceToRace(preferences.getRace()).getTrackLayoutImage(), HelperFunctions.convertJsonRaceToRace(preferences.getRace()).getRaceTrack()},
                HelperFunctions.convertJsonRaceToRace(preferences.getRace()).getCircuitName(),
                new String[]{"1"},null);*/
        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onCreate() {
        initCursor();
        if (cursor != null) {
            cursor.moveToFirst();
        }
    }

    @Override
    public void onDataSetChanged() {
        /** Listen for data changes and initialize the cursor again **/
        initCursor();
    }

    @Override
    public void onDestroy() {
        cursor.close();
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        /** Populate your widget's single list item **/
        //RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.list_item_quote);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        if (preferences.getRace() != null && !preferences.getRace().isEmpty()) {
            Race race = HelperFunctions.convertJsonRaceToRace(preferences.getRace());
            RaceEvent raceEvent = null;
            raceEvent = HelperFunctions.getNextRaceEvent(race);
            cursor.moveToPosition(i);
            views.setTextViewText(R.id.countdownTextViewCountdownTitle, race.getRaceName());
            views.setTextViewText(R.id.countdownTextViewEventName, raceEvent.getEventName());
            views.setTextViewText(R.id.widget_textView_race_hour, String.valueOf(hours));
            views.setTextViewText(R.id.widget_textView_race_min, String.valueOf(minutes));
            views.setTextViewText(R.id.widget_textView_race_sec, String.valueOf(seconds));
            views.setTextViewText(R.id.widget_textView_race_day, String.valueOf(days));
        }



        /*if (cursor.getString(cursor.getColumnIndex(QuoteColumns.ISUP)).equals("1")) {
            remoteViews.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_green);
        } else {
            remoteViews.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_red);
        }*/
        //return remoteViews;
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
