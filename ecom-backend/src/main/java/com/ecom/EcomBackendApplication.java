package com.ecom;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EcomBackendApplication implements CommandLineRunner{
	@Autowired
 private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcomBackendApplication.class, args);
	}
	 
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
//		 System.out.println(this.passwordEncoder.encode("ch123"));
//		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//			Date date=new Date();
//			formatter.format(date);
//		System.out.println(date);
		
	}
}
