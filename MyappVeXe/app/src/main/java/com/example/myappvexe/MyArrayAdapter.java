package com.example.myappvexe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends  BackActivity{
    //Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColums = { SQLiteHelper.ID_STAFF_COL,SQLiteHelper.NameSTAFF_COL,SQLiteHelper.userSTAFF_COL, SQLiteHelper.passWordSTAFF_COL, SQLiteHelper.emailSTAFF_COL,
                                    SQLiteHelper.phoneSTAFF_COL};



    MyArrayAdapter(Context context){

        dbHelper = new SQLiteHelper(context);
    }

    public  void open() throws SQLiteException{
        database = dbHelper.getWritableDatabase();
    }

    public  void close(){
        dbHelper.close();
    }

    public  Staff createStaff (String name){
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.NameSTAFF_COL, name);
        long inserID = database.insert(SQLiteHelper.TABLE_STAFF, null, values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_STAFF, allColums, SQLiteHelper.ID_STAFF_COL +
                " = " + inserID, null, null, null, null);
        cursor.moveToFirst();
        Staff newStaff = cursorToStaff(cursor);
        cursor.close();
        return newStaff;
    }

    public void deleteStaff(Staff name){
        long id = name.getId();
        System.out.println("Coment deleted withd id: " + id);
        database.delete(SQLiteHelper.TABLE_STAFF, SQLiteHelper.ID_STAFF_COL + " = " + id, null);

    }

    public List<Staff> getAllStaffs(){
        List<Staff> staffs = new ArrayList<Staff>();
        Cursor cursor = database.query(SQLiteHelper.TABLE_STAFF, allColums,null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Staff name  = cursorToStaff(cursor);
            staffs.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return staffs;
    }

    private Staff cursorToStaff(Cursor cursor){
        Staff staff = new Staff();
        staff.setId(cursor.getLong(0));
        staff.setName(cursor.getString(1));
        staff.setUsername(cursor.getString(2));
        staff.setPassword(cursor.getString(3));
        staff.setEmail(cursor.getString(4));
        staff.setPhone(cursor.getString(5));
        return staff;
    }
}
