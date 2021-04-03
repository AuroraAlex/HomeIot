package com.cuit.homeiot.utils;

import com.cuit.homeiot.pojo.ApiClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpApiUtil {

    private final RestTemplate restTemplate;

    @Autowired
    public HttpApiUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Boolean isConnected(String DID){
        Map<String,String> para = new HashMap<>();
        para.put("clientid",DID);
        ResponseEntity<ApiClients> responseEntity = restTemplate.getForEntity("http://192.168.220.128:8081/api/v4/clients?clientid={clientid}",
                ApiClients.class,para);

        System.out.println("获取响应的状态："+responseEntity.getStatusCode());
        System.out.println("获取响应的数据："+responseEntity.getBody());
        return !responseEntity.getBody().getData().isEmpty();
    }


}
