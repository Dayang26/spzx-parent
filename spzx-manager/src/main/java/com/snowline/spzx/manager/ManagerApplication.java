package com.snowline.spzx.manager;


import com.snowline.spzx.manager.properties.MinioProperties;
import com.snowline.spzx.manager.properties.UserProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Snow
 * @create 2024-01-13 2:12
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.snowline.spzx"})
@EnableConfigurationProperties(value = {UserProperties.class, MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
