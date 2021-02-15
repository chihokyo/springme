package com.springboot.boot_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 这是一个Spring boot应用 使用注解
 */
@SpringBootApplication
public class Boot01Application {

	public static void main(String[] args) {
		// 主程序类的方法
		SpringApplication.run(Boot01Application.class, args);
	}

}
