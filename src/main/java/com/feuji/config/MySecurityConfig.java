package com.feuji.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.feuji.models.CustomUserDetails;
import com.feuji.services.CustomUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	//basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf().disable()  //when non browser client use then spring security prevent from attack
//		    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //by giving csrf token we can send post and put request
//		    .and()
		    .authorizeRequests()  //taking authorization related request
//		    .antMatchers("/public/**").permitAll() //No authentication required for acess these pages
		    .antMatchers("/signin").permitAll()  //this is public page when user not login then only this page comes
		    .antMatchers("/public/**").hasRole("NORMAL")  //role based access
		    .antMatchers("users/**").hasRole("ADMIN") //role based access
		    .anyRequest()        //apply on any request made by user
		    .authenticated()     //all request must be authenticate
		    .and()              //method chaining
//		    .httpBasic()      //enable http basic authentication
		    .formLogin()     //form based authentication
		    .loginPage("/signin")  //which method of controller hit
		    .loginProcessingUrl("/dologin")  //action in form page
		    .defaultSuccessUrl("/users/");  //after successfully login which url open
//	        .and()
//	        .logout()
//	        .logoutUrl("/logout") // Specify the logout URL
//	        .logoutSuccessUrl("/signin") // Redirect to this URL after successful logout
//	        .invalidateHttpSession(true) // Invalidate the current session
//	        .deleteCookies("JSESSIONID"); // Delete any cookies associated with the session
	} 
	
	
	//creating user with password and role
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		.inMemoryAuthentication()   //it is method for create user
//		.withUser("sam")       //user name
//		.password(this.passwordEncoder().encode("123456"))   //password
//		.roles("NORMAL");     //role
//		
//		//user as admin
//		auth
//		.inMemoryAuthentication()   //it is method for create user
//		.withUser("priya")       //user name
//		.password(this.passwordEncoder().encode("123456"))   //password
//		.roles("ADMIN");     //role
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	//when we use no password encoder
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	//password encription
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10); 
	}
	
	
	

}
