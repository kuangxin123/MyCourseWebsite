package com.kx.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @ClassName StaServiceMain8008
 * @Description TODO
 * @Author kuang
 * @Date 2022/11/4 22:54
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.kx"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.kx.staservice.mapper")
@EnableScheduling
public class StaServiceMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(StaServiceMain8008.class,args);
    }
}
