package com.cuit.homeiot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//扫描mapper文件夹
@MapperScan("com.cuit.homeiot.mapper")
@SpringBootApplication
public class HomeiotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeiotApplication.class, args);

    }


}
