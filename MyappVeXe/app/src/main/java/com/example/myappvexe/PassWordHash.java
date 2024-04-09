package com.example.myappvexe;

import org.mindrot.jbcrypt.BCrypt;

public class PassWordHash {
    public static String hashPassWord(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}



