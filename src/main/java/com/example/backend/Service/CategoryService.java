package com.example.backend.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.Category;
import com.example.backend.Payload.CategoryDto;
import com.example.backend.Repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public CategoryDto createCatgory(CategoryDto newCateogry) {
		Category entity = this.mapper.map(newCateogry, Category.class);
		Category saved = this.categoryRepository.save(entity);
		CategoryDto dto = this.mapper.map(saved, CategoryDto.class);
		return dto;
	}
	
	public List<CategoryDto> viewAllCategory(){
		List<Category> listEntity = this.categoryRepository.findAll();
		List<CategoryDto> listDto = listEntity.stream().map(category -> this.mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return listDto;
	}
	
	public CategoryDto ViewById(int categoryId) {
		Category found = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("This Id: " + categoryId + " category is not found!" ));
		CategoryDto foundDto = this.mapper.map(found, CategoryDto.class);
		return foundDto;
	}
	
	public CategoryDto updateCategory(CategoryDto newCat, int categoryId) {
		Category oldCat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("This Id: " + categoryId + " category is not found!"));
//		oldCat.setCategory_id(newCat.getCategory_id());
		oldCat.setTitle(newCat.getTitle());
		Category saved = this.categoryRepository.save(oldCat);
		CategoryDto savedDto = this.mapper.map(saved, CategoryDto.class);
		return savedDto;
	}
	
	public void deleteCategory(int categoryId) {
		Category oldCat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("This Id: " + categoryId + " category is not found!"));
		this.categoryRepository.delete(oldCat);
	}

}
