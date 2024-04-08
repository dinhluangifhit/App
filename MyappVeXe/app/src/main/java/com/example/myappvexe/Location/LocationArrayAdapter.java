package com.example.myappvexe.Location;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.myappvexe.Location.Location;
import com.example.myappvexe.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class LocationArrayAdapter {
    //Kết nối với SQLite
    private SQLiteHelper dbHelper;
    private SQLiteDatabase database;
    private String[] alcolumLocations = {SQLiteHelper.ID_LOCATION_COL, SQLiteHelper.NameLocation_COL, SQLiteHelper.BusStation_COL};

    LocationArrayAdapter(Context context) {dbHelper = new SQLiteHelper(context);}
    public void open() throws SQLiteException{
        database = dbHelper.getWritableDatabase();
    }

    public void close() {dbHelper.close();}

    public List<Location> getallLocations(){
        List<Location> locations = new ArrayList<Location>();
        Cursor cursor = database.query(SQLiteHelper.TABLE_LOCATION, alcolumLocations, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Location nameLocation = cusortoLocation(cursor);
            locations.add(nameLocation);
            cursor.moveToNext();
        }
        cursor.close();
        return locations;
    }
    private Location cusortoLocation (Cursor cursor){
        Location location = new Location();
        location.setId(cursor.getLong(0));
        location.setNameLocation(cursor.getString(1));
        location.setBusStation(cursor.getString(2));
        return location;
    }
}
