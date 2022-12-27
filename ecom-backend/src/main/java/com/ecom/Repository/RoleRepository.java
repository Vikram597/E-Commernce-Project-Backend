package com.ecom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
	 

}
