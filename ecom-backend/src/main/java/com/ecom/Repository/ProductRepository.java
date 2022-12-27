package com.ecom.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.Model.Category;
import com.ecom.Model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
	Page<Product> findByCategory(Category catgoty,Pageable pageable);
}
