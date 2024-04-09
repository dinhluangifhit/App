package com.example.myappvexe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myappvexe.Category.CategoryActivity;
import com.example.myappvexe.Customer.DanhSachKhachHangAcitivity;
import com.example.myappvexe.Location.LocationActivity;

public class AdminActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        TextView listUser = findViewById(R.id.listUser);
        TextView listCastegory = findViewById(R.id.listCastegory);
        TextView listProduct = findViewById(R.id.listProduct);
        TextView listStatistical = findViewById(R.id.listStatistical);
        TextView listStaff = findViewById(R.id.listStaff);
        TextView viewName = findViewById(R.id.viewNameAdmin);
        TextView viewEmail = findViewById(R.id.viewEmail);
        TextView inLogout = findViewById(R.id.logOut);

        // Kiểm tra nếu có thông tin tài khoản admin từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String saverUserName = sharedPreferences.getString("userName", "");



        if(!saverUserName.isEmpty()){
            SQLiteHelper dbHelper = new SQLiteHelper(this);
            Admin admin = dbHelper.getAdminInfor(saverUserName);
            viewName.setText(admin.getName());
            viewEmail.setText(admin.getEmail());
        }


        listStaff.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, DanhSachNhanVienActivity.class);
            startActivity(intent);
            finish();

        });

        listUser.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, DanhSachKhachHangAcitivity.class);
            startActivity(intent);
            finish();

        });

        listCastegory.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, LocationActivity.class);
            startActivity(intent);
            finish();
        });

        listProduct.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, CategoryActivity.class);
            startActivity(intent);
            finish();
        });

        inLogout.setOnClickListener(v -> removreLogin());
    }

    //Xóa dữ lieeujj trong SharePregerences
    private void removreLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userName");
        editor.remove("passWord");
        editor.putBoolean("isLogin", false);
        editor.apply();

        Intent intent = new Intent(this, DangNhapActivity.class);
        startActivity(intent);
        finish();
    }



}