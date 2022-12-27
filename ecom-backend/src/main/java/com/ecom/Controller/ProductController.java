package com.ecom.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.AssertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.Model.Product;
import com.ecom.Services.ProductService;
import com.ecom.payload.AppConstants;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	private List<Product> findProductByCategoty;
	
	@PostMapping("/create/{catid}")
	//product/create
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product,@PathVariable int catid) {
		//System.out.println(product.getProductName());
		 ProductDto createProduct = productService.createProduct(product,catid);
		return new ResponseEntity<ProductDto>(createProduct,HttpStatus.CREATED);
	}
	
	//view Product
   @GetMapping("/view")
	public  ProductResponse viewAllProduct(@RequestParam(value="pageNumber",defaultValue =AppConstants.PAGE_NUMBER_STRING,required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE_STRING,required = false) int pageSize,
			@RequestParam(value = "sortBy" ,defaultValue = AppConstants.SORT_BY_STRING,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR_STRING,required = false) String sortDir){
	   
		  ProductResponse response=productService.viewAll(pageNumber,pageSize,sortBy,sortDir);
		  
		return response;
	}
   //view ProductById
   @ResponseBody
   @GetMapping("/view/{productId}")
   public ResponseEntity<ProductDto> viewProductById(@PathVariable int productId){
	   
	   ProductDto viewById=productService.viewProductById(productId);
	   				System.out.println(viewById.getCategory().getTitle());
	   return new ResponseEntity<ProductDto>(viewById,HttpStatus.OK);
   }
   
  // delete product
   @DeleteMapping("/del/{productId}")
   public ResponseEntity<String> deleteProduct(@PathVariable int productId){
	  
	   productService.deleteProduct(productId);
	   return new ResponseEntity<String>("Product Deleted",HttpStatus.OK);
   }
   
   // update Product
   @PutMapping("/update/{productId}")
   public ResponseEntity<ProductDto> updateProduct(@PathVariable int productId,@RequestBody ProductDto newproduct ){
	   ProductDto updateProduct = productService.updateProduct(productId,newproduct);
	   return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.ACCEPTED);
   }
   
   //Find product by Categoty wise
   @GetMapping("/category/{catId}")
   public  ProductResponse getProductbyCatgory(@PathVariable int catId,@RequestParam(value="pageNumber",defaultValue =AppConstants.PAGE_NUMBER_STRING,required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE_STRING,required = false) int pageSize,
			@RequestParam(value = "sortBy" ,defaultValue = AppConstants.SORT_BY_STRING,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR_STRING,required = false) String sortDir){
	   
	       ProductResponse findProductByCategoty = this.productService.findProductByCategoty(catId,pageSize,pageNumber,sortDir);
	       
	   return findProductByCategoty;
   }
}
