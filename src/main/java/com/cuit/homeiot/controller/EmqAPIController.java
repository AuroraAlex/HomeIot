package com.cuit.homeiot.controller;

import com.cuit.homeiot.pojo.ApiClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;


@RestController
public class EmqAPIController {
    @Autowired
    RestTemplate restTemplate;

    @Value("${EMQ.URL}")
    public String url;

    @RequestMapping("/isConnected")
    public String isConnected(@RequestParam(name = "DID") String DID){
        Map<String,String> para = new HashMap<>();
        para.put("clientid",DID);
        ResponseEntity<ApiClients> responseEntity = restTemplate.getForEntity(url+"/api/v4/clients?clientid={clientid}",
                ApiClients.class,para);

        System.out.println("获取响应的状态："+responseEntity.getStatusCode());
        System.out.println("获取响应的数据："+responseEntity.getBody());
        if (!responseEntity.getBody().getData().isEmpty()){
            return "connected";
        }

        return "disconnected";
    }



}
