package com.example.myappvexe.Category;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myappvexe.Customer.Customer;
import com.example.myappvexe.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class TripArrayAdapter {
    //Kết nối SQLite
    private final SQLiteHelper dbHelper;
    private SQLiteDatabase database;
    private final String[] allComlumsTrip = {SQLiteHelper.ID_TRIP_COL, SQLiteHelper.tripLocationStar_COL,
            SQLiteHelper.tripLocationEnd_COL,SQLiteHelper.tripDate_COL,
            SQLiteHelper.tripTimeStar_COL,SQLiteHelper.tripTimeEnd_COL, SQLiteHelper.tripPrice_COL,
            SQLiteHelper.seats_COL, SQLiteHelper.intendTime};

    public TripArrayAdapter(Context context) {dbHelper = new SQLiteHelper(context);}

    public void opent() throws SecurityException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){dbHelper.close();}

    public List<Trip> getAllTrips(){
        List<Trip> trips = new ArrayList<Trip>();
        Cursor cursor = database.query(SQLiteHelper.TABLE_TRIP,allComlumsTrip, null,null,null, null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Trip name = cursorToTrip(cursor);
            trips.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return trips;
    }


    private Trip cursorToTrip(Cursor cursor){
        Trip trip = new Trip();
        trip.setId(cursor.getLong(0));
        trip.setLocationStar(cursor.getString(1));
        trip.setLocationEnd(cursor.getString(2));
        trip.setDateBusStar(cursor.getString(3));
        trip.setTimeBusStar(cursor.getString(4));
        trip.setTimeBusEnd(cursor.getString(5));
        trip.setPriceTrip(cursor.getString(6));
        trip.setSeats(cursor.getString(7));
        trip.setTimeEnd(cursor.getString(8));

        return trip;
    }
}
