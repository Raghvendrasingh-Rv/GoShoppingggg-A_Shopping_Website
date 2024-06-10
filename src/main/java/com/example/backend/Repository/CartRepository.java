package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Model.Cart;
import com.example.backend.Model.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	public Cart findByUser(User user);
}
