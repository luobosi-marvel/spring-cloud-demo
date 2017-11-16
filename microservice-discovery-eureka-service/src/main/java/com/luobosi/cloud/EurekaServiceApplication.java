package com.luobosi.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * spring-boot 启动类
 * 开启 Eureka 服务使用该 @EnableEurekaServer 注解
 *
 * @author luobosi@2dfire.com
 * @since 2017-11-16
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
	}
}
