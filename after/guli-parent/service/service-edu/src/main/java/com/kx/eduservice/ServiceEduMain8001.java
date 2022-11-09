package com.kx.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceEduMain8001
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/9 15:44
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.kx"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceEduMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduMain8001.class,args);
    }
}
