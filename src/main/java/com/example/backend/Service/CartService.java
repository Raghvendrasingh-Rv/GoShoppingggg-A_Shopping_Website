package com.example.backend.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.Cart;
import com.example.backend.Model.CartItem;
import com.example.backend.Model.Product;
import com.example.backend.Model.User;
import com.example.backend.Payload.CartDto;
import com.example.backend.Payload.ItemRequest;
import com.example.backend.Repository.CartRepository;
import com.example.backend.Repository.ProductRepository;
import com.example.backend.Repository.UserRepository;

@Service
public class CartService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	
	public CartDto addItem(ItemRequest item, String Username) {
		int productId = item.getProduct_Id();
		int productqnt = item.getQuantity();
		User user = this.userRepo.findByEmail(Username);
		if (user == null) {
			new ResourceNotFoundException("User Not Found!!!");
		}
		Product product = this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product Not Found!!!"));
		if(!product.isStock()) {
			new ResourceNotFoundException("Product Out Of Stock!!");
		}
		
		//create cartItem with productId and qnt
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQualtity(productqnt);
		double totalAmount = product.getProduct_price()*productqnt;
		cartItem.setTotalPrice(totalAmount);
		
		//Create cart is not present for user, If exist, do not create just add cartItm
		Cart userCart = user.getCart();
		if(userCart == null) {
			userCart = new Cart();
			userCart.setUser(user);
		}
		//set cart in cartItem
		cartItem.setCart(userCart);
		
		//fetch existing CartItem from user's cart
		Set<CartItem> items = userCart.getCartItem();
		
		//if item present in the CartItem then just increase the qnt 
		//otherwise add that product in cartItem
		AtomicReference<Boolean> flag = new AtomicReference<>(false);
		Set<CartItem> newItems = items.stream().map((i)->{
			if(i.getProduct().getProduct_id()==productId) {
//				i.setQualtity(i.getQualtity()+productqnt);
//				i.setTotalPrice(i.getProduct().getProduct_price()*i.getQualtity());
				i.setQualtity(productqnt);
				i.setTotalPrice(totalAmount);
				flag.set(true);
			}
			return i;
		}).collect(Collectors.toSet());	
		if(flag.get()) {
			items.clear();
			items.addAll(newItems);
		}else {
			cartItem.setCart(userCart);
			items.add(cartItem);
		}
		
		Cart cart = this.cartRepo.save(userCart);
		
		CartDto cartDto = this.modelmapper.map(cart, CartDto.class);
		
		return cartDto;
	}
	
	//create method to get cart by username
	
	public CartDto getByUser(String username){
		User user = this.userRepo.findByEmail(username);
		if (user == null) {
			new ResourceNotFoundException("User Not Found!!!");
		}
		//find cart
		
		Cart cart = this.cartRepo.findByUser(user);
		if (cart == null) {
			new ResourceNotFoundException("Cart Not Found!!!");
		}
		return this.modelmapper.map(cart, CartDto.class);
	}
	
	// get cart by ID
	
	public CartDto getById(int cartId, String username) {
		User user = this.userRepo.findByEmail(username);
		if (user == null) {
			new ResourceNotFoundException("User Not Found!!!");
		}
		Cart cart = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart Not Found!"));
		
		return this.modelmapper.map(cart, CartDto.class);
	}
	
	
	//remove cartItems from cart
	
	public CartDto removeItem(int productId, String username) {
		User user = this.userRepo.findByEmail(username);
		if (user == null) {
			new ResourceNotFoundException("User Not Found!!!");
		}
		
		Cart cart = user.getCart();
		Set<CartItem> cartItems = cart.getCartItem();
		
		boolean removed = cartItems.removeIf((i)->i.getProduct().getProduct_id()==productId);
		System.out.println(removed);
		Cart saved = this.cartRepo.save(cart);
		return this.modelmapper.map(saved, CartDto.class);
	}
	
}
