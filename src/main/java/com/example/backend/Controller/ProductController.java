package com.example.backend.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.Model.Product;
import com.example.backend.Payload.AppConstant;
import com.example.backend.Payload.ProductDto;
import com.example.backend.Payload.ProductResponse;
import com.example.backend.Service.FileUpload;
import com.example.backend.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileUpload fileUpload;
	
	@Value("$product.path.images")
	private String imagepath;
	
	
	
	@PostMapping("/create/{categoryId}")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto newProduct,@PathVariable int categoryId) {
		ProductDto saved = productService.createProduct(newProduct,categoryId);
		return new ResponseEntity<ProductDto>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<ProductDto>> viewAllProduct(){
		List<ProductDto> view = productService.viewAllProduct();
		return new ResponseEntity<List<ProductDto>>(view,HttpStatus.ACCEPTED);
	}
	
//Pagination
	
//	@GetMapping("/viewAll")
//	public ProductResponse viewAllProduct(
//			@RequestParam(value="pageNumber", defaultValue= AppConstant.PAGE_NUMBER_STRING,required=false) int pageNumber,
//			@RequestParam(value="pageSize", defaultValue= AppConstant.PAGE_SIZE_STRING,required=false) int pageSize,
//			@RequestParam(value="sortBy", defaultValue= AppConstant.SORT_BY_STRING,required=false) String sortBy,
//			@RequestParam(value="sortDir", defaultValue= AppConstant.SORT_DIR_STRING,required=false) String sortDir){
//		List<ProductDto> view = productService.viewAllProduct();
//		return new ResponseEntity<List<ProductDto>>(view,HttpStatus.ACCEPTED);
		
//		ProductResponse response = productService.viewAllProduct(pageNumber,pageSize,sortBy,sortDir);
//		return response;
//	}
	
	@GetMapping("/viewProductById/{productId}")
	public ResponseEntity<Product> viewProductById(@PathVariable int productId) {
		Product send = productService.viewProductById(productId);
		return new ResponseEntity<Product>(send,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<String>("Product is deleted",HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product newP) {
		Product updatedP = productService.updateProduct(productId, newP);
		return new ResponseEntity<Product>(updatedP,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getbyCategoryId/{categoryId}")
	public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable int categoryId){
		List<ProductDto> list = this.productService.getProductByCategory(categoryId);
		return new ResponseEntity<List<ProductDto>>(list,HttpStatus.ACCEPTED);
	}
	
	//Image upload
	@PostMapping("/image/{productId}")
	public ResponseEntity<?> uploadImageOfProduct(@PathVariable int productId,
			@RequestParam("product_image") MultipartFile file) {
		Product product = this.productService.viewProductById(productId);
		String imageName=null;
		try {
			String uploadImage = this.fileUpload.UploadImage(imagepath, file);
			product.setProduct_imageName(uploadImage);
			Product updateProduct = this.productService.updateProduct(productId, product);
			
			return new ResponseEntity<>(updateProduct,HttpStatus.ACCEPTED);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(Map.of("Message","File Not Upload!"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
