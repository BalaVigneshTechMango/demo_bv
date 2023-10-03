package com.jwt.videostream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.videostream.entity.User;
import com.jwt.videostream.response.JwtResponse;
import com.jwt.videostream.service.UserService;
import com.jwt.videostream.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/saveuser")
	public User saveUserDetails(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping("/login")
	public JwtResponse login(@RequestBody User user) throws Exception{
		return userService.login(user);
	}	

	@GetMapping("/check")
	public String getMessage(@RequestHeader(value = "Authorization", defaultValue = "") String auth)
			                throws Exception {
		jwtUtil.verify(auth);
		return "Checked";
	}	
	
}
