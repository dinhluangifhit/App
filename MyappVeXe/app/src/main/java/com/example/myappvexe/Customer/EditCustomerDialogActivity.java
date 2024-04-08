package com.example.myappvexe.Customer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myappvexe.R;
import com.example.myappvexe.SQLiteHelper;

public class EditCustomerDialogActivity extends Activity {
    private final Context context;
    private AlertDialog dialog;
    private final Customer customer;
    private final SQLiteHelper sqLiteHelper;

    public EditCustomerDialogActivity(Context context, Customer customer){
        this.context = context;
        this.customer = customer;
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate layout cho bảng nhỏ
        View view = inflater.inflate(R.layout.activity_edit_customer_dialog, null);
        builder.setView(view);

        //Khởi tạo các thành phần trong layout
        EditText DialogNameCus = view.findViewById(R.id.dialogNameCus);
        EditText DlgEmailCus = view.findViewById(R.id.dlgEmailCus);
        RadioButton RadioDlgNam =  view.findViewById(R.id.radioDlgNam);
        RadioButton RadioDlgNu = view.findViewById(R.id.radioDlgNu);
        EditText DlgDateBirthCus = view.findViewById(R.id.dlgDateBirthCus);
        EditText DlgPhoneCus = view.findViewById(R.id.dlgPhoneCus);



        //Hiển thị thông tin trong editetxt
        DialogNameCus.setText(customer.getName());
        DlgEmailCus.setText(customer.getEmail());
        DlgDateBirthCus.setText(customer.getDayofbidth());
        DlgPhoneCus.setText(customer.getPhone());
        //Kiểm tra trạng thái giới tính của khách hàng
        if(customer.getGender().equalsIgnoreCase("Nam")){
            RadioDlgNam.setChecked(true);
        } else{
            RadioDlgNu.setChecked(true);
        }

        //Khi Admin thay đổi thông tin của khách hàng
        Button BntEditCustomer = view.findViewById(R.id.bntEditCustomer);
        BntEditCustomer.setOnClickListener(v -> {
            String usernameCus = customer.getUsername();
            String nameCus = DialogNameCus.getText().toString().trim();
            String emailCus = DlgEmailCus.getText().toString().trim();
            String dateBirthCus = DlgDateBirthCus.getText().toString().trim();
            String phoneCus = DlgPhoneCus.getText().toString().trim();

            //Kiểm tra khi admin thay đổi giới tính khách hàng
            String editGenderCus;
            if(RadioDlgNam.isChecked()){
                editGenderCus = "Nam";
            } else {
                editGenderCus = "Nữ";
            }

            //Cập nhật dữ liệu khách hàng vào SLQLite
            boolean isEdit = sqLiteHelper.updateCus(nameCus,usernameCus, emailCus, editGenderCus, dateBirthCus, phoneCus);
            if (isEdit){
                Toast.makeText(context, "Thông tin của khách hàng đã được thay đổi", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                //Chuyển về danh sách khách hàng sau khi thay đổi thông tin
                Intent intent = new Intent(context, DanhSachKhachHangAcitivity.class);
                context.startActivity(intent);
//                    finish();
            } else {
                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        dialog = builder.create();
        dialog.show();

        //Khi Admin thoát Eidt
        Button BntExitEditCus = view.findViewById(R.id.bntExitCus);
        BntExitEditCus.setOnClickListener(v -> dialog.dismiss());

    }
}