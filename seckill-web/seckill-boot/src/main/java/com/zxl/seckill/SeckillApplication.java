package com.zxl.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * 启动类
 * <p>
 *
 * @className: com.zxl.seckill.SeckillApplication
 * @author: zxl
 * @date: 2023-02-12
 */
@SpringBootApplication
@EnableFeignClients
public class SeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }
}
