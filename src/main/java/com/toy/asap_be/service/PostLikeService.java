package com.toy.asap_be.service;

import com.toy.asap_be.entity.Post;
import com.toy.asap_be.entity.PostLike;
import com.toy.asap_be.entity.User;
import com.toy.asap_be.repository.PostLikeRepository;
import com.toy.asap_be.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public void clickPostLike(User user, Long postId){
        Post post = postRepository.findByPostId(postId).orElseThrow(null);

        PostLike existLike = postLikeRepository.findByUserAndPost(user, post);
        if(postLikeRepository.existsByUserAndPost(user, post)) {     //해당 글에 좋아요 누른 상태 체크
            postLikeRepository.deleteById(existLike.getPostLikeId());   //postlike id 삭제
            postRepository.downLikeCnt(postId);
        }
        else{
            PostLike postLike = PostLike.builder()
                    .post(post)
                    .user(user)
                    .build();
            postLikeRepository.save(postLike);
            postRepository.upLikeCnt(postId);
        }
        postRepository.save(post);
    }
}