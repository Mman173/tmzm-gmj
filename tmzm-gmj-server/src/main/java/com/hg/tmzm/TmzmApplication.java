package com.hg.tmzm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 工模具管理系统启动类
 *
 * @author hg
 */
@SpringBootApplication
@MapperScan("com.hg.tmzm.mapper")
public class TmzmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmzmApplication.class, args);
    }
}
