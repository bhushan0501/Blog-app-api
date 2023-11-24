package com.bhushan.blog.services;

import java.util.List;

import com.bhushan.blog.Entities.Post;
import com.bhushan.blog.payloads.PostDto;
import com.bhushan.blog.payloads.PostResponse;



public interface PostService {
	//create
	PostDto createpost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatepost(PostDto postDto, Integer postId);
	//delete
	void deletepost(Integer postId);
	//get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize);
	//get single post
	//PostDto getPostById(Integer postId);
	PostDto getPostById(Integer id);
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	//seaarch post
	List<Post> searchPosts(String keyword);

	//List<PostDto> findAllPost();
	 
	
	
	
}
