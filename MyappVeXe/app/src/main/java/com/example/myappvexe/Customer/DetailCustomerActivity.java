package com.example.myappvexe.Customer;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myappvexe.R;
import com.example.myappvexe.SQLiteHelper;

public class DetailCustomerActivity extends AppCompatActivity {
    private SQLiteHelper sqLiteHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);

        //Nhận thông tin từ intent
        Intent intent = getIntent();
        Customer selectedCus = (Customer) intent.getSerializableExtra("selectedCus");

        //Hiển thị thông tin nhân viên trên màn hình giao diện
        TextView detailNameCus = findViewById(R.id.detailNameCus);
        TextView detailEmailCus = findViewById(R.id.detailEmailCus);
        TextView detailGenderCus = findViewById(R.id.detailGenderCus);
        TextView detailBirthCus = findViewById(R.id.detailBirthCus);
        TextView detailSdtCus = findViewById(R.id.detailSdtCus);

        detailNameCus.setText("Họ Tên:  " + selectedCus.getName());
        detailEmailCus.setText("Email: " + selectedCus.getEmail());
        detailGenderCus.setText("Giới tính: " + selectedCus.getGender());
        detailBirthCus.setText("Ngày sinh: " + selectedCus.getDayofbidth());
        detailSdtCus.setText("SĐT: " + selectedCus.getPhone());


        Button bntEditCus = findViewById(R.id.bntEditCus);
        bntEditCus.setOnClickListener(v -> {
            EditCustomerDialogActivity editCustomerDialogActivity = new EditCustomerDialogActivity(DetailCustomerActivity.this,selectedCus);
            editCustomerDialogActivity.show();
        });


        //Xóa khách hàng
        Button bntDeleteCus = findViewById(R.id.bntDeleteCus);
        bntDeleteCus.setOnClickListener(v -> {

            AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(DetailCustomerActivity.this);
            confirmBuilder.setTitle("Xác nhận xóa");
            confirmBuilder.setMessage("Bạn có chắc chắc xóa khách hàng này?");
            confirmBuilder.setPositiveButton("Có", (dialog, which) -> {
                String userNameCus = selectedCus.getUsername();
                sqLiteHelper = new SQLiteHelper(DetailCustomerActivity.this);
                sqLiteHelper.deleteDataCus(userNameCus);
                Toast.makeText(DetailCustomerActivity.this, "Xóa khách hàng thành công", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(DetailCustomerActivity.this, DanhSachKhachHangAcitivity.class);
                startActivity(intent1);
                finish();

            });
            confirmBuilder.setNegativeButton("Không", (dialog, which) -> {
                //Giữ nguyên màn hình
            });
            confirmBuilder.create().show();
        });

    }
}