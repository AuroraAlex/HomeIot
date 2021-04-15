package com.cuit.homeiot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuit.homeiot.pojo.Rule;
import com.cuit.homeiot.pojo.RuleList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleListMapper extends BaseMapper<RuleList> {
    int deleteByRid(@Param("rid") String rid);
}
