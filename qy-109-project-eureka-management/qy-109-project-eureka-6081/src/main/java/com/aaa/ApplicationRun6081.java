package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/15 19:16
 * @description:
 **/

@SpringBootApplication
@EnableEurekaServer
public class ApplicationRun6081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6081.class,args);
    }
}
