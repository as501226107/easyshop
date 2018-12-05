package com.dragon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dragon.mapper")
public class EasyshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyshopApplication.class, args);
    }
}
