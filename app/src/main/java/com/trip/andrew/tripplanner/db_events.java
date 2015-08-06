package com.trip.andrew.tripplanner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 06.08.2015.
 */
public class db_events extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_trip_planner";

    // Labels table name
    private static final String TABLE_MUSEUMS = "museums";

    // Labels Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE_START = "date_start";
    private static final String KEY_DATE_END = "date_end";
    private static final String KEY_ICON_NAME = "icon_name";

    public db_events(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
        //onCreate(db);
        //db.close();
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_MUSEUMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, " + KEY_DATE_START + " LONG, " + KEY_DATE_END + " LONG, " + KEY_ICON_NAME + " TEXT)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSEUMS);

        // Create tables again
        onCreate(db);
    }

    public List<Event> getAllMuseums()
    {
        List<Event> events=new ArrayList<Event>();
        SQLiteDatabase db=this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_MUSEUMS;
        Cursor cursor=db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Museum museum=new Museum();
                museum.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                museum.setStartDate(cursor.getLong(cursor.getColumnIndex(KEY_DATE_START)));
                museum.setEndDate(cursor.getLong(cursor.getColumnIndex(KEY_DATE_END)));
                museum.setIconName(cursor.getString(cursor.getColumnIndex(KEY_ICON_NAME)));
                events.add(museum);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return events;
    }
}