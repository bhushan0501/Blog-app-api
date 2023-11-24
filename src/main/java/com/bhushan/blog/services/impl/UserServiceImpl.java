package com.bhushan.blog.services.impl;

import com.bhushan.blog.Entities.User;
import com.bhushan.blog.Repositories.UserRepo;
import com.bhushan.blog.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhushan.blog.payloads.UserDto;
import com.bhushan.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
       User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User existingUser = this.userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // Update the attributes of the existingUser with data from userDto
        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setAbout(userDto.getAbout());

        // Save the updated entity
        User updatedUser = this.userRepo.save(existingUser);

        return this.userToDto(updatedUser);
    }



    @Override
    public UserDto getUserById(Integer userId) {
    	User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
     return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        // TODO: Implement the get all users logic
    	List<User> users = this.userRepo.findAll();
    	List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        // TODO: Implement the delete user logic
    	User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
    	this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return user;
    }
    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }

}
