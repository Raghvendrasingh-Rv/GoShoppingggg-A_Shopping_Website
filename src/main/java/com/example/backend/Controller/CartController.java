package com.example.backend.Controller;

import java.security.Principal;

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

import com.example.backend.Payload.CartDto;
import com.example.backend.Payload.ItemRequest;
import com.example.backend.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addItem")
	private ResponseEntity<CartDto> addItem(@RequestBody ItemRequest item, Principal principal) {
		CartDto cartDto = this.cartService.addItem(item, principal.getName());
		return new ResponseEntity<CartDto>(cartDto,HttpStatus.ACCEPTED);
	}
	
	//Create method for getting cart
	@GetMapping("/getByUser")
	public ResponseEntity<CartDto> getByUser(Principal principal){
		CartDto Allcart = this.cartService.getByUser(principal.getName());
		return new ResponseEntity<CartDto>(Allcart, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getById/{cartId}")
	public ResponseEntity<CartDto> getById(@PathVariable int cartId, Principal principal){
		CartDto cartDto = this.cartService.getById(cartId, principal.getName());
		return new ResponseEntity<CartDto>(cartDto,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/removeItem/{productId}")
	public ResponseEntity<CartDto> removeItem(@PathVariable int productId, Principal principal){
		CartDto cartDto = this.cartService.removeItem(productId, principal.getName());
		return new ResponseEntity<CartDto>(cartDto, HttpStatus.ACCEPTED);
	}

}
