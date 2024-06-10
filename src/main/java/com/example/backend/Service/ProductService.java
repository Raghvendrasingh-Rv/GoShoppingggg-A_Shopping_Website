package com.example.backend.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Model.Category;
import com.example.backend.Model.Product;
import com.example.backend.Payload.CategoryDto;
import com.example.backend.Payload.ProductDto;
import com.example.backend.Repository.CategoryRepository;
import com.example.backend.Repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ProductDto createProduct(ProductDto newProduct, int categoryId) {
		//is category available?
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("This Id: " + categoryId + " category is not found!" ));
		
		Product entity = toEntity(newProduct);
		entity.setCategory(category);
		Product saved = productRepository.save(entity);
		ProductDto Dto = toDto(saved);
		
		return Dto;
	}
	
	public List<ProductDto> viewAllProduct(){
		List<Product> allList = productRepository.findAll();
		List<ProductDto> allListDto = allList.stream().map(product -> this.toDto(product)).collect(Collectors.toList());
		return allListDto;
	}
	
	public Product viewProductById(int Id) {
		Product send =  productRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("This Id: " + Id + " product is not found!" ));
		return send;
	}
	
	public void deleteProduct(int productId) {
		Product p = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("This Id: " + productId + " product is not found!" ));
		productRepository.delete(p);
	}
	
	
	public List<ProductDto> getProductByCategory(int categoryId){
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("This Id: " + categoryId + " category is not found!" ));
		List<Product> found = this.productRepository.findByCategory(category);
		List<ProductDto> allListDto = found.stream().map(product -> this.toDto(product)).collect(Collectors.toList());
		return allListDto;
	}

	public Product updateProduct(int productId, Product newP) {
		Product oldP = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("This Id: " + productId + " product is not found!" ));
		oldP.setProduct_name(newP.getProduct_name());
		oldP.setProduct_price(newP.getProduct_price());
		oldP.setProduct_quantity(newP.getProduct_quantity());
		oldP.setLive(newP.isLive());
		oldP.setStock(newP.isStock());
		oldP.setProduct_description(newP.getProduct_description());
		oldP.setProduct_imageName(newP.getProduct_imageName());
		
		Product saved= productRepository.save(oldP);
		return saved;
	}
	
	public Product toEntity(ProductDto pDto) {
		Product p = new Product();
		p.setProduct_id(pDto.getProduct_id());
		p.setProduct_name(pDto.getProduct_name());
		p.setProduct_price(pDto.getProduct_price());
		p.setProduct_quantity(pDto.getProduct_quantity());
		p.setProduct_imageName(pDto.getProduct_imageName());
		p.setProduct_description(pDto.getProduct_description());
		p.setLive(pDto.isLive());
		p.setStock(pDto.isStock());
		
		return p;
	}
	
	public ProductDto toDto(Product p) {
		ProductDto pDto = new ProductDto();
		pDto.setProduct_id(p.getProduct_id());
		pDto.setProduct_name(p.getProduct_name());
		pDto.setProduct_price(p.getProduct_price());
		pDto.setProduct_quantity(p.getProduct_quantity());
		pDto.setProduct_imageName(p.getProduct_imageName());
		pDto.setProduct_description(p.getProduct_description());
		pDto.setLive(p.isLive());
		pDto.setStock(p.isStock());
		
		CategoryDto catDto = new CategoryDto();
		catDto.setCategory_id(p.getCategory().getCategory_id());
		catDto.setTitle(p.getCategory().getTitle());

		pDto.setCategory(catDto);
		return pDto;
	}

}
