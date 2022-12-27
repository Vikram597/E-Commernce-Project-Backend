package com.ecom.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.Model.Order;
import com.ecom.Model.User;
import com.ecom.payload.OrderDto;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
