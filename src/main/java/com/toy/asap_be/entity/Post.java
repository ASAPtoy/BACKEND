package com.toy.asap_be.entity;

import com.toy.asap_be.dto.PostRequestDto;
import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false) // , columnDefinition = "LONGTEXT"
    private String content;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String goodsImg;

    @Column(nullable = false)
    private Boolean negoCheck;

    @JsonIgnoreProperties({"post"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostLike> likeList;

    @Column
    private int postLikes;

    @Column
    private Integer visitCount;

    @ManyToOne
    @JoinColumn
    private Category category;

    public void update(PostRequestDto postRequestDto, Category category) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.price = postRequestDto.getPrice();
        this.goodsImg = postRequestDto.getGoodsImg();
        this.negoCheck = postRequestDto.getNegoCheck();
        this.category = category;


    }

    @PrePersist
    public void prePersist() {
        this.visitCount = this.visitCount == null ? 1 : this.visitCount;
    }

}
