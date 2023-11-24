package com.bhushan.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhushan.blog.payloads.ApiResponse;
import com.bhushan.blog.payloads.CategoryDto;
import com.bhushan.blog.payloads.UserDto;
import com.bhushan.blog.services.CategoryService;
import com.bhushan.blog.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
//@Tag(name = "category Controller", description = "APIs for managing category")
@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	//create
//	@Operation
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategoryDto =  this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
	}
	//update
	//@Operation
	@PostMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
		CategoryDto updateCategory =  this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);}
	//delete
	//@Operation
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		 this.categoryService.deleteCategory(catId);
		 return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);}
//get
	//@Operation
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto>getCategory (@PathVariable Integer catId){
		CategoryDto CategoryDto =  this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(CategoryDto, HttpStatus.OK);}
	
	//get all
	//@Operation
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getCategories (){
		List<CategoryDto> categories = this.categoryService.getCategories();
		return ResponseEntity.ok(categories);}

	
	}