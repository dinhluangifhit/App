package com.example.myappvexe.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappvexe.R;
import com.example.myappvexe.SQLiteHelper;

import java.util.Calendar;

public class AddTripActivity extends AppCompatActivity {
    private AutoCompleteTextView AutoCompleteStationTrip,  AutoCompleteLocationTrip;
    private SQLiteDatabase db;
    private EditText EditTimeBusStar, EditDateBusStar, EditTimeBusEnd, TimeEnd, PriceTrip, SeatTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        EditTimeBusStar = findViewById(R.id.editTimeBusStar);
        EditDateBusStar = findViewById(R.id.editDateBusStar);
        EditTimeBusEnd = findViewById(R.id.editTimeBusEnd);
        TimeEnd = findViewById(R.id.timeEnd);
        PriceTrip = findViewById(R.id.priceTrip);
        SeatTrip = findViewById(R.id.seatTrip);
        AutoCompleteStationTrip = findViewById(R.id.autoCompleteStationTrip);
        AutoCompleteLocationTrip = findViewById(R.id.autoCompleteLocationTrip);
        Button bntAddTrip = findViewById(R.id.bntAddTrip);
        TextView txtBackListTrip = findViewById(R.id.txtBackListTrip);



        SQLiteHelper dbHelper = new SQLiteHelper(this);
        db = dbHelper.getReadableDatabase();
        LocationAutoCompleteAdapter adapter = new LocationAutoCompleteAdapter(this, null, db);

        AutoCompleteLocationTrip.setAdapter(adapter);
        AutoCompleteStationTrip.setAdapter(adapter);

        txtBackListTrip.setOnClickListener(v -> {
            Intent intent = new Intent(AddTripActivity.this, CategoryActivity.class);
            startActivity(intent);
            finish();
        });

        AutoCompleteLocationTrip.setOnItemClickListener((adapterView, view, position, id) -> {
            // Lấy CursorAdapter và Cursor tương ứng
            CursorAdapter cursorAdapter = (CursorAdapter) AutoCompleteLocationTrip.getAdapter();
            Cursor cursor = cursorAdapter.getCursor();
            // Di chuyển Cursor đến vị trí của mục đã chọn
            cursor.moveToPosition(position);

            // Lấy giá trị từ cột "Name" của Cursor và đặt vào AutoCompleteTextView
            String selectedName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            AutoCompleteLocationTrip.setText(selectedName);

            // Đóng danh sách và loại bỏ focus khỏi AutoCompleteTextView
            AutoCompleteLocationTrip.dismissDropDown();
            AutoCompleteLocationTrip.clearFocus();
        });

        AutoCompleteStationTrip.setOnItemClickListener((adapterView, view, position, id) -> {
            // Lấy CursorAdapter và Cursor tương ứng
            CursorAdapter cursorAdapter = (CursorAdapter) AutoCompleteStationTrip.getAdapter();
            Cursor cursor = cursorAdapter.getCursor();
            // Di chuyển Cursor đến vị trí của mục đã chọn
            cursor.moveToPosition(position);

            // Lấy giá trị từ cột "Name" của Cursor và đặt vào AutoCompleteTextView
            String selectedName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            AutoCompleteStationTrip.setText(selectedName);

            // Đóng danh sách và loại bỏ focus khỏi AutoCompleteTextView
            AutoCompleteStationTrip.dismissDropDown();
            AutoCompleteStationTrip.clearFocus();
        });

        //Sử lý thêm vào Trip
        bntAddTrip.setOnClickListener(v -> {
            if (validaterFields()){
                String locationStar = AutoCompleteLocationTrip.getText().toString().trim();
                String locationEnd = AutoCompleteStationTrip.getText().toString().trim();
                String dateBusStar = EditDateBusStar.getText().toString().trim();
                String timeBusStar = EditTimeBusStar.getText().toString().trim();
                String timeBusEnd = EditTimeBusEnd.getText().toString().trim();
                String timeEnd = TimeEnd.getText().toString().trim();
                String priceTrip = PriceTrip.getText().toString().trim();
                String seats = SeatTrip.getText().toString().trim();
                if (!locationStar.equals(locationEnd)){
                    dbHelper.addTrip(locationStar, locationEnd, dateBusStar, timeBusStar, timeBusEnd, priceTrip,
                            seats, timeEnd);
                    Toast.makeText(AddTripActivity.this, "Đã thêm chuyến đi thành công!", Toast.LENGTH_SHORT).show();
                    //Xóa thông tin ngay sau khi đã thêm thành công

                    AutoCompleteLocationTrip.setText("");
                    AutoCompleteStationTrip.setText("");
                    EditDateBusStar.setText("");
                    EditTimeBusStar.setText("");
                    EditTimeBusEnd.setText("");
                    TimeEnd.setText("");
                    PriceTrip.setText("");
                    SeatTrip.setText("");
                } else {
                    Toast.makeText(AddTripActivity.this, "Không để thêm chuyến đi trong 1 tỉnh thành", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(AddTripActivity.this, "Vui lòng nhập đầy đủ các thông tin!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        db.close();
    }


    //Kiểm tra các trường
    private boolean validaterFields(){
        String locationStar = AutoCompleteLocationTrip.getText().toString().trim();
        String locationEnd = AutoCompleteStationTrip.getText().toString().trim();
        String dateBusStar = EditDateBusStar.getText().toString().trim();
        String timeBusStar = EditTimeBusStar.getText().toString().trim();
        String timeBusEnd = EditTimeBusEnd.getText().toString().trim();
        String timeEnd = TimeEnd.getText().toString().trim();
        String priceTrip = PriceTrip.getText().toString().trim();
        String seats = SeatTrip.getText().toString().trim();

        return !locationEnd.isEmpty() && !locationStar.isEmpty() && !dateBusStar.isEmpty() && !timeBusStar.isEmpty() &&
                !timeBusEnd.isEmpty() && !timeEnd.isEmpty() && !priceTrip.isEmpty() && !seats.isEmpty();
    }
    //Hiển thị chọn thời gian
    public  void showDialogTime(View v){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int munite = c.get(Calendar.MINUTE);

        @SuppressLint("SetTextI18n") TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> EditTimeBusStar.setText(hourOfDay + ":" + minute), hour, munite, true);
        timePickerDialog.show();
    }

    public  void showDialogTimeEnd(View v){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int munite = c.get(Calendar.MINUTE);

        @SuppressLint("SetTextI18n") TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> EditTimeBusEnd.setText(hourOfDay + ":" + minute), hour, munite, true);
        timePickerDialog.show();
    }

    //Hiển thị chọn ngày
    public void showDialogDate(View v){
        final  Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> EditDateBusStar.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1), year, month, day);
        datePickerDialog.show();

    }
}