package com.ecom.Services;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.hibernate.ResourceClosedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Exception.ResourceNotFoundException;
import com.ecom.Model.Cart;
import com.ecom.Model.CartItem;
import com.ecom.Model.Product;
import com.ecom.Model.User;
import com.ecom.Repository.CartRepository;
import com.ecom.Repository.ProductRepository;
import com.ecom.Repository.UserRepository;
import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;

@Service
public class CartService {
	
	@Autowired
private	UserRepository userRepo;
	@Autowired
 private ProductRepository productRepo;
 
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public CartDto addItem(ItemRequest item,String username){
		int productId=item.getProductId();
        int quantity=item.getQuantity();
        //fetch user
        User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User not found"));
       //fetch Product 
       	Product product=this.productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
        	
       	//here we are checking product stock 
        if(!product.isStock()){
        	
        	new ResourceNotFoundException("Product Out of Stock");
        }
        
        // create cartItem with productId and Qnt
        
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        double totaleprice=product.getProductPrize()*quantity;
        cartItem.setTotalprice(totaleprice);
        
        //getting cart from user
        Cart cart=user.getCart();
        
        if(cart==null) {
        	cart=new Cart();
        	//
        	cart.setUser(user);
        }
        
        cartItem.setCart(cart);
        
        Set<CartItem> items = cart.getItems();
        
        // here we check item is available in CartItem or not 
        // if CartItem is availabe then we inc Qnt only else
        // add new product in cartItem
        //
        // by default false
        	AtomicReference<Boolean> flag=new AtomicReference<>(false);
        	
        Set<CartItem> newproduct = items.stream().map((i)->{
          if(i.getProduct().getProductId()==product.getProductId()) {
        	  i.setQuantity(quantity);
        	  i.setTotalprice(totaleprice);
        	  flag.set(true);
          }
          return i;
          
        }).collect(Collectors.toSet());
        
        if(flag.get()){
        	items.clear();
        	items.addAll(newproduct);
        	
        }else {
        	cartItem.setCart(cart);
        	items.add(cartItem);
        }
        
        Cart saveCart = this.cartRepo.save(cart);
        
           
        
		return  this.modelMapper.map(saveCart,CartDto.class);
	}
	
	
	public CartDto getcartAll(String email){
		//find user
		User user = this.userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
		//find cart
		    Cart cart= this.cartRepo.findByUser(user).orElseThrow(()->new ResourceNotFoundException("There is no cart"));
		
		    return this.modelMapper.map(cart,CartDto.class);
		
	}
	
	// get cart by CartId
	
	
	public CartDto getCartByID(int cartId){
		
		//User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User Not found"));
		
	    Cart findByUserAndcartId = this.cartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart not Found"));
		 
		return this.modelMapper.map(findByUserAndcartId,CartDto.class);
	}
	
	public CartDto removeCartItemFromCart(String userName, int ProductId){
		User user=this.userRepo.findByEmail(userName).orElseThrow(()->new ResourceNotFoundException("User Not found"));
		
		Cart cart=user.getCart();
		Set<CartItem> items = cart.getItems();
		
		boolean removeIf = items.removeIf((i)->i.getProduct().getProductId()==ProductId);
		Cart save = this.cartRepo.save(cart);
		System.out.println(removeIf);
		return this.modelMapper.map(save,CartDto.class);
	}
	
}
