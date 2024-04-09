package com.example.myappvexe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends BackActivity {

    private Button btnMale, btnFemale, btnDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnMale = findViewById(R.id.btnmale);
        btnFemale = findViewById(R.id.btnfemale);
        btnDiff = findViewById(R.id.btndiff);


        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút Nam được nhấn
                Toast.makeText(SettingActivity.this, "Bạm đã chọnNam", Toast.LENGTH_SHORT).show();
            }
        });
        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút Nữ được nhấn
                Toast.makeText(SettingActivity.this, "Bạn đã chọn Nữ", Toast.LENGTH_SHORT).show();
            }
        });
        btnDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút Khác được nhấn
                Toast.makeText(SettingActivity.this, "Bạn đã chọn Khác", Toast.LENGTH_SHORT).show();
            }
        });
    }
}