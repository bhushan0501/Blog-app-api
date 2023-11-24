package com.bhushan.blog.payloads;

import java.util.Date;


import com.bhushan.blog.Entities.Category;
import com.bhushan.blog.Entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer id;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
}
