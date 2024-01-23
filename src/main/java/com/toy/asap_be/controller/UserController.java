package com.toy.asap_be.controller;

import com.toy.asap_be.dto.user.*;
import com.toy.asap_be.entity.User;
import com.toy.asap_be.security.UserDetailsImpl;
import com.toy.asap_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);

        return ResponseEntity.ok()
                .body("회원가입 완료");
    }

    @PostMapping("/userInfo")
    public ResponseEntity<UserInfoDto> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        UserInfoDto userInfoDto = new UserInfoDto(user.getUsername(), user.getNickname(), user.getPassword());
        return ResponseEntity.ok()
                .body(userInfoDto);
    }

    @PostMapping("/user/checkId")
    public ResponseEntity<CheckIdResponseDto> checkId(@RequestBody CheckIdRequestDto checkIdRequestDto) {
        CheckIdResponseDto checkIdResponseDto = userService.checkId(checkIdRequestDto);

        return ResponseEntity.ok()
                .body(checkIdResponseDto);
    }

    @PostMapping("/user/checkNickname")
    public ResponseEntity<CheckNicknameResponseDto> checkNickname(@RequestBody CheckNicknameRequestDto checkNicknameRequestDto) {
        CheckNicknameResponseDto checkNicknameResponseDto = userService.checkNickname(checkNicknameRequestDto);

        return ResponseEntity.ok()
                .body(checkNicknameResponseDto);
    }
}
