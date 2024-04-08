package com.example.myappvexe;

import android.content.Context;
import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationManager {
    SQLiteHelper dbHelper, dbHelperAdmin;
    public AuthenticationManager(Context context){
        dbHelper = new SQLiteHelper(context);
        dbHelperAdmin = new SQLiteHelper(context);

    }
    public boolean auhenticate(String username, String password){
        String hashedPassWordFromData = dbHelper.getHashedPassWordByUserName(username);
        if (hashedPassWordFromData != null && BCrypt.checkpw(password, hashedPassWordFromData)){
            return true;

        }else{
            return false;
        }
    }

    public  boolean AdminAuthenticate(String username, String password){
        String hashedPassWordFromDataAdmin = dbHelperAdmin.getHashedPassWordByUserNameAdmin(username);
        if(hashedPassWordFromDataAdmin != null && BCrypt.checkpw(password, hashedPassWordFromDataAdmin)){
            return true;
        }else{
            return false;
        }
    }

}
