package com.manning.readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  启动引导类 & 配置类
 */
@SpringBootApplication  // 开启组件扫描和自动配置
public class ReadingListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadingListApplication.class, args);  // 负责启动引导应用程序
    }
}
