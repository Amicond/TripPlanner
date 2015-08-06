package com.trip.andrew.tripplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 03.08.2015.
 */
public class db_trip extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_trip_planner";

    // Labels table name
    private static final String TABLE_TRIPS = "trips";

    // Labels Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE_START = "date_start";
    private static final String KEY_DATE_END = "date_end";
    private static final String KEY_ICON_NAME = "icon_name";

    public db_trip(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
        //onCreate(db);
        //db.close();
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_TRIPS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, "+KEY_DATE_START+ " LONG, "+KEY_DATE_END + " LONG, " + KEY_ICON_NAME + " TEXT)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);

        // Create tables again
        onCreate(db);
    }

    /**
     * Inserting new lable into lables table
     * */
    public void insertTrip(Trip trip){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, trip.getName());
        values.put(KEY_DATE_START, trip.getStartDate());
        values.put(KEY_DATE_END, trip.getEndDate());
        values.put(KEY_ICON_NAME, trip.getIconName());

        // Inserting Row
        db.insert(TABLE_TRIPS, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<Trip> getAllTrips(){
        List<Trip> trips = new ArrayList<Trip>();
        //Trip cur_trip=new Trip();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRIPS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Trip cur_trip=new Trip();
                cur_trip.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                cur_trip.setStartDate(cursor.getLong(cursor.getColumnIndex(KEY_DATE_START)));
                cur_trip.setEndDate(cursor.getLong(cursor.getColumnIndex(KEY_DATE_END)));
                cur_trip.setIconName(cursor.getString(cursor.getColumnIndex(KEY_ICON_NAME)));
                trips.add(cur_trip);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return trips;
    }


}
