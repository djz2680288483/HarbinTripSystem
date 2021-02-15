package com.djz;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 默尘
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.djz.mapper")
//@EnableDubbo
public class HarbinTripSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarbinTripSystemApplication.class, args);
    }

}
