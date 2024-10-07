package com.zxm.club.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 权限模块启动类
 *
 * @author zxm
 * @date 2024/10/01
 */
@SpringBootApplication
@ComponentScan("com.zxm")
@MapperScan("com.zxm.**.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
