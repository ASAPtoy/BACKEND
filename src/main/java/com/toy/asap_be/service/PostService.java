package com.toy.asap_be.service;
import com.toy.asap_be.dto.PostDetailDto;
import com.toy.asap_be.dto.PostInsideDto;
import com.toy.asap_be.dto.PostRequestDto;
import com.toy.asap_be.dto.PostResponseDto;
import com.toy.asap_be.entity.Category;
import com.toy.asap_be.entity.Post;
import com.toy.asap_be.entity.User;
import com.toy.asap_be.repository.CategoryRepository;
import com.toy.asap_be.repository.PostLikeRepository;
import com.toy.asap_be.repository.PostRepository;
import com.toy.asap_be.security.UserDetailsImpl;
import com.toy.asap_be.validator.UserInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CategoryRepository categoryRepository;


    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {

        String categoryName = postRequestDto.getCategoryName();
        Category category= categoryRepository.findByCategoryName(categoryName).orElseThrow(
                () -> new NullPointerException("해당 카테고리명이 존재하지 않습니다.")
        );

        Post post = Post.builder()
                .user(user)
                .goodsImg(postRequestDto.getGoodsImg())
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .negoCheck(postRequestDto.getNegoCheck())
                .category(category)
                .price(postRequestDto.getPrice())
                .build();

        postRepository.save(post);

        return PostResponseDto.builder()

                .nickname(user.getNickname())
                .username(user.getUsername())
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .categoryName(category.getCategoryName())
                .goodsImg(postRequestDto.getGoodsImg())
                .price(postRequestDto.getPrice())
                .negoCheck(postRequestDto.getNegoCheck())
                .build();
    }

    @Transactional      //리스트로 보내기
    public PostDetailDto showDetail(Long postId, User user) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 게시글 정보가 존재하지 않습니다.")
        );

        Post postMain = postRepository.findById(postId).orElse(null);

        List<Post> postList = postRepository.findAllByUserOrderByPostIdDesc(postMain.getUser());

        List<PostInsideDto> postInsideDtos = new ArrayList<>();
        for (Post insidePost : postList) {
            if (postInsideDtos.size() == 4) break;
            if (insidePost.getPostId().equals(postId)) continue;

            Long insideId = insidePost.getPostId();
            String title = insidePost.getTitle();
            int price = insidePost.getPrice();
            String goodsImg = insidePost.getGoodsImg();

            postInsideDtos.add(new PostInsideDto(insideId, title, price, goodsImg));
        }

        Boolean likeCheck = postLikeRepository.existsByUserAndPost(user, post);
        System.out.println(likeCheck);

        postRepository.upVisitCnt(postId);

        return PostDetailDto.builder()
                .postId(postId)
                .nickname(postMain.getUser().getNickname())
                .username(postMain.getUser().getUsername())
                .title(post.getTitle())
                .content(post.getContent())
                .categoryName(post.getCategory().getCategoryName())
                .goodsImg(post.getGoodsImg())
                .price(post.getPrice())
                .negoCheck(post.getNegoCheck())
                .visitCount(post.getVisitCount())
                .createdAt(post.getCreatedAt())
                .insideList(postInsideDtos)
                .likeCheck(likeCheck)
                .postLike(post.getPostLikes())
                .build();
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findByUserAndPostId(user, postId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        Category category= categoryRepository.findByCategoryName(postRequestDto.getCategoryName()).orElseThrow(
                () -> new NullPointerException("해당 카테고리명이 존재하지 않습니다.")
        );
        post.update(postRequestDto, category);
    }

    @Transactional
    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        User user = UserInfoValidator.userDetailsIsNull(userDetails);

        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            throw new NullPointerException("유효하지 않거나 이미 삭제된 글입니다.");
        }
        if (!user.getId().equals(post.get().getUser().getId())) {
            throw new IllegalArgumentException("당신의 게시글이 아닙니다.");
        }
        postRepository.deleteById(postId);
    }

    @Transactional
    public List<String> setCategory(){

        List<String> categoryList = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category :categories) {
            categoryList.add(category.getCategoryName());
        }
        return categoryList;
    }
}
