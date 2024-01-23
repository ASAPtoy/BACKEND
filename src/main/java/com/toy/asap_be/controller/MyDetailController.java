package com.toy.asap_be.controller;

import com.toy.asap_be.dto.MyPostLikeResponseDto;
import com.toy.asap_be.security.UserDetailsImpl;
import com.toy.asap_be.service.MyDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyDetailController {
    private final MyDetailService myDetailService;

    //관심목록 추가 한 게시물 불러오기
    @GetMapping("/postLike")
    public List<MyPostLikeResponseDto> showLikePost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myDetailService.showLikePost(userDetails.getUser());
    }

}
