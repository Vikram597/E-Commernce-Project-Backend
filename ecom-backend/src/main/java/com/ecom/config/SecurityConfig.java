package com.ecom.config;

import javax.servlet.Filter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ecom.Security.JwtAuthenticationEntryPoint;
import com.ecom.Security.JwtAuthenticationFilter;
import com.ecom.payload.UserDto;
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	public static String[] PUBLIC_URL= {"/auth/login"};
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	@Autowired
	private UserDetailsService userDtailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers(PUBLIC_URL).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		//.httpBasic();
		.exceptionHandling()
		.authenticationEntryPoint(entryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailsService).passwordEncoder(this.PasswordEncoder());
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public FilterRegistrationBean cosFilter() {
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration=new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOriginPattern("*");
		configuration.addAllowedHeader("Authorization");
		configuration.addAllowedHeader("Context-Type");
		configuration.addAllowedHeader("Accept");
		configuration.addAllowedMethod("POST");
		configuration.addAllowedMethod("GET");
		configuration.addAllowedMethod("PUT");
		configuration.addAllowedMethod("DELETE");
		configuration.addAllowedMethod("OPTIONS");
		configuration.setMaxAge(3600L);
		source.registerCorsConfiguration("/**",configuration);
		FilterRegistrationBean bean= new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(source));
		bean.setOrder(-110);
		return bean;
	}

}
