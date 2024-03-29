package com.toy.asap_be.repository;

import com.toy.asap_be.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository <Category, Long> {

    List<Category> findAllByCategoryId(Category category);
    Optional<Category> findByCategoryName(String categoryName);

}