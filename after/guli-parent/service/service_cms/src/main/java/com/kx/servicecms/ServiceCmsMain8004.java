package com.kx.servicecms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ServiceCmsMain8004
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/23 15:46
 */
@SpringBootApplication
@ComponentScan({"com.kx"})
@MapperScan("com.kx.servicecms.mapper")
public class ServiceCmsMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsMain8004.class,args);
    }
}
