package com.example.myappvexe.Customer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.myappvexe.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomerArrayAdapter {
    //Kết nối SQLite
    private final SQLiteHelper dbHelper;
    private SQLiteDatabase database;
    private final String[] allColumsCustomer = {SQLiteHelper.ID_COL, SQLiteHelper.Name_COL,SQLiteHelper.userName_COL,SQLiteHelper.passWord_COL, SQLiteHelper.email_COL, SQLiteHelper.gender_COL,
                                            SQLiteHelper.dateOfBirth_COL,SQLiteHelper.phone_COL};

    CustomerArrayAdapter(Context context){
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLiteException{
        database = dbHelper.getWritableDatabase();
    }

    public void close() {dbHelper.close();}


    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<Customer>();
        Cursor cursor = database.query(SQLiteHelper.TABLE_NAME,allColumsCustomer, null,null,null, null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Customer name = cursorToCustomer(cursor);
            customers.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return customers;
    }


    private Customer cursorToCustomer(Cursor cursor){
        Customer customer = new Customer();
        customer.setId(cursor.getLong(0));
        customer.setName(cursor.getString(1));
        customer.setUsername(cursor.getString(2));
        customer.setPassword(cursor.getString(3));
        customer.setEmail(cursor.getString(4));
        customer.setGender(cursor.getString(5));
        customer.setDayofbidth(cursor.getString(6));
        customer.setPhone(cursor.getString(7));
        return customer;
    }

}
