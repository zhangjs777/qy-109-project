package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/16 16:26
 * @description:
 **/

@SpringBootApplication
@MapperScan("com.aaa.mapper")
public class ApplicationRun8081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8081.class,args);
    }
}
