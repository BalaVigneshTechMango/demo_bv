package com.jwt.videostream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.videostream.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	
	public String getPasswordByUsername(String username);
	
}
