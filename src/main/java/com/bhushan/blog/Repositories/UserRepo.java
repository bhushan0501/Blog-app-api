package com.bhushan.blog.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhushan.blog.Entities.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer>{
	Optional<User>  findByEmail(String email);
}
