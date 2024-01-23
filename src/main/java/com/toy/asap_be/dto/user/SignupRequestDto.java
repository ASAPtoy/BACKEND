package com.toy.asap_be.dto.user;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;
    private String profileImg;
}