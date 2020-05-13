package com.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링부트 및 스프링 bean관리 자동 설정
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 was 실행
    }
}
