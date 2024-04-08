package com.example.myappvexe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class EditStaffDialogActivity extends AppCompatActivity {
    private Context context;
    private AlertDialog dialog;
    private Staff staff;
    private SQLiteHelper sqLiteHelper;

    public EditStaffDialogActivity(Context context, Staff staff){
        this.context = context;
        this.staff = staff;
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate layout cho bảng nhỏ
        View view = inflater.inflate(R.layout.activity_edit_staff_dialog, null);
        builder.setView(view);

        //Khởi tạo các thành phần trong layout
        EditText EditNameStaff = view.findViewById(R.id.editNameStaff);
        EditText EditEmailStaff = view.findViewById(R.id.editEmailStaff);
        EditText EditPhoneStaff = view.findViewById(R.id.editPhoneStaff);



        //Hiển thị thông tin trong editetxt
        EditNameStaff.setText(staff.getName());
        EditEmailStaff.setText(staff.getEmail());
        EditPhoneStaff.setText(staff.getPhone());


        //Sử lý khi người dùng ấn núc lưu
        Button BntREditStaff = view.findViewById(R.id.bntREditStaff);
        BntREditStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =  staff.getUsername();
                String namelStaff = EditNameStaff.getText().toString().trim();
                String emailStaff = EditEmailStaff.getText().toString().trim();
                String phoneStaff = EditPhoneStaff.getText().toString().trim();


                Boolean isCussec = sqLiteHelper.updateStaff(namelStaff,username,emailStaff, phoneStaff);
                if(isCussec){
                    Toast.makeText(context, "Thông tin nhân viên đã được cập nhật", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    Intent intent = new Intent(context, DanhSachNhanVienActivity.class);
                    context.startActivity(intent);

                } else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog = builder.create();
        dialog.show();

        Button BntExitEditStaff = (Button) view.findViewById(R.id.bntExitEditStaff);
        BntExitEditStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
