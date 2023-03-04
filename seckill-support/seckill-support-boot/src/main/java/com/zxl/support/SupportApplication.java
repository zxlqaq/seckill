package com.zxl.support;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>
 * 启动类
 * <p>
 *
 * @className: SupportApplication
 * @author: zxl
 * @date: 2023-02-13
 */
@SpringBootApplication
@MapperScan("com.zxl.support.mapper")
@EnableDiscoveryClient
public class SupportApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupportApplication.class, args);
    }
}
