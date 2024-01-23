package com.toy.asap_be.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInfoDto {
    private String username;
    private String nickname;
    private String password;
}