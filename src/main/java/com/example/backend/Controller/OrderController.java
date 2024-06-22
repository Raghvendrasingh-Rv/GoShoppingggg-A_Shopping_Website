package com.example.backend.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Payload.ApiResponse;
import com.example.backend.Payload.OrderDto;
import com.example.backend.Payload.OrderRequest;
import com.example.backend.Payload.OrderResponse;
import com.example.backend.Service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest request, Principal principal){
		OrderDto orderDto = this.orderService.createOrder(request, principal.getName());
		return new ResponseEntity<OrderDto>(orderDto,HttpStatus.ACCEPTED);
	}
	

	@DeleteMapping("/cancel/{orderId}")
	public ResponseEntity<?> cancelOrder(@PathVariable int orderId) {
		this.orderService.cancelOrder(orderId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Order Cancelled!!!",true),HttpStatus.OK);
	}
	
	@GetMapping("/getById/{orderId}")
	public ResponseEntity<OrderDto> getById(@PathVariable int orderId){
		OrderDto orderDto = this.orderService.getById(orderId);
		return new ResponseEntity<OrderDto>(orderDto,HttpStatus.ACCEPTED); 
	}
	
	@GetMapping("/viewAllOrder")
	public OrderResponse viewAllOrder(
			@RequestParam(value="pageNumber", defaultValue= "0",required=false) int pageNumber,
			@RequestParam(value="pageSize", defaultValue= "2",required=false) int pageSize) {	
		OrderResponse response = this.orderService.viewAllOrder(pageNumber, pageSize);
	return response;	
	}
}
