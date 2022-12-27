package com.ecom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.Model.Category;
import com.ecom.Model.Product;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
	

}
