package com.example.myappvexe.Customer;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myappvexe.Category.ListShowAdapterTrip;
import com.example.myappvexe.Category.Trip;
import com.example.myappvexe.Category.TripArrayAdapter;
import com.example.myappvexe.DangNhapActivity;
import com.example.myappvexe.R;
import com.example.myappvexe.SQLiteHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private ListView ListShowTripHome;
    private AutoCompleteTextView AutoCompleteLocationStar, AutoCompleteLocationEnd;
    private EditText EdDateStar;
    private final String[] cities = {"An Giang","Bà rịa Vũng Tàu","Bạc Liêu","Bắc Giang","Bắc Kạn","Bắc Ninh","Bến Tre","Bình Dương",
            "Bình Định","Bình Phước","Bình Thuận","Cà Mau","Cao Bằng","Cần Thơ","Đà Nẵng","Đắk Lắk","Đắk Nông","Điện Biên","Đồng Nai",
            "Đồng Tháp","Gia Lai","Hà Giang","Hà Nam","Hà Nội","Hà Tĩnh","Hải Dương","Hải Phòng","Hậu Giang","Hòa Bình","Hưng Yên","Khánh Hòa",
            "Kiên Giang","Kon Tum","Lai Châu","Lạng Sơn","Lào Cai","Lâm Đồng","Long An","Nam Định","Nghệ An","Ninh Bình","Ninh Thuận","Phú Thọ",
            "Phú Yên","Quảng Bình","Quảng Nam","Quảng Ngãi","Quảng Ninh","Quảng Trị","Sóc Trăng","Sơn La","Tây Ninh","Thái Bình","Thái Nguyên",
            "Thanh Hóa","Thừa Thiên Huế","Tiền Giang",
            "TP Hồ Chí Minh","Trà Vinh","Tuyên Quang","Vĩnh Long","Vĩnh Phúc","Yên Bái"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView txtCusName = findViewById(R.id.txtCusName);
        ImageView logout = findViewById(R.id.logOut);
        ListShowTripHome = findViewById(R.id.listShowTripHome);
        AutoCompleteLocationStar = findViewById(R.id.autoCompleteLocationStar);
        AutoCompleteLocationEnd = findViewById(R.id.autoCompleteLocationEnd);
        Button searchTrip = findViewById(R.id.searchTrip);
        EdDateStar = findViewById(R.id.edDateStar);

        TripArrayAdapter adapter1 = new TripArrayAdapter(this);
        adapter1.opent();





        List<Trip> tripList =  adapter1.getAllTrips();
        //Hiển thị danh sách chuyến đi
        ListShowAdapterTrip adapter = new ListShowAdapterTrip(this, tripList);
        ListShowTripHome.setAdapter(adapter);

        ArrayAdapter<String> adapters = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cities);
        AutoCompleteLocationStar.setAdapter(adapters);
        AutoCompleteLocationEnd.setAdapter(adapters);

        searchTrip.setOnClickListener(v -> {
            if (validaterFields()){
                String locationStar = AutoCompleteLocationStar.getText().toString().trim();
                String locationEnd = AutoCompleteLocationEnd.getText().toString().trim();
                String date = EdDateStar.getText().toString().trim();

                //Danh sách tạm thời để lưu trữ các chuyến đi
                List<Trip> filteredTripList = new ArrayList<>();

                for (Trip trip : tripList){
                    if (trip.getLocationStar().equalsIgnoreCase(locationStar) && trip.getLocationEnd().equalsIgnoreCase(locationEnd)
                    && trip.getDateBusStar().equalsIgnoreCase(date)){
                        filteredTripList.add(trip);
                    }
                }
                //Hiển thị sau khi đã lọc
                ListShowAdapterTrip filteredAdapter = new ListShowAdapterTrip(HomePage.this, filteredTripList);
                ListShowTripHome.setAdapter(filteredAdapter);
            } else {
                Toast.makeText(HomePage.this, "", Toast.LENGTH_SHORT).show();
            }

        });


        // Kiểm tra nếu có thông tin tài khoản user từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String saverCusName = sharedPreferences.getString("userName", "");

        if(!saverCusName.isEmpty()){
            SQLiteHelper dbHelper = new SQLiteHelper(this);
            Customer customer = dbHelper.getCustomerInfor(saverCusName);
            if(customer != null) {
                txtCusName.setText(customer.getName());
            }
        }

        logout.setOnClickListener(v -> removreLogin());



    }
    //Xóa dữ lieeujj trong SharePregerences
    private void removreLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userName");
        editor.remove("passWord");
        editor.putBoolean("isLogin", false);
        editor.apply();

        Intent intent = new Intent(HomePage.this, DangNhapActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validaterFields(){
        String locationStar = AutoCompleteLocationStar.getText().toString().trim();
        String locationEnd = AutoCompleteLocationEnd.getText().toString().trim();

        return !locationStar.isEmpty() && !locationEnd.isEmpty();
    }

    //Hiển thị chọn ngày
    public void showDialogDate(View v){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> EdDateStar.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1), year, month, day);
        datePickerDialog.show();

    }
}