package com.example.myappvexe.Location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myappvexe.Location.Location;
import com.example.myappvexe.R;

import java.util.List;

public class ListAdapterLocation extends ArrayAdapter<Location> {
    private List<Location> locationList;
    private LayoutInflater inflaterl;

    public ListAdapterLocation(Context context, List<Location> locationList){
        super(context, R.layout.listview_item, locationList);
        this.locationList = locationList;
        this.inflaterl = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent){
        View view = converView;
        if(view == null){
            view = inflaterl.inflate(R.layout.listview_item, parent, false);
        }
        Location location = locationList.get(position);
        TextView TexViewName = view.findViewById(R.id.textViewName);
        TextView TexUserName = view.findViewById(R.id.textUserName);
        TexViewName.setText(location.getNameLocation());
        TexUserName.setText(location.getBusStation());
        return view;
    }
}
