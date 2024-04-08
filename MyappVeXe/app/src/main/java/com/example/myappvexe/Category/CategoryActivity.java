package com.example.myappvexe.Category;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myappvexe.AdminActivity;
import com.example.myappvexe.R;
import com.example.myappvexe.SQLiteHelper;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private EditText SearchTripList;
    private TextView TxtAddTrip, TxtBackHomeAdmin;
    private ListAdapterTrip adapterTrip;
    private SwipeRefreshLayout SwipeRefreshLayout;
    private SQLiteHelper sqLiteHelper;
    private TripArrayAdapter adapter;
    private ListView ListShowTrip;

    public CategoryActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        TxtAddTrip = findViewById(R.id.txtAddTrip);
        ListShowTrip = findViewById(R.id.listShowTrip);
        TxtBackHomeAdmin = findViewById(R.id.txtBackHomeAdmin);


        adapter = new TripArrayAdapter(this);
        adapter.opent();


        List<Trip> tripList =  adapter.getAllTrips();
        //Hiển thị danh sách khách hàng
        ListAdapterTrip adapter = new ListAdapterTrip(this, tripList);
        ListShowTrip.setAdapter(adapter);

        SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        loadTripList();
        //Load lại danh sach khi admin thay dổi nhân viên
        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTripList();
                SwipeRefreshLayout.setRefreshing(false);
            }
        });

        ListShowTrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trip selectedTrip = (Trip) parent.getItemAtPosition(position);

                Intent intent = new Intent(CategoryActivity.this, DetailTripActivity.class);

                intent.putExtra("selectedTrip", selectedTrip);
                startActivity(intent);
                finish();
            }
        });

        TxtBackHomeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(CategoryActivity.this, AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TxtAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, AddTripActivity.class);
                startActivity(intent);
                finish();
            }
        });
        
    }

    private  void loadTripList(){
        adapter = new TripArrayAdapter(this);
        adapter.opent();

        List<Trip> loadList = adapter.getAllTrips();
        ListAdapterTrip adapter = new ListAdapterTrip(this, loadList);
        ListShowTrip.setAdapter(adapter);
    }
}