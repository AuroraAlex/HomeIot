package com.cuit.homeiot.controller;

import com.cuit.homeiot.pojo.Device;
import com.cuit.homeiot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @RequestMapping("/getAllList")
    private Map<String,Object> getAllList(){
        Map<String,Object> map = new HashMap<>(10);
        List<Device> all = deviceService.listAllDeviceAndCheckConnected();
        all.forEach(device -> {
            device.setId(null);
            device.setKey("");
        });
        map.put("data",all);
        map.put("msg","OK");
        map.put("code","0");
        map.put("count",Integer.valueOf(all.size()).toString());

        return map;
    }

    @PostMapping("/addDevice")
    private Map<String,Object> addDevice(@RequestParam(name = "DID") String DID,
                                         @RequestParam(name = "name") String name,
                                         @RequestParam(name = "type") String type,
                                         @RequestParam(name = "comm") String comm,
                                         @RequestParam(name = "config")  String config){
        Map<String,Object> map = new HashMap<>(10);
        map.put("code","0");
        if (deviceService.addDevice(new Device(DID,type,comm,name,config))){
            map.put("msg","OK");
            return map;
        }
        map.put("msg","False");
        return map;
    }

    @DeleteMapping("/delDevice")
    private Map<String,Object> delDevice(@RequestParam(name = "did") String did){
        Map<String,Object> map = new HashMap<>(10);
        map.put("code","0");
        if (deviceService.delDevice(did)){
            map.put("msg","OK");
            map.put("success",true);
            return map;
        }
        map.put("success",false);
        map.put("msg","False");
        return map;
    }

    @DeleteMapping("/batchRemove")
    private Map<String,Object> delDevices(@RequestParam(name = "dids") String dids){
        Map<String,Object> map = new HashMap<>(10);
        map.put("code","0");
        if (deviceService.batchDelDevice(dids)){
            map.put("msg","OK");
            map.put("success",true);
            return map;
        }
        map.put("success",false);
        map.put("msg","False");
        return map;
    }

    @GetMapping("/getAllTypeDeviceNum")
    private Map<String,Object> getAllTypeDeviceNum(){
        Map<String,Object> map = new HashMap<>(10);

        map.put("code","0");
        map.put("msg","OK");
        map.put("count",deviceService.getAllTypeNum());
        return map;
    }



}
