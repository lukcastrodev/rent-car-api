package com.ctech.rentcarapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    private PasswordEncoder(){

    }
    
    public static String encoder(String password){
        BCryptPasswordEncoder hash = new BCryptPasswordEncoder();
        return hash.encode(password);
    }
}
