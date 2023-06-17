package com.feuji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.feuji.models.User;
import com.feuji.repo.UserRepo;

@SpringBootApplication
public class Springsecurity2Application implements CommandLineRunner{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Springsecurity2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user=new User();
		user.setEmail("riya@gmial.com");
		user.setUserName("riya");
		user.setPassword(this.bCryptPasswordEncoder.encode("123456"));
		user.setRole("ROLE_NORMAL");
		this.userRepo.save(user);
		
		User user1=new User();
		user1.setEmail("priya@gmial.com");
		user1.setUserName("priya");
		user1.setPassword(this.bCryptPasswordEncoder.encode("123456"));
		user1.setRole("ROLE_ADMIN");
		this.userRepo.save(user1);

		
	}

}
