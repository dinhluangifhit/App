package com.example.myappvexe.Customer;

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

import com.example.myappvexe.AdminActivity;
import com.example.myappvexe.R;
import com.example.myappvexe.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKhachHangAcitivity extends AppCompatActivity {
    private TextView TxtBackHomeAdmin;
    private ListView ListShowCus;
    private CustomerArrayAdapter adapter;
    private EditText SearchCustomerList;
    private SwipeRefreshLayout SwipeRefreshLayout;
    private SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_khach_hang_acitivity);

        ListShowCus = (ListView) findViewById(R.id.listShowCustomer);
        SearchCustomerList = (EditText) findViewById(R.id.searchCustomerList);
        TxtBackHomeAdmin = (TextView) findViewById(R.id.txtBackHomeAdmin);
        adapter = new CustomerArrayAdapter(this);
        adapter.open();


        List<Customer> customerList =  adapter.getAllCustomers();
        //Hiển thị danh sách khách hàng
        ListAdapterCustpmer adapter = new ListAdapterCustpmer(this, customerList);
        ListShowCus.setAdapter(adapter);

        SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        loadCusList();
        //Load lại danh sach khi admin thay dổi nhân viên
        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCusList();
                SwipeRefreshLayout.setRefreshing(false);
            }
         });
                //Item thông tin chi tiết của khách hàng
                ListShowCus.setOnItemClickListener( new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Customer selectedCus = (Customer) parent.getItemAtPosition(position);

                        Intent intent = new Intent(DanhSachKhachHangAcitivity.this, DetailCustomerActivity.class);

                        //Đặt thông tin nhân viên vào intent
                        intent.putExtra("selectedCus", selectedCus);
                        startActivity(intent);
                    }
                });

        // Lắng nghe sự kiện thay đổi trong EditText để tìm kiếm
        SearchCustomerList.addTextChangedListener(new TextWatcher() {
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
                Intent intent = new Intent(DanhSachKhachHangAcitivity.this, AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //Lọc danh sách dựa trên từ khóa tên của nhân viên
    private  void filter(String text){
        List<Customer> filterCustomerList = new ArrayList<>();
        for (Customer customer : adapter.getAllCustomers()){
            if(customer.getName().toLowerCase().contains(text.toLowerCase())){
                filterCustomerList.add(customer);
            }
        }
        ListAdapterCustpmer adapter = new ListAdapterCustpmer(this, filterCustomerList);
        ListShowCus.setAdapter(adapter);
    }


    private  void loadCusList(){
        adapter = new CustomerArrayAdapter(this);
        adapter.open();

        List<Customer> loadList = adapter.getAllCustomers();
        ListAdapterCustpmer adapter = new ListAdapterCustpmer(this, loadList);
        ListShowCus.setAdapter(adapter);
    }

}