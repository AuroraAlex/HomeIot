package com.cuit.homeiot.controller;

import com.cuit.homeiot.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RuleController {

    @Autowired
    RuleService ruleService;

    @PostMapping("/addRule")
    public Map<String,Object> addRule(@RequestParam(name = "SDID") String sdid,
                                      @RequestParam(name = "TDID") String tdid,
                                      @RequestParam(name = "CMD") String cmd,
                                      @RequestParam(name = "CON") String condition,
                                      @RequestParam(name = "DES") String description){
        Map<String,Object> map = new HashMap<>();
        if (ruleService.addRule(sdid, tdid, cmd, condition, description)){
            map.put("msg","OK");
            return map;
        }
        map.put("msg","False");
        return map;
    }

}
