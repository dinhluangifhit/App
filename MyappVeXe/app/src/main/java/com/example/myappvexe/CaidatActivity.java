package com.example.myappvexe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CaidatActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);

        TextView tvCheckUpdate = findViewById(R.id.tvCheckUpdate);
        TextView tvDeleteAccount = findViewById(R.id.tvDeleteAccount);

        tvCheckUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi TextView Kiểm tra cập nhật được nhấn vào
                Toast.makeText(CaidatActivity.this, "Kiểm tra cập nhật", Toast.LENGTH_SHORT).show();
                kiemTraCapNhat();
            }
        });
        tvDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi TextView Xóa tài khoản được nhấn vào
                Toast.makeText(CaidatActivity.this, "Xóa tài khoản", Toast.LENGTH_SHORT).show();
                xoaTaiKhoan();
            }
        });
    }

    private void kiemTraCapNhat() {
        // Hiển thị cửa sổ thông báo xác nhận cập nhật
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cập nhật ứng dụng");
        builder.setMessage("Có phiên bản mới của ứng dụng. Bạn có muốn cập nhật ngay bây giờ không?");
        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Thực hiện các hành động cập nhật
                Toast.makeText(CaidatActivity.this, "Đang cập nhật...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Bỏ qua", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Người dùng chọn không cập nhật
                Toast.makeText(CaidatActivity.this, "Không cập nhật", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void xoaTaiKhoan() {
        // Hiển thị cảnh báo xác nhận xóa tài khoản
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa tài khoản");
        builder.setMessage("Bạn có chắc chắn muốn xóa tài khoản không? Hành động này sẽ không thể hoàn tác.");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CaidatActivity.this, "Đang xóa tài khoản...", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Người dùng chọn hủy bỏ
                Toast.makeText(CaidatActivity.this, "Hủy bỏ xóa tài khoản", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
