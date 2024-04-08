package com.example.myappvexe.Location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myappvexe.AdminActivity;
import com.example.myappvexe.R;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private TextView TxtAddLocation, TxtBackHomeAdmin;
    private LocationArrayAdapter adapter;
    private ListView ListShowLocation;
    private EditText SearchLocationList;
    private SwipeRefreshLayout SwipeRefreshLayout;
    public void setSearchStaffList(EditText searchLocationList) {
        SearchLocationList = searchLocationList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ListShowLocation = findViewById(R.id.listShowLocation);
        SearchLocationList = findViewById(R.id.searchLocationList);
        TxtAddLocation = findViewById(R.id.txtAddLocation);
        TxtBackHomeAdmin = findViewById(R.id.txtBackHomeAdmin);

        adapter = new LocationArrayAdapter(this);
        adapter.open();


        List<Location> locationList = adapter.getallLocations();
        //Hiển thị danh sách các điểm đến
        ListAdapterLocation adapter = new ListAdapterLocation(this, locationList);
        ListShowLocation.setAdapter(adapter);
        SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        loadLocationList();

        //Load lại danh sách khi người dùng thay đổi thông tin
        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLocationList();
                SwipeRefreshLayout.setRefreshing(false);
            }
        });
        TxtBackHomeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this, AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TxtAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this, AddLocationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Tìm kiếm
        SearchLocationList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    //Lọc danh sách dựa trên từ khóa tên của Tỉnh thành
    private  void filter(String text){
        List<Location> filterLocationList = new ArrayList<>();
        for (Location location : adapter.getallLocations()){
            if(location.getNameLocation().toLowerCase().contains(text.toLowerCase())){
                filterLocationList.add(location);
            }
        }
        ListAdapterLocation adapter = new ListAdapterLocation(this, filterLocationList);
        ListShowLocation.setAdapter(adapter);
    }

    private void loadLocationList(){
        adapter = new LocationArrayAdapter(this);
        adapter.open();
        List<Location> loadList = adapter.getallLocations();
        ListAdapterLocation adapter = new ListAdapterLocation(this, loadList);
        ListShowLocation.setAdapter(adapter);
    }
}