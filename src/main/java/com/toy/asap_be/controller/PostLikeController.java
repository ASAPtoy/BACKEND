package com.toy.asap_be.controller;

import com.toy.asap_be.security.UserDetailsImpl;
import com.toy.asap_be.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/postLike/{postId}")
    public void clickPostLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
//        if(userDetails == null) {
//            throw new IllegalArgumentException("회원가입 후 이용해주세요");
//        }
        postLikeService.clickPostLike(userDetails.getUser(), postId);
    }

}