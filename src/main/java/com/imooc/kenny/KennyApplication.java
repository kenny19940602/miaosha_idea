package com.imooc.kenny;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动类
 * @author kenny
 */
@SpringBootApplication
@MapperScan("com.imooc.kenny.mapper")
public class KennyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(KennyApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(KennyApplication.class);
    }

}
