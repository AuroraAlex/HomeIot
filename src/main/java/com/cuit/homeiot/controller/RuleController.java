package com.cuit.homeiot.controller;

import com.cuit.homeiot.pojo.RuleList;
import com.cuit.homeiot.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/getAllRules")
    public Map<String,Object> getAllRules(){
        Map<String,Object> map = new HashMap<>();
        List<RuleList> all = ruleService.getAll();

        map.put("data",all);
        map.put("msg","OK");
        map.put("code","0");
        map.put("count",Integer.valueOf(all.size()).toString());

        return map;
    }

    @DeleteMapping("/delRule")
    public Map<String,Object> delRule(@RequestParam(name = "rid") String rid){
        Map<String,Object> map = new HashMap<>();
        if (ruleService.delRule(rid)){
            map.put("msg","OK");
            map.put("success",true);
            return map;
        }

        map.put("success",false);
        map.put("msg","False");
        return map;
    }

    @DeleteMapping("/batchRemoveRules")
    private Map<String,Object> delDevices(@RequestParam(name = "rids") String rids){
        Map<String,Object> map = new HashMap<>(10);
        map.put("code","0");
        if (ruleService.batchDelDevice(rids)){
            map.put("msg","OK");
            map.put("success",true);
            return map;
        }
        map.put("success",false);
        map.put("msg","False");
        return map;
    }

}
