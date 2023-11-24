package com.bhushan.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NotEmpty
	private String categoryTitle;
	@NotEmpty
	@Size(min = 10, message = "Description should me more than 10 character")
	private String categoryDescription;
	

}
