package com.cuit.homeiot.service;

import com.cuit.homeiot.mapper.DeviceListMapper;
import com.cuit.homeiot.pojo.Device;
import com.cuit.homeiot.utils.HttpApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceService {
    private final DeviceListMapper deviceListMapper;
    private final HttpApiUtil httpApiUtil;
    @Autowired
    public DeviceService(DeviceListMapper deviceListMapper, HttpApiUtil httpApiUtil) {
        this.deviceListMapper = deviceListMapper;
        this.httpApiUtil = httpApiUtil;
    }

    public List<Device> listAllDeviceAndCheckConnected(){
        List<Device> all = deviceListMapper.selectList(null);
        all.forEach(device -> {
            device.setIsConnected(httpApiUtil.isConnected(device.getDid().toString()));
            System.out.println(device);
        });
        return all;
    }

    @Transactional
    public Boolean addDevice(Device device){
        return deviceListMapper.insert(device) > 0;
    }

    @Transactional
    public Boolean delDevice(String did){
        Map<String, Object> col  = new HashMap<>();
        col.put("did",did);
        return deviceListMapper.deleteByMap(col) > 0;
    }

    @Transactional
    public Boolean batchDelDevice(String ids){
        String[] dids = ids.split(",");
        for(String did:dids){
            if (!delDevice(did)){
                return false;
            }
        }
        return true;
    }

    public Map<String,Object> getAllTypeNum(){
        Map<String,Object> map = new HashMap<>(10);
        Long ADNum =  deviceListMapper.countByTypeLong("AD");
        Long DDNum =  deviceListMapper.countByTypeLong("DD");
        Long SDNum =  deviceListMapper.countByTypeLong("SD");
        Long GWNum =  deviceListMapper.countByTypeLong("GW");
        map.put("AD",ADNum);
        map.put("DD",DDNum);
        map.put("SD",SDNum);
        map.put("GW",GWNum);
        return map;
    }


}
