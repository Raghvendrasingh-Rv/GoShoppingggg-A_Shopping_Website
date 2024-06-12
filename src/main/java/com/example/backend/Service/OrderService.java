package com.example.backend.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.Cart;
import com.example.backend.Model.CartItem;
import com.example.backend.Model.Order;
import com.example.backend.Model.OrderItem;
import com.example.backend.Model.User;
import com.example.backend.Payload.OrderDto;
import com.example.backend.Payload.OrderRequest;
import com.example.backend.Payload.OrderResponse;
import com.example.backend.Repository.CartRepository;
import com.example.backend.Repository.OrderRepository;
import com.example.backend.Repository.UserRepository;

@Service
public class OrderService {
	
	//order create method
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public OrderDto createOrder(OrderRequest request, String username) {
		User user = this.userRepo.findByEmail(username);
		if (user == null) {
			new ResourceNotFoundException("User Not Found!!!");
		}
		int cartId = request.getCartId();
		String orderAddress = request.getOrderAddress();
		
		Cart cart = this.cartRepo.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart Not Found!"));
		Set<CartItem> cartItems = cart.getCartItem();
		
		Order order = new Order();
		AtomicReference<Double> totalOrderAmount = new AtomicReference<Double>(0.0);
		Set<OrderItem> orderItems = cartItems.stream().map((cartItem)->{
			
			OrderItem orderItem = new OrderItem();
			//set product
			orderItem.setProduct(cartItem.getProduct());
			//set productqnt
			orderItem.setProductQnt(cartItem.getQualtity());
			//set price			
			orderItem.setTotalProductPrice(cartItem.getTotalPrice());
			orderItem.setOrder(order);
			totalOrderAmount.set(totalOrderAmount.get()+orderItem.getTotalProductPrice());
			
			int productId = orderItem.getProduct().getProduct_id();
			return orderItem;
		}).collect(Collectors.toSet());
		
		order.setBillingAddress(orderAddress);
		order.setOrderAmt(totalOrderAmount.get());
		order.setOrderDelivered(null);
		order.setPaymentStatus("Note Paid!");
		order.setUser(user);
		order.setOrderItem(orderItems);
		order.setOrderStatus("Created!");
		Date date = new Date();
		order.setOrderDate(date);
		Order saved;
		if(order.getOrderAmt()>0) {
			saved = this.orderRepo.save(order);
			cart.getCartItem().clear();
			this.cartRepo.save(cart);
		}else {
			throw new ResourceNotFoundException("Add Item Into Cart and then Order!");
		}
		return this.mapper.map(saved, OrderDto.class);	
	}
	
	
	public void cancelOrder(int orderId) {
		Order order = this.orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Found!"));
		this.orderRepo.delete(order);
	}

	
	public OrderDto getById(int orderId) {
		Order order = this.orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order Not Found!"));
		return this.mapper.map(order, OrderDto.class);
	}
	
	//find All orders by page
	
	public OrderResponse viewAllOrder(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Order> findAll = this.orderRepo.findAll(pageable);
		List<Order> order = findAll.getContent();
		List<OrderDto> orderDto = order.stream().map(each -> this.mapper.map(each,OrderDto.class)).collect(Collectors.toList());
		
		OrderResponse response = new OrderResponse();
		response.setContent(orderDto);
		response.setPageNumber(findAll.getNumber());
		response.setPageSize(findAll.getSize());
		response.setLastPage(findAll.isLast());
		response.setTotalpages(findAll.getTotalPages());
		response.setTotalElement(findAll.getTotalElements());
		
		return response;
	}
}
