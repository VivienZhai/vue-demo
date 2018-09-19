package com.inspur.viz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.loushang", "com.inspur.viz" })
@MapperScan({ "org.loushang.*", "com.inspur.viz" })
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
