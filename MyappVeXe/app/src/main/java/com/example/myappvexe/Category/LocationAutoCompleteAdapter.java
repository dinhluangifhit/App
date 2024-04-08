package com.example.myappvexe.Category;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class LocationAutoCompleteAdapter extends CursorAdapter implements Filterable {

    private final SQLiteDatabase db;

    public LocationAutoCompleteAdapter(Context context, Cursor c, SQLiteDatabase db) {
        super(context, c, false);
        this.db = db;
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        if (constraint == null) {
            return null;
        }
        String query = constraint.toString().toLowerCase();
        return db.rawQuery("SELECT id AS _id, Name FROM location WHERE Name LIKE ?", new String[]{"%" + query + "%"});
    }

    @Override
    public void bindView(android.view.View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view;
        textView.setText(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return  inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);

    }

    @Override
    public CharSequence convertToString(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow("Name"));
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint != null) {
                    Cursor cursor = runQueryOnBackgroundThread(constraint);
                    if (cursor != null) {
                        results.values = cursor;
                        results.count = cursor.getCount();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    swapCursor((Cursor) results.values);
                } else {
                    swapCursor(null);
                }
            }
        };
    }
}
