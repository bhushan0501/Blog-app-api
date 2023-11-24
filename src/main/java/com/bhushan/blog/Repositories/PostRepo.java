package com.bhushan.blog.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhushan.blog.Entities.Category;
import com.bhushan.blog.Entities.Post;
import com.bhushan.blog.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

}
