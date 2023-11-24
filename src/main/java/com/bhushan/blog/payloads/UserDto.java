package com.bhushan.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 4, message = "User name must be of 4 character")
	private String name;
	@Email(message="your email is not valid")
	private String email;
	@NotEmpty
	@Size(min = 4, message="Password must be minimum of 4")
	private String password;
	@NotEmpty
	private String about;

}
