package com.cuit.homeiot.service;

import com.cuit.homeiot.mapper.SysUserMapper;
import com.cuit.homeiot.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomUserService implements UserDetailsService {

    private final SysUserMapper sysUserMapper;

    @Autowired
    public CustomUserService(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.findByUserName(username);

        if (user != null){
            return user;
        }else {
            throw new UsernameNotFoundException(username +"do not exists!");
        }

    }
}
