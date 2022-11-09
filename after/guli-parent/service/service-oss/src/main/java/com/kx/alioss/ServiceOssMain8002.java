package com.kx.alioss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ServiceOssMain8002
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/14 14:35
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.kx")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceOssMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOssMain8002.class,args);
    }
}
