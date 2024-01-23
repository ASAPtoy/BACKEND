package com.toy.asap_be.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "pr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prId;

    @Column(name = "pr_reg_date")
    private String prDate;

    @Column(name = "pr_title")
    private String prTitle;

    @Column(name = "pr_category")
    private String prCateg;

    @Column(name = "pr_region")
    private String prRegion;

    @Column(name = "pr_price")
    private String prPrice;

    @Column(name = "pr_descript")
    private String prDes;

    @Column(name = "pr_img_1")
    private String prImage1;
    @Column(name = "pr_img_2")
    private String prImage2;
    @Column(name = "pr_img_3")
    private String prImage3;
    @Column(name = "pr_img_4")
    private String prImage4;
    @Column(name = "pr_img_5")
    private String prImage5;

    @Builder
    public Product(int prId, String prDate, String prTitle, String prCateg, String prRegion, String prPrice, String prDes, String prImage1, String prImage2, String prImage3, String prImage4, String prImage5) {
        this.prId = prId;
        this.prDate = prDate;
        this.prTitle = prTitle;
        this.prCateg = prCateg;
        this.prRegion = prRegion;
        this.prPrice = prPrice;
        this.prDes = prDes;
        this.prImage1 = prImage1;
        this.prImage2 = prImage2;
        this.prImage3 = prImage3;
        this.prImage4 = prImage4;
        this.prImage5 = prImage5;
    }

}


