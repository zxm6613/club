package com.zxm.club.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题模块启动类
 *
 * @author zxm
 * @date 2024/10/01
 */
@SpringBootApplication
@ComponentScan("com.zxm")
@MapperScan("com.zxm.**.mapper")
public class SubjectApplication {
    // TODO: 数据库表设计中的题目标签分类三者的关系设计巧妙，很值得思考
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class);
    }
}
