package com.cuit.homeiot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SecurityController {
    @RequestMapping("/success")
    private Map<String,String> success(){
        Map<String,String> map = new HashMap<>(10);
        map.put("msg","OK");
        return map;
    }


    @RequestMapping("/failed")
    private Map<String,String> failed(){
        Map<String,String> map = new HashMap<>(10);
        map.put("msg","failed");
        return map;
    }
}
