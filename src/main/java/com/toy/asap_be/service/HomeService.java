package com.toy.asap_be.service;

import com.toy.asap_be.dto.HomeResponseDto;
import com.toy.asap_be.entity.Post;
import com.toy.asap_be.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final PostRepository postRepository;

    public List<HomeResponseDto> findAllPosts(PageRequest pageable) {
        List<Post> posts = postRepository.findAllByOrderByPostIdDesc(pageable).getContent();
        List<HomeResponseDto> allPosts = new ArrayList<>();
        for (Post post : posts) {
            HomeResponseDto responseDto = createPostDto(post);
            allPosts.add(responseDto);
        }
        return allPosts;
    }
    private HomeResponseDto createPostDto(Post post) {

        return new HomeResponseDto(
                post.getPostId(),
                post.getUser().getUsername(),
                post.getUser().getNickname(),
                post.getTitle(),
                post.getPrice(),
                post.getGoodsImg(),
                post.getPostLikes(),
                post.getCreatedAt()
        );
    }
}
