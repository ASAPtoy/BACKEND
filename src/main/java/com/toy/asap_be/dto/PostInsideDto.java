package com.toy.asap_be.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostInsideDto {
    private Long postId;
    private String title;
    private int price;
    private String goodsImg;
}
