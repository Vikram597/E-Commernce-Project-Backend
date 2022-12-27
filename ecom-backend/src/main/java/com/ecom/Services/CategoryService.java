package com.ecom.Services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Exception.ResourceNotFoundException;
import com.ecom.Model.Category;
import com.ecom.Repository.CategoryRepository;
import com.ecom.payload.CategoryDto;
@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public CategoryDto create(CategoryDto dto) {
		//CategoryDto to Categoty
		  Category cat = this.mapper.map(dto,Category.class);
		  Category save = this.catRepo.save(cat);
		  
		  //Category to CategoryDto
		return this.mapper.map(save,CategoryDto.class);
	}
	
	public CategoryDto update( CategoryDto newcat , int catid) {
		Category oldCat= this.catRepo.findById(catid).orElseThrow(()->new ResourceNotFoundException("This Category Id not Found"));
		
		
		oldCat.setTitle(newcat.getTitle());
		System.out.println(newcat.getTitle());
		Category save = this.catRepo.save(oldCat);
		return this.mapper.map(save,CategoryDto.class);
	}
	
	public void delete(int catid) {
		Category Cat= this.catRepo.findById(catid).orElseThrow(()->new ResourceNotFoundException("This Category Id not Found"));
		this.catRepo.delete(Cat);
	}
	
	public CategoryDto getbyId(int catid) {
		Category getByid= this.catRepo.findById(catid).orElseThrow(()->new ResourceNotFoundException("This Category Id not Found"));
		return this.mapper.map(getByid,CategoryDto.class);
	}
	
	public List<CategoryDto> getAll(){
		List<Category> findAll = this.catRepo.findAll();
		 List<CategoryDto> allDto= findAll.stream().map(cat->this.mapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return allDto;
	}
}
