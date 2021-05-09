package com.qyuee.admin.web.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter

// 선언된 모든 final 필드가 포함된 생성자를 생성
// final이 없는 필드는 생성자에서 제외
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
