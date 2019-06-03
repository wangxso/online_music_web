package com.demo.utils;

import java.util.UUID;

public class UUIDGenrator {
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        System.out.println(uuidStr.substring(20));
        return uuidStr.substring(0,10);
    }
}
