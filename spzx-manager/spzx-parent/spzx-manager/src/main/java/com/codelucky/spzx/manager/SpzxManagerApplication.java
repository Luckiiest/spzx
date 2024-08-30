package com.codelucky.spzx.manager;

import com.codelucky.spzx.common.log.annotation.EnableLogAspect;
import com.codelucky.spzx.manager.properties.MinioProperties;
import com.codelucky.spzx.manager.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableLogAspect
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = "com.codelucky.spzx")
@SpringBootApplication
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
public class SpzxManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpzxManagerApplication.class, args);
    }
}
