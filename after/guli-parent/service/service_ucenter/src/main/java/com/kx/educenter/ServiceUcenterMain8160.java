package com.kx.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ServiceUcenterMain8006
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/19 16:32
 */
@ComponentScan(basePackages = {"com.kx"})
@SpringBootApplication
@MapperScan("com.kx.educenter.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceUcenterMain8160 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcenterMain8160.class,args);
    }
}
