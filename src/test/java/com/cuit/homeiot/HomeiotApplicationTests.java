package com.cuit.homeiot;

import com.cuit.homeiot.mapper.DeviceListMapper;
import com.cuit.homeiot.mapper.SysUserMapper;
import com.cuit.homeiot.pojo.ApiClients;
import com.cuit.homeiot.pojo.Switch;
import com.cuit.homeiot.service.DataService;
import com.cuit.homeiot.service.DeviceService;
import com.cuit.homeiot.service.RuleService;
import com.cuit.homeiot.service.SwitchService;
import com.cuit.homeiot.utils.HttpApiUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HomeiotApplicationTests {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DeviceListMapper deviceListMapper;

    @Transactional
    @Test
    void contextLoads() {
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
        String result = encoder.encode("admin");
        String result2 = encoder.encode("user");
        System.out.println(result);
        System.out.println(result2);
    }
    @Test
    void testAPI(){
        Map<String,String> para = new HashMap<>();
        para.put("clientid","1");
        ResponseEntity<ApiClients> responseEntity = restTemplate.getForEntity("http://192.168.220.128:8081/api/v4/clients?clientid={clientid}",
                ApiClients.class,para);
        System.out.println("获取响应的状态："+responseEntity.getStatusCode());
        System.out.println("获取响应的数据："+responseEntity.getBody());

    }

    @Autowired
    private HttpApiUtil httpApiUtil;

    @Test
    @Transactional
    void testALL(){
        deviceListMapper.selectList(null).forEach(device -> {
            device.setIsConnected(httpApiUtil.isConnected(device.getDid().toString()));
            System.out.println(device);
        });
    }

    @Autowired
    DeviceService deviceService;

    @Test
    void testDel(){
        System.out.println(deviceService.delDevice("1111111"));
    }

    @Test
    void testType(){
        System.out.println(deviceService.getAllTypeNum());
    }
    @Test
    void testBatchDel(){
//        System.out.println(deviceService.batchDelDevice(Arrays.asList("111,222".split(","))));
        System.out.println(deviceService.batchDelDevice("111,222"));
    }

    @Autowired
    DataService dataService;

    @Test
    void testData(){
        System.out.println(dataService.getOneDayData("89cac68c4497466c8e36e92f1207e97c"));
    }

    @Autowired
    SwitchService switchService;
    @Test
    void testSw(){
        System.out.println(switchService.getKeys("a8e1c779b555480f9dc5c9580a6a1e21"));
    }

    @Autowired
    RuleService ruleService;

    @Test
    void testAdd(){
        System.out.println(ruleService.addRule("da8880a8194245a6b6aefc57b804bd09",
                "435c758a0ec34bb89bb145b1fe3e7405",
                "{\"SW\":1}",
                "MQ2=1","测试可燃气"));
    }

    @Test
    void testAdd2(){
        System.out.println(ruleService.addRule("da8880a8194245a6b6aefc57b804bd09",
                "435c758a0ec34bb89bb145b1fe3e7405",
                "{\"SW\":0}",
                "MQ2=0","测试可燃气"));
    }
    @Test
    void testDelR(){
        ruleService.delRule("rule:e0851535");
    }

}
