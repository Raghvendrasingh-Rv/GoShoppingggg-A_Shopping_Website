package com.example.backend.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.User;
import com.example.backend.Payload.UserDto;
import com.example.backend.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDto create(UserDto newUser) {
		User entity = this.mapper.map(newUser, User.class);
		String pass = entity.getPassword();
		String encodedPass = this.passwordEncoder.encode(pass);
		entity.setPassword(encodedPass);
		User saved = this.userRepository.save(entity);
		UserDto dto = this.mapper.map(saved, UserDto.class);
		return dto;
	}
	
	public List<UserDto> viewAllUser(){
		List<User> list = this.userRepository.findAll();
		List<UserDto> dtoList = list.stream().map(user -> this.mapper.map(user, UserDto.class)).collect(Collectors.toList());
		return dtoList;
	}
	
	public UserDto viewById(int userId) {
		User found = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		UserDto dto = this.mapper.map(found, UserDto.class);
		return dto;
	}
	
	public void delete(int userId) {
		User found = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		this.userRepository.delete(found);
	}
	
	public UserDto update(UserDto newUser, int userId) {
		User oldUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		User entity = this.mapper.map(newUser, User.class);
		oldUser.setName(entity.getName());
		oldUser.setPassword(entity.getPassword());
		oldUser.setGender(entity.getGender());
		oldUser.setEmail(entity.getEmail());
		oldUser.setPhone(entity.getPhone());
		oldUser.setAddress(entity.getAddress());
		oldUser.setAbout(entity.getAbout());
		oldUser.setActive(entity.isActive());
		
		User saved = this.userRepository.save(oldUser);
		UserDto dto = this.mapper.map(saved, UserDto.class);
		return dto;
		}
}
