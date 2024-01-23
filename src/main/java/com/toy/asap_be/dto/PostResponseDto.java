package com.toy.asap_be.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostResponseDto {
    String nickname;
    String username;
    String title;
    String content;
    int price;
    String goodsImg;
    boolean negoCheck;
    String categoryName;
}
