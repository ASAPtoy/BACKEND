package com.toy.asap_be.repository;

import com.toy.asap_be.entity.Post;
import com.toy.asap_be.entity.PostLike;
import com.toy.asap_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Boolean existsByUserAndPost(User user, Post post);
    List<PostLike> findAllByUser(User user);
    PostLike findByUserAndPost(User user, Post post);

}
