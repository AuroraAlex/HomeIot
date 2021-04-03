package com.cuit.homeiot.controller;

import com.cuit.homeiot.pojo.DataDTO;
import com.cuit.homeiot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataController {

    @Autowired
    DataService dataService;
    @Autowired
    SimpleDateFormat simpleDateFormat;

    @GetMapping("/getOneDayData")
    private Map<String,Object> getOneDayData(@RequestParam(name = "did") String did){
        Map<String,Object> map = new HashMap<>();
        List<String> dataList  = new ArrayList<>();
        List<DataDTO> dataDTOList = dataService.getOneDayData(did);
        if (dataDTOList.isEmpty()){
            map.put("count", dataDTOList.size());
            map.put("msg","False");
            map.put("code","0");
            return map;
        }
        dataDTOList.forEach(dataDTO -> {
           dataList.add("{\"Time\":\""+simpleDateFormat.format(dataDTO.getAt())+"\","+dataDTO.getPayload().replace("{","")
                   .replace("}","")+"}");
        });
        map.put("data", dataList);
        map.put("count", dataDTOList.size());
        map.put("msg","OK");
        map.put("code","0");

        return map;
    }
}
