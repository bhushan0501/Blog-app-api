package com.bhushan.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhushan.blog.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
