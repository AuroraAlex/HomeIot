package com.cuit.homeiot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuit.homeiot.pojo.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//在对应的mapper上集成BaseMapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findByUserName(@Param("username") String username);
}
