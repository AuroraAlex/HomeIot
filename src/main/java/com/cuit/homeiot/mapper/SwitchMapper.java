package com.cuit.homeiot.mapper;

import com.cuit.homeiot.pojo.Switch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwitchMapper {
    Switch getKeyByDid(@Param("did") String did);
}
