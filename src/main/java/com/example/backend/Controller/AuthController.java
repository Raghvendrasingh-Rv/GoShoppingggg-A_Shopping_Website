package com.example.backend.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Payload.JwtRequest;
import com.example.backend.Payload.JwtResponse;
import com.example.backend.Payload.UserDto;
import com.example.backend.Security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JwtHelper helper;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		this.authenticationUser(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.helper.generateToken(userDetails);
		JwtResponse response = new JwtResponse();
		response.setToken(token);
		response.setUser(this.mapper.map(userDetails, UserDto.class));
		return new ResponseEntity<JwtResponse>(response,HttpStatus.ACCEPTED);
	}
	
	private void authenticationUser(String username, String password) {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}catch(BadCredentialsException e) {
			throw new ResourceNotFoundException("Invalid Username and Password!");
		}catch(DisabledException e) {
			throw new ResourceNotFoundException("User Is Not Active!");
		}
	}
	

}
