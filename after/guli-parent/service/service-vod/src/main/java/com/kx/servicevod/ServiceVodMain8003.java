package com.kx.servicevod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ServiceVodMain8003
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/21 21:13
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.kx"})
@MapperScan("com.kx.servicevod.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceVodMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodMain8003.class,args);
    }
}
