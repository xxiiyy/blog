package com.lhd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 启动类
 */

//springboot标识
@SpringBootApplication
//扫描mapper
@MapperScan("com.lhd.mapper")
public class BlogApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		//程序启动入口
		SpringApplication.run(BlogApplication.class, args);
	}


	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(BlogApplication.class);
	}
}
