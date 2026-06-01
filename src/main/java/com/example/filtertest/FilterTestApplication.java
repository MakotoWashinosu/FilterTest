package com.example.filtertest;

import com.example.filtertest.config.SysCheckProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SysCheckProperties.class)
public class FilterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterTestApplication.class, args);
    }
}
