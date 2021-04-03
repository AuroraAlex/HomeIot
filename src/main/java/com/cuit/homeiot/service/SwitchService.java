package com.cuit.homeiot.service;

import com.cuit.homeiot.mapper.SwitchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class SwitchService {

    private final SwitchMapper switchMapper;

    @Autowired
    public SwitchService(SwitchMapper switchMapper) {
        this.switchMapper = switchMapper;
    }

    public List<String> getKeys(String did){
        return  Arrays.asList(switchMapper.getKeyByDid(did).getKeys().split(","));
    }
}
