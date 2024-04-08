package com.example.myappvexe.Category;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappvexe.Customer.DanhSachKhachHangAcitivity;
import com.example.myappvexe.Customer.DetailCustomerActivity;
import com.example.myappvexe.R;
import com.example.myappvexe.SQLiteHelper;

public class DetailTripActivity extends AppCompatActivity {
    private SQLiteHelper sqLiteHelper;
    private TextView DetaiLocationStar, DetailLocationEnd, DetailDateBusStar, DetailTimeBusStar, DetailTimeBusEnd, DetailPrice,
            DetailSeat, DetailTimeEnd, TxtBackHomeCategory;
    private Button BntDeleteTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);

        //Nhận thông tin từ intent
        Intent intent = getIntent();
        Trip selectedTrip = (Trip) intent.getSerializableExtra("selectedTrip");

        DetaiLocationStar =  findViewById(R.id.detaiLocationStar);
        DetailLocationEnd = findViewById(R.id.detailLocationEnd);
        DetailDateBusStar = findViewById(R.id.detailDateBusStar);
        DetailTimeBusStar = findViewById(R.id.detailTimeBusStar);
        DetailTimeBusEnd = findViewById(R.id.detailTimeBusEnd);
        DetailPrice = findViewById(R.id.detailPrice);
        DetailSeat = findViewById(R.id.detailSeat);
        DetailTimeEnd = findViewById(R.id.detailTimeEnd);
        TxtBackHomeCategory = findViewById(R.id.txtBackHomeCategory);
        BntDeleteTrip = findViewById(R.id.bntDeleteTrip);

        //Hiển thị thông tin chuyến đi
        DetaiLocationStar.setText("Xuất phát: " + selectedTrip.getLocationStar());
        DetailLocationEnd.setText("Nơi đến: " + selectedTrip.getLocationEnd());
        DetailDateBusStar.setText("Ngày xuất phát: " + selectedTrip.getDateBusStar());
        DetailTimeBusStar.setText("Giờ xuất phát: " + selectedTrip.getTimeBusStar());
        DetailTimeBusEnd.setText("Giờ đến: " + selectedTrip.getTimeBusEnd());
        DetailPrice.setText("Giá vé: " + selectedTrip.getPriceTrip() + "VNĐ");
        DetailSeat.setText("Chõ ngồi: " + selectedTrip.getSeats());
        DetailTimeEnd.setText("Thời gian dự kiến: " + selectedTrip.getTimeEnd() + "h");

        TxtBackHomeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailTripActivity.this, CategoryActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        BntDeleteTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTrip != null){
                    AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(DetailTripActivity.this);
                    confirmBuilder.setTitle("Xác nhận xóa");
                    confirmBuilder.setMessage("Bạn có chắc chắc xóa chuyến đi này?");
                    confirmBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int tripID = (int) selectedTrip.getId();
                            sqLiteHelper = new SQLiteHelper(DetailTripActivity.this);
                            sqLiteHelper.deleteDataTrip(tripID);
                            Toast.makeText(DetailTripActivity.this, "Xóa khách hàng thành công", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(DetailTripActivity.this, CategoryActivity.class);
                            startActivity(intent1);
                            finish();

                        }
                    });
                    confirmBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Giữ nguyên màn hình
                        }
                    });
                    confirmBuilder.create().show();
                }
            }
        });
    }

//    private void deletedTrip(int tripID){
//        sqLiteHelper.deleteDataTrip(tripID);
//
//        Intent intent = new Intent(DetailTripActivity.this, CategoryActivity.class);
//        startActivity(intent);
//        finish();
//    }
}