package com.example.backend.Payload;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class UserDto {
	

	private int userId;
	

	private String name;
	

	private String password;
	private String email;

	private String address;
	private String about;

	private String gender;
	
	private String phone;

	private Date date;
	private boolean active;

}
