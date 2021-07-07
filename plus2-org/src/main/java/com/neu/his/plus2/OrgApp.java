package com.neu.his.plus2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: TODO
 * @Author: tanchuyue
 * @Date: 21-7-7
 */
@SpringBootApplication
@EnableEurekaClient
public class OrgApp {
    public static void main(String[] args) {
        SpringApplication.run(OrgApp.class, args);
    }
}
