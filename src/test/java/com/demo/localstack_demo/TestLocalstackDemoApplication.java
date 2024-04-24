package com.demo.localstack_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestLocalstackDemoApplication {

    public static void main(String[] args) {
        SpringApplication.from(LocalstackDemoApplication::main)
            .with(TestLocalstackDemoApplication.class).run(args);
    }

}
