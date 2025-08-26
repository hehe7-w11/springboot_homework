package com.oocl.springboot_exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.oocl.springboot_exercise")
@SpringBootApplication
public class SpringbootExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootExerciseApplication.class, args);
	}

}
