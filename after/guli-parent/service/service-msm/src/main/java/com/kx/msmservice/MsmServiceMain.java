package com.kx.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @ClassName MsmServiceMain
 * @Description TODO
 * @Author kuang
 * @Date 2022/10/19 19:14
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.kx"})
public class MsmServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(MsmServiceMain.class,args);
    }
}
