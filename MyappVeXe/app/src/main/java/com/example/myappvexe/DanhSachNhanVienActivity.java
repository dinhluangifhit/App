package com.example.myappvexe;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class DanhSachNhanVienActivity extends AppCompatActivity {
    TextView TxtAddStaff, TxtBackHomeAdmin;
    ListView ListShowStaff;
    private  MyArrayAdapter adapter;
    EditText SearchStaffList;
    private SwipeRefreshLayout SwipeRefreshLayout;

    SQLiteHelper staffSQLiteHelper;



    public void setSearchStaffList(EditText searchStaffList) {
        SearchStaffList = searchStaffList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nhan_vien);

        TxtAddStaff = (TextView) findViewById(R.id.txtAddStaff);
        TxtBackHomeAdmin = (TextView) findViewById(R.id.txtBackHomeAdmin);
        ListShowStaff = (ListView) findViewById(R.id.listShowStaff);
        SearchStaffList = (EditText) findViewById(R.id.searchStaffList);

        adapter = new MyArrayAdapter(this);
        adapter.open();




        List<Staff> staffList = adapter.getAllStaffs();
        //Hiển thị danh sách nhân viên
        LisAdapterAcitivity adapter = new LisAdapterAcitivity(this, staffList);
        ListShowStaff.setAdapter(adapter);

        SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        loadStaffList();

        //Load lại danh sach khi admin thay dổi nhân viên
        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadStaffList();
                SwipeRefreshLayout.setRefreshing(false);
            }
        });

           //Chuyển trang
        TxtAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachNhanVienActivity.this, AddStaffActivity.class);
                startActivity(intent);
            }
        });

        //Item thông tin chi tiết của nhân viên
        ListShowStaff.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Staff selectedStaff = (Staff) parent.getItemAtPosition(position);

                Intent intent = new Intent(DanhSachNhanVienActivity.this, DetailedActivity.class);

                //Đặt thông tin nhân viên vào intent
                intent.putExtra("selectedStaff", selectedStaff);

                startActivity(intent);
            }
        });

        // Lắng nghe sự kiện thay đổi trong EditText để tìm kiếm
        SearchStaffList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        TxtBackHomeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachNhanVienActivity.this, AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //Lọc danh sách dựa trên từ khóa tên của nhân viên
    private  void filter(String text){
        List<Staff> filterStaffList = new ArrayList<>();
        for (Staff staff : adapter.getAllStaffs()){
            if(staff.getName().toLowerCase().contains(text.toLowerCase())){
                filterStaffList.add(staff);
            }
        }
        LisAdapterAcitivity adapter = new LisAdapterAcitivity(this, filterStaffList);
        ListShowStaff.setAdapter(adapter);
    }


    private  void loadStaffList(){
        adapter = new MyArrayAdapter(this);
        adapter.open();

        List<Staff> loadList = adapter.getAllStaffs();
        LisAdapterAcitivity adapter = new LisAdapterAcitivity(this, loadList);
        ListShowStaff.setAdapter(adapter);
    }




}