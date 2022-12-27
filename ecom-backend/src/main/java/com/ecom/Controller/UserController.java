package com.ecom.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.Model.Role;
import com.ecom.Services.UserService;
import com.ecom.payload.UserDto;

@RestController
@RequestMapping("/users")

public class UserController {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserService userServie;
	 
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date=new Date();
		formatter.format(date);
		userDto.setDate(date);
		userDto.setActive(true);
		
		UserDto ud=this.userServie.create(userDto);
		return new ResponseEntity<UserDto>(ud,HttpStatus.CREATED);
	}
	
}
	
	
	