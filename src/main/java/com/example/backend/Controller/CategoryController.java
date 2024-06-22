package com.example.backend.Controller;

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
import com.example.backend.Payload.CategoryDto;
import com.example.backend.Service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCatgory(@RequestBody CategoryDto newCateogry) {
		CategoryDto saved = this.categoryService.createCatgory(newCateogry);
		return new ResponseEntity<CategoryDto>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<CategoryDto>> viewAllCategory(){	
		List<CategoryDto> list = this.categoryService.viewAllCategory();
		return new ResponseEntity<List<CategoryDto>>(list,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{categoryId}")
	public ResponseEntity<CategoryDto> ViewById(@PathVariable int categoryId) {
		CategoryDto found = this.categoryService.ViewById(categoryId);
		return new ResponseEntity<CategoryDto>(found,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto oldCat, @PathVariable int categoryId) {
		CategoryDto updated = this.categoryService.updateCategory(oldCat, categoryId);
		return new ResponseEntity<CategoryDto>(updated,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successsfully", true),HttpStatus.OK);
	}

}
