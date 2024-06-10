package com.example.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
 
	public User findByEmail(String email); 
}
