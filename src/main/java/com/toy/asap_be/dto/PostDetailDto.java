package com.toy.asap_be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDetailDto {
    private Long postId;
    private String nickname;
    private String username;
    private String title;
    private String content;
    private int price;
    private String Description;
    private String goodsImg;
    private Boolean negoCheck;
    private Boolean likeCheck;
    private Integer visitCount;
    private String categoryName;
    private int postLike;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;


    private List<PostInsideDto> insideList;
}
