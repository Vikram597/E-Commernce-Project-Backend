package com.ecom.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.Services.CartService;
import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
;

@RestController
@RequestMapping("/cart")
public class CartController{
	
@Autowired
 private CartService cartSer;

	@PostMapping("/")
	public ResponseEntity<CartDto> addtoCart(@RequestBody ItemRequest itemRequest,Principal principal){
		String email=principal.getName();
		System.out.println(email);	  
		CartDto addItem = this.cartSer.addItem(itemRequest,principal.getName());
		
	            	  return new ResponseEntity<CartDto>(addItem,HttpStatus.OK);
	   }
	
	
	//create method for getting cart
	@GetMapping("/")
	public ResponseEntity<CartDto>getAllCart(Principal principal){
		    CartDto getcartAll = this.cartSer.getcartAll(principal.getName());
		return new ResponseEntity<CartDto>(getcartAll,HttpStatus.ACCEPTED);
	}
	@GetMapping("/{cartId}")
	public ResponseEntity<CartDto>getCartById(@PathVariable  int cartId){
		
		System.out.println(cartId);
		CartDto cartByID = this.cartSer.getCartByID(cartId);
		return new ResponseEntity<CartDto>(cartByID,HttpStatus.OK);
	}
	
	@DeleteMapping("/{pid}")
	public ResponseEntity<CartDto>deleteCartItemFromCart(@PathVariable int pid,Principal p){
		
		CartDto remove = this.cartSer.removeCartItemFromCart(p.getName(),pid);
		return new ResponseEntity<CartDto>(remove,HttpStatus.UPGRADE_REQUIRED);
	}
	

}
