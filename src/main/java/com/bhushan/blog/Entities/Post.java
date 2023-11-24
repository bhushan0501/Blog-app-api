package com.bhushan.blog.Entities;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Post")
@Setter
@Getter
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer postId;
	@Column(name= "Post Title")
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	@ManyToOne
	private Category category;
	@ManyToOne
	private User user;
}
