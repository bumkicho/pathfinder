package com.bkc.pathfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.bkc.pathfinder.config.AppConfig;

/**
 * 
 * @author bumki
 *
 */

/*
 * @EntityScan(basePackages = {"com.bkcdata.pathfinder.model"} )
 * @ComponentScan(basePackages = {"com.bkcdata.pathfinder.repository","com.bkcdata.pathfinder.service"}) 
 */
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@PropertySource("classpath:application-${spring.profiles.active:default}.yml")
public class PathfinderApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		SpringApplication.run(PathfinderApplication.class, args);
	}

}
