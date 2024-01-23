package com.toy.asap_be.entity;

import com.toy.asap_be.dto.user.SignupRequestDto;
import com.toy.asap_be.validator.UserInfoValidator;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //userId == 사용자 ID
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(columnDefinition = "varchar(255) default 'default.img'")
    private String profileImg;

    public User(SignupRequestDto requestDto, String enPassword) {
        UserInfoValidator.validateUserInfoInput(requestDto);
        this.username = requestDto.getUsername();
        this.nickname = requestDto.getNickname();
        this.password = enPassword;
        this.profileImg = requestDto.getProfileImg();
    }

    public User(String nickname, String encodedPassword, String username) {
        this.username = username;
        this.nickname = nickname;
        this.password = encodedPassword;
    }
}

