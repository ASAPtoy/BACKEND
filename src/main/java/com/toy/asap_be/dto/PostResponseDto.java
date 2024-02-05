package com.toy.asap_be.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class PostResponseDto {
    private String nickname;
    private String Title;
    private String Description; // content 필드를 description으로 변경
    private int price;
    private String goodsImg; // 이미지 경로를 저장할 수 있는 필드 추가
    private Boolean negoCheck;
    private String transactionMethod; // 거래 방식 추가
}


