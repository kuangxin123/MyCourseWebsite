package com.kx.orderservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @ClassName OrderServiceMain8007
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/30 19:10
 */
@SpringBootApplication
@MapperScan("com.kx.orderservice.mapper")
@ComponentScan(basePackages = {"com.kx"})
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServiceMain8007 {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceMain8007.class,args);
    }

}
