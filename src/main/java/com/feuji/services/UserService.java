package com.feuji.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.feuji.models.User;
import com.feuji.repo.UserRepo;

@Service
public class UserService {
	
	List<User> listUser =new ArrayList<>();
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepo userRepo;

	//hard coded user create
	public UserService() {
		listUser.add(new User("samundar","123456","sam@gmail.com"));
		listUser.add(new User("priya","123456","priya@gmail.com"));
		listUser.add(new User("ankush","123456","ankush@gmail.com"));
		listUser.add(new User("johny","123456","johny@gmail.com"));
	}
	
	//get all users
	public List<User> getAllUser(){
		return this.listUser;
	}
	
	//single user
	public User getUser(String userName)
	{
		return this.listUser.stream().filter((user) -> user.getUserName().
				equals(userName)).findAny().orElse(null);
	}
	
	
	//add new user
	public User addUser(User user)
	{
		this.listUser.add(user);
		return user;
	}

	public User addingUser() {
		User user=new User();
		user.setEmail("raj@gmail.com");
		user.setPassword(this.bCryptPasswordEncoder.encode("raj"));
		user.setRole("ROLE_ADMIN");
		user.setUserName("raj");
		this.userRepo.save(user);
		return user;
	}
	

	
	

}
