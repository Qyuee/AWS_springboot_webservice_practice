package com.qyuee.admin.web.Dto;

// dto 객체가 생성되지 않는 이슈
// https://github.com/jojoldu/freelec-springboot2-webservice/issues/2

// variable name not initialized in the default constructor 이슈
// https://kkambi.tistory.com/155

// gradle 5.X 이상에서의 lombok 이슈
// https://eblo.tistory.com/70

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        // Junit의 assertThat이 아닌 assertj의 것
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}