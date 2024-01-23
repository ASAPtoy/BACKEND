package com.toy.asap_be.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostRequestDto {
    private String title;
    private String content;
    private int price;
    private String goodsImg;
    private Boolean negoCheck;
    private String categoryName;
}
