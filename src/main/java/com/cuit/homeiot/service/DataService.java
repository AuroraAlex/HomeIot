package com.cuit.homeiot.service;


import com.cuit.homeiot.mapper.DataMapper;
import com.cuit.homeiot.pojo.DataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    private final DataMapper dataMapper;

    @Autowired
    public DataService(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public List<DataDTO> getOneDayData(String clientId){
        return dataMapper.selectByClient_idAndAtBetweenTime(clientId);

    }

}
