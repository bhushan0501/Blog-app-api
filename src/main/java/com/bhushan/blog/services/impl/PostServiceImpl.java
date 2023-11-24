package com.bhushan.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bhushan.blog.Entities.Category;
import com.bhushan.blog.Entities.Post;
import com.bhushan.blog.Entities.User;
import com.bhushan.blog.Repositories.CategoryRepo;
import com.bhushan.blog.Repositories.PostRepo;
import com.bhushan.blog.Repositories.UserRepo;
import com.bhushan.blog.exception.ResourceNotFoundException;
import com.bhushan.blog.payloads.PostDto;
import com.bhushan.blog.payloads.PostResponse;
import com.bhushan.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	

	@Override
	public PostDto createpost(PostDto postDto,Integer userId,Integer categoryId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id", userId));
		
		Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","Category id", categoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatepost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletepost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber,pageSize);
		Page<Post> pagePost = this.postRepo.findAll(p);
	List<Post> allPosts  =	pagePost.getContent();
	List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	PostResponse postResponse = new PostResponse();
	postResponse.setContent(postDtos);
	postResponse.setPageNumber(pagePost.getNumber());
	postResponse.setPageSize(pagePost.getSize());
	postResponse.setTotalElements(pagePost.getTotalElements());
	postResponse.setTotalPages(pagePost.getTotalPages());
	postResponse.setLastPage(pagePost.isLast());
	return postResponse;
	}

	@Override
	//public PostDto getPostById(Integer postId)
	public PostDto getPostById(Integer id)
 {
	    Post post = postRepo.findById(id)
	                        .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", id));
	    return this.modelMapper.map(post, PostDto.class);
}
	
//public PostDto getPostById(Integer id)


	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
List<Post> posts = this.postRepo.findByUser(user);
List<PostDto> postDtos=posts.stream()
  .map(post -> modelMapper.map(post, PostDto.class))
  .collect(Collectors.toList());
return postDtos;
		
	}

	@Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
                                  .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
       List<PostDto> postDtos=posts.stream()
                    .map(post -> modelMapper.map(post, PostDto.class))
                    .collect(Collectors.toList());
       return postDtos;
    }

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	

}
