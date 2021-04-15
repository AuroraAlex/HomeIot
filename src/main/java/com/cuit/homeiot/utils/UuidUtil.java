package com.cuit.homeiot.utils;
import java.util.UUID;
public class UuidUtil {
    public String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
        return uuid;
    }

}