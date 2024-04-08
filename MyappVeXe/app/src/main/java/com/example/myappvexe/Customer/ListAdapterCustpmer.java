package com.example.myappvexe.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myappvexe.R;

import java.util.List;

public class ListAdapterCustpmer extends ArrayAdapter<Customer> {
    private final List<Customer> customerList;
    private final LayoutInflater inflater;
    //Staff
   public ListAdapterCustpmer(Context context, List<Customer> customerList){
       super(context, R.layout.listview_item,customerList);
       this.customerList = customerList;
       this.inflater = LayoutInflater.from(context);

   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.listview_item, parent, false);
        }
        Customer customer = customerList.get(position);

        TextView TexViewName = view.findViewById(R.id.textViewName);
        TextView TexUserName = view.findViewById(R.id.textUserName);
        TexViewName.setText(customer.getName());
        TexUserName.setText(customer.getUsername());
        return view;
    }
}
