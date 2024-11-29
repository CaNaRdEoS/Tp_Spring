package com.example.demo;

import org.springframework.boot.SpringApplication;

public class TestTp1Application {

	public static void main(String[] args) {
		SpringApplication.from(DemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
