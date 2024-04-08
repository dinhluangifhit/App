package com.example.myappvexe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LisAdapterAcitivity extends ArrayAdapter<Staff> {
    private List<Staff> staffList;
    private LayoutInflater inflater;
    //Staff
    public LisAdapterAcitivity (Context context, List<Staff> staffList){
        super(context, R.layout.listview_item, staffList);
        this.staffList = staffList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.listview_item, parent, false);
        }
        Staff staff = staffList.get(position);

        TextView TexViewName = view.findViewById(R.id.textViewName);
        TextView TexUserName = view.findViewById(R.id.textUserName);
        TexViewName.setText(staff.getName());
        TexUserName.setText(staff.getUsername());
        return view;
    }
}
