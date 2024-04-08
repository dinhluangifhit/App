package com.example.myappvexe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myappvexe.Customer.Customer;

import org.mindrot.jbcrypt.BCrypt;


public class SQLiteHelper extends SQLiteOpenHelper{
    //Tạo cơ sở dữ liệu
    private static final String DB_NAME = "mySQLiteBanVeXe";
    private static final int VERSION = 1;
    //Dữ liệu bảng khách hàng
    public static final String TABLE_NAME = "Register";

    public static final String ID_COL = "id";

    public static final String Name_COL = "Name";

    public static final String userName_COL = "userName";

    public static final String passWord_COL = "passWord";

    public static final String email_COL = "email";

    public static final String gender_COL = "gender";

    public static final String dateOfBirth_COL = "dateOfBirth";

    public static final String phone_COL = "phone";


    //Dữ liệu bảng Admin
    private static final String TABLE_ADMIN = "Admin";

    private static final String ID_ADMIN_COL = "id";

    private static final String NameAdmin_COL = "Name";

    private static final String userAdmin_COL = "userName";

    private static final String passWordAdmin_COL = "passWord";

    private static final String emailAdmin_COL = "email";

    private static final String phoneAdmin_COL = "phone";

    //Dữ liệu bảng Admin
    public static final String TABLE_STAFF= "Staff";

    public static final String ID_STAFF_COL = "id";

    public static final String NameSTAFF_COL = "Name";

    public static final String userSTAFF_COL = "userName";

    public static final String passWordSTAFF_COL = "passWord";

    public static final String emailSTAFF_COL = "email";

    public static final String phoneSTAFF_COL = "phone";


    //Dữ liệu bảng location
    public static final String TABLE_LOCATION = "Location";
    public static final String ID_LOCATION_COL = "id";
    public static final String NameLocation_COL = "Name";
    public static final String BusStation_COL = "NameStation";

    //Dữ liệu bảng Trip
    //Dữ liệu bảng location
    public static final String TABLE_TRIP = "Trip";
    public static final String ID_TRIP_COL = "id";
    public static final String tripLocationStar_COL = "NameLocationStar";
    public static final String tripLocationEnd_COL = "NameLocationEnd";
    public static final String tripDate_COL = "dateStar";

    public static final String tripTimeStar_COL = "timeStar";
    public static final String tripTimeEnd_COL = "timeEnd";
    public static final String tripPrice_COL = "price";
    public static final String seats_COL = "seat";
    public static final String intendTime = "tripTime";



    private static final String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + Name_COL + " TEXT,"

                + userName_COL + " TEXT,"

                + passWord_COL + " TEXT,"

                + email_COL + " TEXT,"

                + gender_COL + " TEXT,"

                + dateOfBirth_COL + " TEXT,"

                + phone_COL + " TEXT)";



     private  final String admin = "CREATE TABLE " + TABLE_ADMIN + " ("
                + ID_ADMIN_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + NameAdmin_COL + " TEXT,"

                + userAdmin_COL + " TEXT,"

                + passWordAdmin_COL + " TEXT,"

                + emailAdmin_COL + " TEXT,"

                + phoneAdmin_COL + " TEXT)";



    private  final String adstaff = "CREATE TABLE " + TABLE_STAFF+ " ("
            + ID_STAFF_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

            + NameSTAFF_COL + " TEXT,"

            + userSTAFF_COL + " TEXT,"

            + passWordSTAFF_COL + " TEXT,"

            + emailSTAFF_COL + " TEXT,"

            + phoneSTAFF_COL + " TEXT)";


    private  final String location = "CREATE TABLE " + TABLE_LOCATION+ " ("
            + ID_LOCATION_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

            + NameLocation_COL + " TEXT,"

            + BusStation_COL + " TEXT)";

    private final String trip = "CREATE TABLE " + TABLE_TRIP + " ("
            + ID_TRIP_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + tripLocationStar_COL + " TEXT, "
            + tripLocationEnd_COL + " TEXT, "
            + tripDate_COL + " TEXT, "
            + tripTimeStar_COL + " TEXT, "
            + tripTimeEnd_COL + " TEXT, "
            + tripPrice_COL + " REAL, "
            + seats_COL + " INTEGER, "
            + intendTime + " TEXT)";


    public SQLiteHelper( Context context) {

        super(context, DB_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(query);
        db.execSQL(admin);
        db.execSQL(adstaff);
        db.execSQL(location);
        db.execSQL(trip);



        ContentValues values = new ContentValues();
        values.put(NameAdmin_COL, "Nguyen Van A");
        values.put(userAdmin_COL, "adminA");
        String hashedAdminPassWord = BCrypt.hashpw("adminA", BCrypt.gensalt());
        values.put(passWordAdmin_COL, hashedAdminPassWord);
        values.put(emailAdmin_COL, "admin@example.com");
        values.put(phoneAdmin_COL, "1234567890");
        db.insert(TABLE_ADMIN, null, values);

    }


    // thêm user
    public void addNewAccount (String RegisterName, String RegisterUserName, String PassWord, String RegisterEmail,
                               String RegisterGender, String RegisterBirth,String RegisterPhone){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(Name_COL, RegisterName);
        values.put(userName_COL, RegisterUserName);
        values.put(passWord_COL, PassWord);
        values.put(email_COL, RegisterEmail);
        values.put(gender_COL, RegisterGender);
        values.put(dateOfBirth_COL, RegisterBirth);
        values.put(phone_COL, RegisterPhone);



        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Thêm Admin
    public boolean adAdmin(String AdminName, String AdminUser, String AdminPassWord, String AdminEmail, String AdminPhone){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NameAdmin_COL, AdminName);
        values.put(userAdmin_COL, AdminUser);
        values.put(passWordAdmin_COL, AdminPassWord);
        values.put(emailAdmin_COL, AdminEmail);
        values.put(phoneAdmin_COL, AdminPhone);


        db.insert(TABLE_ADMIN, null, values);
        db.close();

        return false;
    }




    //Thêm Staff
    public void  adStaff(String StaffName, String StaffUser, String StaffPassWord, String StaffEmail, String StaffPhone){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NameSTAFF_COL, StaffName);
        values.put(userSTAFF_COL, StaffUser);
        values.put(passWordSTAFF_COL, StaffPassWord);
        values.put(emailSTAFF_COL, StaffEmail);
        values.put(phoneSTAFF_COL, StaffPhone);


        db.insert(TABLE_STAFF, null, values);
        db.close();
    }
    //Thêm location
    public  void addLocation(String locationName, String busSationName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NameLocation_COL, locationName);
        values.put(BusStation_COL, busSationName);

        db.insert(TABLE_LOCATION, null, values);
        db.close();
    }

    //Thêm Trip
    public  void addTrip(String locationStar, String locationEnd, String tripDate, String tripTimeStar,
                         String tripTimeEnd, String tripPrice, String tripSeat, String tripintendTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tripLocationStar_COL, locationStar);
        values.put(tripLocationEnd_COL, locationEnd);
        values.put(tripDate_COL, tripDate);
        values.put(tripTimeStar_COL, tripTimeStar);
        values.put(tripTimeEnd_COL,tripTimeEnd);
        values.put(tripPrice_COL, tripPrice);
        values.put(seats_COL, tripSeat);
        values.put(intendTime, tripintendTime);

        db.insert(TABLE_TRIP, null, values);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + TABLE_ADMIN + TABLE_STAFF + TABLE_LOCATION + TABLE_TRIP);
        onCreate(db);
    }

    //Xóa data trong bảng staff
    public void deleteDataStaff(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        //Xóa nhân viên từ bảng nhân viên
        db.delete(TABLE_STAFF, userSTAFF_COL + "=?", new  String[]{username});
        db.close();
    }


    //Xóa data trong bảng Register
    public void deleteDataCus(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        //Xóa khách hàng
        db.delete(TABLE_NAME, userName_COL + "=?", new String[]{username});
        db.close();
    }

    //Xóa data trong bảng Tip
    public void deleteDataTrip(int tripID){
        SQLiteDatabase db = this.getWritableDatabase();
        //Xóa chuyến đi
        db.delete(TABLE_TRIP, "id=?", new String[]{String.valueOf(tripID)});
        db.close();
    }


    public  String getHashedPassWordByUserName(String userName){
        SQLiteDatabase db = this.getReadableDatabase();
        String hashedpassword = null;
        Cursor cursor  = db.query(TABLE_NAME, new String[]{passWord_COL}, userName_COL + "=?", new String[]{userName}, null, null, null);
        if(cursor!=null && cursor.moveToFirst()){
            hashedpassword = cursor.getString(0);
            cursor.close();
        }
        db.close();
        return hashedpassword;
    }


    //Cập nhật data Staff
    public boolean updateStaff(String name, String username, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NameSTAFF_COL, name);
        values.put(emailSTAFF_COL, email);
        values.put(phoneSTAFF_COL, phone);
        int rowsAffected = db.update(TABLE_STAFF, values, userSTAFF_COL + "=?", new  String[]{username});
        db.close();
        return rowsAffected > 0;
    }

    //Cập nhật data của Customer
    public  boolean updateCus(String name, String username, String email, String gender, String dateBirth, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name_COL, name);
        values.put(email_COL, email);
        values.put(gender_COL, gender);
        values.put(dateOfBirth_COL, dateBirth);
        values.put(phone_COL, phone);
        int rowAffectedCus = db.update(TABLE_NAME, values, userName_COL + "=? ", new  String[]{username});
        db.close();
        return rowAffectedCus > 0;
    }

    //Lấy thông tin của admin
        public Admin getAdminInfor(String userName) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = null;
            Admin admin = null;


            try {
                //truy ván cơ sở dữ liệu đẻ lấy thông tin admin
                 cursor = db.query(TABLE_ADMIN, null, userAdmin_COL + " = ?",
                         new String[]{userName}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    String name = cursor.getString( cursor.getColumnIndexOrThrow(NameAdmin_COL));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(emailAdmin_COL));
                    admin = new Admin(name, email);
                }

            } catch (Exception e){
                e.printStackTrace();
            } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

            return admin;
        }

     //Lấy thông tin của Customer
    public Customer getCustomerInfor (String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        Customer customer = null;
        try {
            //Truy vấn cở sở dữ liệu để lấy thông tin của Customer
            cursor = db.query(TABLE_NAME, null, userName_COL + " = ?",
                    new String[]{userName}, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                String name = cursor.getString((cursor.getColumnIndexOrThrow(Name_COL)));
                customer = new Customer(name);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (cursor != null){
                cursor.close();
            }
            db.close();
        }
        return customer;
    }
    //Lấy passWord trong database để so sánh đăng nhập Admin
    public  String getHashedPassWordByUserNameAdmin(String userName){
        SQLiteDatabase db = this.getReadableDatabase();
        String hashedpassword = null;
        Cursor cursor  = db.query(TABLE_ADMIN, new String[]{passWordAdmin_COL}, userAdmin_COL + "=?", new String[]{userName}, null, null, null);
        if(cursor!=null && cursor.moveToFirst()){
            hashedpassword = cursor.getString(0);
            cursor.close();
        }
        db.close();
        return hashedpassword;
    }

    //Kiểm tra tên người dùng đăng nhập trong các table
    public  boolean isUserNameExists(String username){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        //Kiểm tra trong bảng Khách hàng
        cursor = db.rawQuery("SELECT 1 FROM Register WHERE username = ?", new String[]{username});
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        cursor.close();


        //Kiểm tra trong bảng admin
        cursor = db.rawQuery("SELECT 1 FROM Admin WHERE username = ?", new String[]{username});
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        cursor.close();

        //Kiểm tra trong bảng Staff
        cursor = db.rawQuery("SELECT 1 FROM Staff WHERE username = ?", new String[]{username});
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        cursor.close();

        return false;
    }


    //Kiểm tra bên trong admin có chứa satff chưa
    public boolean isUserStaffExists(String username){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;
        //Kiểm tra bảng Admin
        cursor = db.rawQuery("SELECT 1 FROM Admin WHERE username = ?", new String[]{username});
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //Kiểm tra tỉnh thành đã có trong table chưa
    public boolean isLocationExits(String Name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;
        //Kiểm tra trong bảng Location
        cursor = db.rawQuery("SELECT 1 FROM Location WHERE Name =?", new String[]{Name});
            if (cursor.getCount() > 0){
                cursor.close();;
                return true;
            }
            cursor.close();
            return false;
        }

}
