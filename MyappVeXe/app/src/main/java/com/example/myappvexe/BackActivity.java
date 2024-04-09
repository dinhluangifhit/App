package com.example.myappvexe;

import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BackActivity extends AppCompatActivity {
    private boolean doubleBackToExitPresedOnce = false;
    private static final int TIME_INTERVAL = 2000; //thời gian giữa hai lần nhắn 2s
    @Override
    //xử lý nút trở về
    public void onBackPressed() {
        if (doubleBackToExitPresedOnce) {
            super.onBackPressed(); // thoát khi nhấn lần 2
            return;
        }
        this.doubleBackToExitPresedOnce = true;
        Toast.makeText(this, "Nhấn lần nửa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPresedOnce = false; // đặt lại trạng thái quá thời gian
            }
        }, TIME_INTERVAL);
    }
}
