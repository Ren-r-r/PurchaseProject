package com.ryz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ryz.mapper")
public class PurchaseprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseprojectApplication.class, args);
    }

}
