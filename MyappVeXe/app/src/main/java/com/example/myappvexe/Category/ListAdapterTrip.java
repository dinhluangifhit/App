package com.example.myappvexe.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myappvexe.Customer.Customer;
import com.example.myappvexe.R;

import java.util.List;

public class ListAdapterTrip extends ArrayAdapter<Trip> {
    private List<Trip> tripList;
    private LayoutInflater inflater;
    //Staff
    public ListAdapterTrip(Context context, List<Trip> tripList){
        super(context, R.layout.listview_item,tripList);
        this.tripList = tripList;
        this.inflater = LayoutInflater.from(context);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.listview_item, parent, false);
        }
        Trip trip = tripList.get(position);

        TextView TexViewName = view.findViewById(R.id.textViewName);
        TextView TexUserName = view.findViewById(R.id.textUserName);
        String name1 = trip.getLocationStar();
        String name2 = trip.getLocationEnd();
        String name = name1 + " - " + name2;
        TexViewName.setText(name);
        TexUserName.setText(trip.getDateBusStar());
        return view;
    }
}
