package com.jwt.videostream.serviceimple;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.videostream.entity.User;
import com.jwt.videostream.repository.UserRepository;
import com.jwt.videostream.response.JwtResponse;
import com.jwt.videostream.service.UserService;
import com.jwt.videostream.util.JwtUtil;

@Service
public class UserServiceImple implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	public User saveUser(User user) {
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userRepository.save(user);
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), 
					        user.getPassword(), Collections.emptyList());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	public JwtResponse login(User user) throws Exception{
		JwtResponse jwtResponse=new JwtResponse();
		UserDetails userDetails = loadUserByUsername(user.getUsername());
		if (passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
	        String token = jwtUtil.generateToken(userDetails);
			jwtResponse.setToken(token);
			jwtResponse.setMessage("Valid User..");
			jwtResponse.setSuccess(true);
		}else {
			jwtResponse.setToken("Token is not generated due to this method");
			jwtResponse.setMessage("User not found");
			jwtResponse.setSuccess(false);
		}
		return jwtResponse;
	}	
		
}
