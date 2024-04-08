package com.example.myappvexe.Category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myappvexe.R;

import java.util.List;

public class ListShowAdapterTrip extends ArrayAdapter<Trip> {
    private final List<Trip> tripList;
    private final LayoutInflater inflater;
    //Staff
    public ListShowAdapterTrip(Context context, List<Trip> tripList){
        super(context, R.layout.listview_ticket,tripList);
        this.tripList = tripList;
        this.inflater = LayoutInflater.from(context);

    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.listview_ticket, parent, false);
        }
        Trip trip = tripList.get(position);

        TextView TimeStar = view.findViewById(R.id.time_start);
        TextView TimeEnd = view.findViewById(R.id.time_end);
        TextView TxtVLocationStar = view.findViewById(R.id.textViewLocationStar);
        TextView TxtVLocationEnd = view.findViewById(R.id.textViewLocationEnd);
        TextView TextPrce = view.findViewById(R.id.textViewPrice);
        TextView TextSeat = view.findViewById(R.id.textViewSeat);
        TextView Distance = view.findViewById(R.id.distance);
        TextView TextViewDateStar = view.findViewById(R.id.textViewDateStar);


        TimeStar.setText(trip.getTimeBusStar());
        Distance.setText(trip.getTimeEnd() + "h");
        TimeEnd.setText(trip.getTimeBusEnd());
        TxtVLocationStar.setText(trip.getLocationStar());
        TxtVLocationEnd.setText(trip.getLocationEnd());
        TextPrce.setText(trip.getPriceTrip() + " VNĐ");
        TextSeat.setText(trip.getSeats() + " chỗ trống");
        TextViewDateStar.setText(trip.getDateBusStar());

        return view;
    }

    public void opent() {
    }
}
