package com.jwt.videostream.service;

import com.jwt.videostream.entity.User;
import com.jwt.videostream.response.JwtResponse;

public interface UserService {

	public User saveUser(User user);
	
	public JwtResponse login(User user) throws Exception;
	
}
