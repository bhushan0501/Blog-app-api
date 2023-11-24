package com.bhushan.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhushan.blog.payloads.ApiResponse;
import com.bhushan.blog.payloads.PostDto;
import com.bhushan.blog.payloads.PostResponse;
import com.bhushan.blog.services.PostService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
//@Tag(name = "post Controller", description = "APIs for managing post")
@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	
    
	//crete
	//@Tag(name = "")
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> cretePost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		PostDto createPost = this.postService.createpost(postDto,userId,categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	//get by user
	//@Tag(name = "")
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
	//get by category
	//@Tag(name = "")
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
}
	//get all posts
	//@Tag(name = "")
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber", defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue = "10", required = false) Integer pageSize
			
			
			)
	{
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}
	//update post
	//@Tag(name = "")

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
	    PostDto updatedPost = this.postService.updatepost(postDto, postId);
	    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
	}
	
	//delete post
	//@Tag(name = "")
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		this.postService.deletepost(postId);
		return new ApiResponse("Post is deleted!!", true);
	}
	//get post by id
	//@Tag(name = "")
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId) {
   PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
}
	
	
	
}
