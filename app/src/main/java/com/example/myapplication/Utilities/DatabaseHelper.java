package com.example.myapplication.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplication.TypeClasses.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "race_series.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_RACE_SERIES = "race_series";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_ABBREVIATION = "abbreviation";
    private static final String COLUMN_SERIES_LOGO = "seriesLogo";

    // SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_RACE_SERIES + " (" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_URL + " TEXT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_ABBREVIATION + " TEXT, " +
                    COLUMN_SERIES_LOGO + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RACE_SERIES);
        onCreate(db);
    }





        // Method to insert RaceSeries data
        public void addRaceSeries(RaceSeries raceSeries) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, raceSeries.getId().toString());
            values.put(COLUMN_NAME, raceSeries.getName());
            values.put(COLUMN_URL, raceSeries.getUrl());
            values.put(COLUMN_TYPE, raceSeries.getType());
            values.put(COLUMN_DESCRIPTION, raceSeries.getDescription());
            values.put(COLUMN_ABBREVIATION, raceSeries.getAbbreviation());
            values.put(COLUMN_SERIES_LOGO, raceSeries.getSeriesLogo());

            db.insert(TABLE_RACE_SERIES, null, values);
            db.close();
        }
    }



