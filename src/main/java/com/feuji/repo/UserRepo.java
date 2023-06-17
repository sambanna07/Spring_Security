package com.feuji.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	User findByUserName(String username);

}
