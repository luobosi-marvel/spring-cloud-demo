package com.luobosi.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

/**
 * spring-boot 启动类
 *
 * @author luobosi@2dfire.com
 * @since 2017-11-16
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.luobosi.cloud")
@ImportResource("classpath:mapper/*.xml")
public class UserSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSystemApplication.class, args);
	}
}
