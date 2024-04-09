package com.example.myappvexe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedActivity extends BackActivity {
    private SQLiteHelper sqLiteHelper;
    private TextView DetailNameStaff, DetailEmailStaff, DetailPhoneStaff;
    private Button BntRevisionStaff, BntDetailAddStaff,BntDeleteStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);




        //Nhận thông tin từ intent
        Intent intent = getIntent();
        Staff selectedStaff = (Staff) intent.getSerializableExtra("selectedStaff");

        //Hiển thị thông tin nhân viên trên màn hình giao diện
        DetailNameStaff = findViewById(R.id.detailNameStaff);
        DetailEmailStaff = findViewById(R.id.detailEmailStaff);
        DetailPhoneStaff = findViewById(R.id.detailSdtStaff);

        DetailNameStaff.setText("Họ Tên:  " + selectedStaff.getName());
        DetailEmailStaff.setText("Email: " + selectedStaff.getEmail());
        DetailPhoneStaff.setText("SĐT: " + selectedStaff.getPhone());


        //Phân quyền cho nhân viên
        BntDetailAddStaff = (Button) findViewById(R.id.bntDetailAddStaff);
        BntDetailAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedStaff != null) {
                    AlertDialog.Builder confimBuilder = new AlertDialog.Builder(DetailedActivity.this);
                    confimBuilder.setTitle("Xác phân quyền");
                    confimBuilder.setMessage("Bạn có muốn phân quyền cho nhân viên này?");
                    confimBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String name = selectedStaff.getName();
                                    String username = selectedStaff.getUsername();
                                    String password = selectedStaff.getPassword();
                                    String email = selectedStaff.getEmail();
                                    String phone = selectedStaff.getPhone();

                                    sqLiteHelper = new SQLiteHelper(DetailedActivity.this);
                                    if(sqLiteHelper.isUserStaffExists(username)){
                                        Toast.makeText(DetailedActivity.this, "Nhân viên đã tồn tại trong Admin", Toast.LENGTH_SHORT).show();
                                    } else {
                                        sqLiteHelper.adAdmin(name, username, password, email, phone);
                                        sqLiteHelper.deleteDataStaff(username);
                                        Toast.makeText(DetailedActivity.this, "Đã thêm nhân viên thành công", Toast.LENGTH_SHORT).show();

                                        Intent intent1 = new Intent(DetailedActivity.this, DanhSachNhanVienActivity.class);
                                        startActivity(intent1);
                                        finish();

                                    }
                                }

                            });
                    confimBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    confimBuilder.create().show();


                } else {
                    Toast.makeText(DetailedActivity.this, "Không thể thêm Admin từ nhân viên không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BntRevisionStaff = (Button) findViewById(R.id.bntRevisionStaff);
        BntRevisionStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedStaff != null){
                    EditStaffDialogActivity editStaffDialogActivity = new EditStaffDialogActivity(DetailedActivity.this, selectedStaff);
                    editStaffDialogActivity.show();
                } else {
                    Toast.makeText(DetailedActivity.this, "Không thể sửa thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Chức năng xóa nhân viên khỏi hệ thống
        BntDeleteStaff = (Button) findViewById(R.id.bntDeleteStaff);
        BntDeleteStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedStaff != null){
                    AlertDialog.Builder confirmBuider= new AlertDialog.Builder(DetailedActivity.this);
                    confirmBuider.setTitle("Xác nhận xóa");
                    confirmBuider.setMessage("Bạn có muốn xóa nhân viên này?");
                    confirmBuider.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String username = selectedStaff.getUsername();
                            sqLiteHelper = new SQLiteHelper(DetailedActivity.this);
                            sqLiteHelper.deleteDataStaff(username);
                            Toast.makeText(DetailedActivity.this, "Đã xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(DetailedActivity.this, DanhSachNhanVienActivity.class);
                            startActivity(intent1);
                            finish();
                        }
                    });
                   confirmBuider.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           //Trở về màn hình detail
                       }
                   });
                   confirmBuider.create().show();
                }
            }
        });


    }

}