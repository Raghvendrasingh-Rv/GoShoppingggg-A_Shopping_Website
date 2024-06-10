package com.example.backend;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping("/test")
	@ResponseBody
	public HashMap<String,String> test() {
		HashMap<String,String> ob= new HashMap<>();
		ob.put("Name","Raghvendra");
		ob.put("Age", "23");
		
		System.out.println("Changing obj to JSON");
		return ob;
	}
}
