package com.zxm.club.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * OSS模块启动类
 *
 * @author zxm
 * @date 2024/10/01
 */
@SpringBootApplication
@ComponentScan("com.zxm")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}
