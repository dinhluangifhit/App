package com.example.myappvexe.Location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappvexe.R;
import com.example.myappvexe.SQLiteHelper;

public class AddLocationActivity extends AppCompatActivity {
    private AutoCompleteTextView AutoCompleteLocation;
    private EditText EditBusStation;
    private TextView TxtBackListLocation;
    private Button BntAddLocation;
    private SQLiteHelper sqLiteHelper;
    private String[] cities = {"An Giang","Bà rịa Vũng Tàu","Bạc Liêu","Bắc Giang","Bắc Kạn","Bắc Ninh","Bến Tre","Bình Dương",
            "Bình Định","Bình Phước","Bình Thuận","Cà Mau","Cao Bằng","Cần Thơ","Đà Nẵng","Đắk Lắk","Đắk Nông","Điện Biên","Đồng Nai",
            "Đồng Tháp","Gia Lai","Hà Giang","Hà Nam","Hà Nội","Hà Tĩnh","Hải Dương","Hải Phòng","Hậu Giang","Hòa Bình","Hưng Yên","Khánh Hòa",
            "Kiên Giang","Kon Tum","Lai Châu","Lạng Sơn","Lào Cai","Lâm Đồng","Long An","Nam Định","Nghệ An","Ninh Bình","Ninh Thuận","Phú Thọ",
            "Phú Yên","Quảng Bình","Quảng Nam","Quảng Ngãi","Quảng Ninh","Quảng Trị","Sóc Trăng","Sơn La","Tây Ninh","Thái Bình","Thái Nguyên",
            "Thanh Hóa","Thừa Thiên Huế","Tiền Giang",
            "TP Hồ Chí Minh","Trà Vinh","Tuyên Quang","Vĩnh Long","Vĩnh Phúc","Yên Bái",};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        AutoCompleteLocation = findViewById(R.id.autoCompleteLocation);
        EditBusStation = findViewById(R.id.editBusStation);
        BntAddLocation = findViewById(R.id.bntAddLocation);
        TxtBackListLocation = findViewById(R.id.txtBackListLocation);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cities);
        AutoCompleteLocation.setAdapter(adapter);

        sqLiteHelper = new SQLiteHelper(AddLocationActivity.this);

        TxtBackListLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLocationActivity.this, LocationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        BntAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaterFields()){
                    String nameLocation = AutoCompleteLocation.getText().toString().trim();
                    String busStation = EditBusStation.getText().toString().trim();
                    if(sqLiteHelper.isLocationExits(nameLocation)){
                        Toast.makeText(AddLocationActivity.this, "Tỉnh thành này đã tồn tại!", Toast.LENGTH_SHORT).show();
                    } else {
                        sqLiteHelper.addLocation(nameLocation, busStation);
                        Toast.makeText(AddLocationActivity.this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddLocationActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Kiểm tra các trường đã nhập đủ chưa
    private boolean validaterFields(){
        String namelocation = AutoCompleteLocation.getText().toString().trim();
        String busStation = EditBusStation.getText().toString().trim();

        if(namelocation.isEmpty() || busStation.isEmpty()){
            return false;
        } else {
            return true;
        }
    }
}