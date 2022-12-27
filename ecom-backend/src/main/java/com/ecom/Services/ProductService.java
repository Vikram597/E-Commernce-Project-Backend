package com.ecom.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.Exception.ResourceNotFoundException;
import com.ecom.Model.Category;
import com.ecom.Model.Product;
import com.ecom.Repository.CategoryRepository;
import com.ecom.Repository.ProductRepository;
import com.ecom.payload.CategoryDto;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;

@Service
public class ProductService {
	@Autowired
	private	ProductRepository productRepo;
	@Autowired
	private CategoryRepository catRepo;
	@Autowired
    private ModelMapper mapper;
	
	
	public ProductDto createProduct(ProductDto productDto,int catid){
		
		//Fetch Catgory where we want to add Product
		Category cat = this.catRepo.findById(catid).orElseThrow(()->new ResourceNotFoundException("This Category id not found Catgory"));
		
		//ProductDto to Product
		System.out.println(productDto.getProductName());
		Product product = toEntity(productDto);
		//System.out.println("ProductDto to Product"+product.getProductName());
		product.setCategory(cat);
		
		//Save Product into Database
		Product save= this.productRepo.save(product);
				 
		 // Change Product to ProductDto
		 ProductDto dto = toDto(save);
		 	return dto;
	}
	
	public ProductResponse viewAll(int pageNumber,int pageSize,String sortBY,String sortDir){
		Sort sort=null;
		if(sortDir.trim().toLowerCase().equals("asc")) {
			sort = Sort.by(sortBY).ascending();
			System.out.print(sort);
		}else {
				sort=Sort.by(sortBY).descending();
				System.out.print(sort);
		}
		
			Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
			 Page<Product> page = this.productRepo.findAll(pageable);
			 List<Product> pageProduct = page.getContent();
			  
		          List<ProductDto> productDto = pageProduct.stream().map(p->this.toDto(p)).collect(Collectors.toList());
		          
		          ProductResponse response=new ProductResponse();
		          response.setContent(productDto);
		          response.setPageNumber(page.getNumber());
		          response.setPageSize(page.getSize());
		          response.setTotalPages(page.getTotalPages());
		          response.setLastPage(page.isLast());
		//ProductDto to Product
		  // List<Product> findAll = productRepo.findAll();
		   //Product to ProductDto
		  // List<ProductDto> findAllDto = findAll.stream().map(product ->this.toDto(product)).collect(Collectors.toList());
		return response;
	}
	
	public ProductDto viewProductById(int pid) {
	  Product findById = productRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException(+pid+"from this product id product not found"));
	      ProductDto dto = this.toDto(findById);
		return dto;
	}
	public void  deleteProduct(int pid) {
		
		Product byId = productRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException(+pid+"from this product id product not found"));
		productRepo.delete(byId);
		
	}
	
	public ProductDto updateProduct(int pid,ProductDto newp) {
           Product oldp= productRepo.findById(pid).orElseThrow(()->new ResourceNotFoundException(+pid+"product Not found"));
          oldp.setImageName(newp.getProductName());
          oldp.setLive(newp.isLive());
          oldp.setStock(newp.isStock());
          oldp.setProductPrize(newp.getProductPrize());
          oldp.setProductDesc(newp.getProductDesc());
          oldp.setProductName(newp.getProductName());
          oldp.setProductQuantity(newp.getProductQuantity());
          Product save = productRepo.save(oldp);
          ProductDto dto = toDto(save);
		return dto;
	}
	
	//find product by Category
	public ProductResponse findProductByCategoty(int catId,int pageSize,int pageNumber,String sortDir){
	Category Cat = this.catRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("this id Category not Found"));
				//List<Product> findByCategory = this.productRepo.findByCategory(Cat);
				Pageable pageable = PageRequest.of(pageNumber, pageSize);
				Page<Product> page = this.productRepo.findByCategory(Cat,pageable);
				List<Product> product=page.getContent();
				List<ProductDto> productDto = product.stream().map(p -> toDto(p)).collect(Collectors.toList());
				//List<ProductDto> collect = findByCategory.stream().map(product ->toDto(product)).collect(Collectors.toList());
				
				
				ProductResponse response=new ProductResponse();
				response.setContent(productDto);
				 response.setPageNumber(page.getNumber());
		          response.setPageSize(page.getSize());
		          response.setTotalPages(page.getTotalPages());
		          response.setLastPage(page.isLast());
				
		return response;
	}
	
	//ProductDto to Product
	public Product toEntity(ProductDto pDto){
		//Product p=new Product();
//		p.setProductId(pDto.getProductId());
//		p.setProductName(pDto.getProductName());
//		p.setProductDesc(pDto.getProductDesc());
//		p.setProductPrize(pDto.getProductPrize());
//		p.setImageName(pDto.getImageName());
//		p.setLive(pDto.isLive());
//		p.setStock(pDto.isStock());
		return this.mapper.map(pDto, Product.class);
	}
	
	//Product to productDto
	public ProductDto toDto(Product product) {
		ProductDto pDto=new ProductDto();
		pDto.setProductId(product.getProductId());
		pDto.setImageName(product.getImageName());
		pDto.setProductName(product.getProductName());
		pDto.setProductDesc(product.getProductDesc());
		pDto.setProductPrize(product.getProductPrize());
		pDto.setLive(product.isLive());
		pDto.setStock(product.isStock());
		
		//Change Category to CategotyDto
	    	CategoryDto catDto=new CategoryDto();
		catDto.setCategoryId(product.getCategory().getCategoryId());
		catDto.setTitle(product.getCategory().getTitle());
		
		//Then Set Category Dto in Product Dto
		    pDto.setCategory(catDto);
		return pDto;
	}

}
