package com.toy.asap_be.service;

import com.toy.asap_be.dto.MyPostLikeResponseDto;
import com.toy.asap_be.entity.Post;
import com.toy.asap_be.entity.PostLike;
import com.toy.asap_be.entity.User;
import com.toy.asap_be.repository.PostLikeRepository;
import com.toy.asap_be.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyDetailService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    //좋아요 목록에서 userId를 이용하여 post id 받아오기
    // postId를 이용하여 post들 찾아서 뷰에 뿌려주기
    public List<MyPostLikeResponseDto> showLikePost(User user) {

        List <MyPostLikeResponseDto> myPostLikeResponseDto = new ArrayList<>();
        List<PostLike> postLikeList = postLikeRepository.findAllByUser(user);
        for (PostLike postLike : postLikeList) {
            Post post = postRepository.findByPostId(postLike.getPost().getPostId()).orElse(null);
            Boolean likeCheck = postLikeRepository.existsByUserAndPost(user, post);
            assert post != null;
            MyPostLikeResponseDto responseDto = MyPostLikeResponseDto.builder()
                    .postId(post.getPostId())
                    .goodsImg(post.getGoodsImg())
                    .price(post.getPrice())
                    .title(post.getTitle())
                    .likeCheck(likeCheck)
                    .postLikes(post.getPostLikes())
                    .build();
            myPostLikeResponseDto.add(responseDto);
        }
        return myPostLikeResponseDto;
    }
}