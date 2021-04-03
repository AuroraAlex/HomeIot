package com.cuit.homeiot.mapper;

import com.cuit.homeiot.pojo.DataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataMapper{
    List<DataDTO>  selectByClient_idAndAtBetweenTime(@Param("client_id") String client_id);
}
