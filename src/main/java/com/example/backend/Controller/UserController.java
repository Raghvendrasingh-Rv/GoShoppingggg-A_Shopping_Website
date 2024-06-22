package com.example.backend.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Payload.ApiResponse;
import com.example.backend.Payload.UserDto;
import com.example.backend.Service.UserService;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@RequestBody UserDto newUser) {
		Date date  = new Date();
		newUser.setDate(date);
		UserDto saved = this.userService.create(newUser);
		return new ResponseEntity<UserDto>(saved, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<UserDto>> viewAllUser(){
		List<UserDto> list = this.userService.viewAllUser();
		return new ResponseEntity<List<UserDto>>(list,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{userId}")
	public ResponseEntity<UserDto> viewById(@PathVariable int userId){
		UserDto found = this.userService.viewById(userId);
		return new ResponseEntity<UserDto>(found, HttpStatus.ACCEPTED);
	}

	@PostMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable int userId){
		this.userService.delete(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> update(@RequestBody UserDto newUser, @PathVariable int userId){
		UserDto saved = this.userService.update(newUser, userId);
		return new ResponseEntity<UserDto>(saved, HttpStatus.ACCEPTED);
	}
}
