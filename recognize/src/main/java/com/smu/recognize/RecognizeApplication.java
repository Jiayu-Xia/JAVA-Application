package com.smu.recognize;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.smu.recognize.dao") //扫描的mapper
@SpringBootApplication
public class RecognizeApplication{
    public static void main(String[] args) {
        SpringApplication.run(RecognizeApplication.class, args);
    }

}
