package com.ecom.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	@Autowired
	private JwtHelper jwtHelper;
	@Autowired
	private UserDetailsService userDetailsServicel;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// getToken from Header 
		  String requestToken = request.getHeader("Authorization");
		  logger.info("message {}" ,requestToken);
		  
		  String Username=null;
		  String jwtToken=null;
		  
		 
		  
		  if(requestToken!=null && requestToken.trim().startsWith("Bearer")){
			  // get acutal token
			  jwtToken=requestToken.substring(7);
			  
			  try {
				  
				  Username=this.jwtHelper.getUsername(jwtToken);
				  
			  }catch(ExpiredJwtException e) {
				  logger.info("Invaild token message","Jwt token expire");
			  }catch(MalformedJwtException e) {
				  logger.info("Invaild token message","Invaild Jwt Token");
			  }catch(IllegalArgumentException e) {
				  logger.info("Invaild token message","unable to get token");
			  }
			  
			   if(Username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
				   
				   //vaildate
				   UserDetails userDtails=this.userDetailsServicel.loadUserByUsername(Username);
				      
				     if(this.jwtHelper.vaildateToken(jwtToken,userDtails)){
				    	 
				    	 UsernamePasswordAuthenticationToken auth=new 
				    	UsernamePasswordAuthenticationToken(userDtails,null,userDtails.getAuthorities());
				    	 
				    	 SecurityContextHolder.getContext().setAuthentication(auth);
				    	 
				     }else {
				    	 logger.info("not vaildate Message","invaild Jwt Token");
				    	 
				     }
			   }else {
				   logger.info("User Message","username is null or auth is allready there");
			   }
		   
			  
		  }else {
			  logger.info("Token message {}","token does not start with bearer");
		  }
	  filterChain.doFilter(request,response);
	}

}
