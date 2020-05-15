package com.project.springboot.web;

import com.project.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //JUnit 내장 외 다른 실행자 실행 (연결자)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void helloTest() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")) //MockMvc를 통해 Http Get 요청
                .andExpect(status().isOk())
                .andExpect(content().string(hello)); //http 상태(ok-200)와 결과 검증
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDtoTest() throws Exception {
        String name = "name";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) //String만 허용
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //json 응답값 필드별 검증
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
