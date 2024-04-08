package com.example.myappvexe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class DangKyActivity extends AppCompatActivity {
    private SQLiteHelper sQLiteHelper;
    EditText EdRegisterBirth;
    EditText EdRegisterName,EdRegisterUserName,EdPassWord,EdEndPassWord,EdRegisterEmail,EdRegisterPhone;
    RadioButton RadioNam, RadioNu;
//    CheckBox chBox;
    Button BntDangKy;
//    DBContext db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        EdRegisterName = (EditText) findViewById(R.id.edRegisterName);
        EdPassWord = (EditText) findViewById(R.id.edPassWord);
        EdRegisterUserName = (EditText) findViewById(R.id.edRegisterUserName);
        EdRegisterEmail = (EditText) findViewById(R.id.edRegisterEmail);
        EdRegisterPhone = (EditText) findViewById(R.id.edRegisterPhone);
        EdEndPassWord = (EditText) findViewById(R.id.edEndPassWord);
        BntDangKy =  (Button)  findViewById(R.id.bntDangKy);
        EdRegisterBirth = (EditText) findViewById(R.id.edRegisterBirth);
        RadioNam =  findViewById(R.id.radioNam);
        RadioNu = findViewById(R.id.radioNu);


        sQLiteHelper = new SQLiteHelper(DangKyActivity.this);

        //Đăng ký khách hàng
       BntDangKy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (validaterFields()) {
                   String RegisterName = EdRegisterName.getText().toString().trim();
                   String RegisterUserName = EdRegisterUserName.getText().toString().trim();
                   String PassWord = EdPassWord.getText().toString().trim();
                   String RegisterEmail = EdRegisterEmail.getText().toString().trim();
                   String RegisterBirth = EdRegisterBirth.getText().toString().trim();
                   String RegisterPhone = EdRegisterPhone.getText().toString().trim();
                   String hashedPassWord = PassWordHash.hashPassWord(PassWord);

                   //Kiểm tra giới tính
                   String RegisterGender = null;
                   if (RadioNam.isChecked()) {
                       RegisterGender = "Nam";

                   } else {
                       RegisterGender = "Nữ";

                   }

                   if(sQLiteHelper.isUserNameExists(RegisterUserName)){
                       Toast.makeText(DangKyActivity.this, "Tên đăng nhập người dùng đã tồn tại.", Toast.LENGTH_SHORT).show();
                   }

                    else{
                       sQLiteHelper.addNewAccount(RegisterName, RegisterUserName, hashedPassWord, RegisterEmail, RegisterGender, RegisterBirth, RegisterPhone);

                       // after adding the data we are displaying a toast message.
                       Toast.makeText(DangKyActivity.this, "Đăng ký tài khoản thành công.", Toast.LENGTH_SHORT).show();

                       //Create a Handler to delay the navigation back to LoginActivity
                       new Handler().postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               // Create an Intent to navigate back to DangNhapActivity
                               Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                               startActivity(intent);// Start the DangNhapActivity
                               finish();
                           }
                       }, 3000);
                   }

               }else{
                   Toast.makeText(DangKyActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
               }
           }
       });
        EdRegisterName.addTextChangedListener(new TextWatcher() {//Kiểm tra tên có ký tực đặc biệt
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Kiểm tra ngay khi người dùng thay đổi văn bản
                if(!isValidName(s.toString())){
                    EdRegisterName.setError("Tên không được chứa các ký tự đặc biệt ");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //Check mật khẩu
        EdEndPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String passWord = EdPassWord.getText().toString();
                String endConfimPassWord = s.toString();
                if(!passWord.equals(endConfimPassWord)){
                    EdEndPassWord.setError("Mật khẩu nhập lại không khớp");
                }else{
                    EdEndPassWord.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        EdRegisterBirth = findViewById(R.id.edRegisterBirth);
    }


    //Kiểm tra đã nhập đủ các trường chưa
    private boolean validaterFields(){
        String name = EdRegisterName.getText().toString().trim();
        String username = EdRegisterUserName.getText().toString().trim();
        String password = EdPassWord.getText().toString().trim();
        String confirmPassWord = EdEndPassWord.getText().toString().trim();
        String birthday = EdRegisterBirth.getText().toString().trim();
        String email = EdRegisterEmail.getText().toString().trim();
        String phone = EdRegisterPhone.getText().toString().trim();


        //Kiểm tra đã nhập đầy đủ các trường chưa
        if(name.isEmpty()||username.isEmpty()||password.isEmpty()||confirmPassWord.isEmpty()||birthday.isEmpty()||
        email.isEmpty()||phone.isEmpty()){
            return false;
        }
        if (!RadioNam.isChecked() && !RadioNu.isChecked()){
            Toast.makeText(this,"Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private boolean isValidName(String name){
        String regex = "^[\\p{L} .'-]+$";
        return name.matches(regex);
    }
        public void showDatePickDialog(View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DangKyActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               String dateOfBirth = dayOfMonth + "/" + (month + 1) + "/" + year;
                EdRegisterBirth.setText(dateOfBirth);
            }
        }, // Thiết lập ngày mặc định khi mở DatePickerDialog
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
//Chuyển về trang đăng nhập
    public void onDangNhapClick(View view){
        Intent intent = new Intent(this,DangNhapActivity.class);
        startActivity(intent);
    }

}