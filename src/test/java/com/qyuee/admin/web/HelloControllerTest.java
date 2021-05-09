package com.qyuee.admin.web;

import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 최근 Junit5에서는 @RunWith를 제공하지 않음
// @RunWith를 사용하고 싶으면 @ExtendWith를 사용해야하지만
// 이미 @SpringBootTest에 포함되어 있음
//@SpringBootTest -> 정상실행안됨
@WebMvcTest // @Runwith를 포함하고 있음
public class HelloControllerTest {

    // MockMvc: 가짜 객체를 생성하여 서버 배포없이 MVC동작을 재현 할 수 있는 클래스스
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // get, status, content를 static하게 import해야함
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "lee";
        int amount = 2021;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount));
    }
}