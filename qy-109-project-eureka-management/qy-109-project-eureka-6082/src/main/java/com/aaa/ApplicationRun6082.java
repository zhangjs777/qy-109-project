package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/15 19:20
 * @description:
 **/

@SpringBootApplication
@EnableEurekaServer
public class ApplicationRun6082 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6082.class,args);
    }
}
