package com.cuit.homeiot.controller;

import com.cuit.homeiot.service.SwitchService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SwitchController {

    @Autowired
    SwitchService switchService;

    @GetMapping("/getKeys")
    public Map<String,Object> getKeys(@RequestParam(name = "did") String did){
        Map<String,Object> map = new HashMap<>();
        List<String> keys = switchService.getKeys(did);
        if (keys.isEmpty()){
            map.put("count", keys.size());
            map.put("msg","False");
            map.put("code","0");
            return map;
        }
        map.put("data", keys);
        map.put("count", keys.size());
        map.put("msg","OK");
        map.put("code","0");
        return map;
    }
}
