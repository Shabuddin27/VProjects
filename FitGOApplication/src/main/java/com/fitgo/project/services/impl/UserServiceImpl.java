package com.fitgo.project.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitgo.project.entities.User;
import com.fitgo.project.repository.UserRepository;
import com.fitgo.project.security.util.JwtTokenUtil;
import com.fitgo.project.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@Override
	public void signUp(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		System.out.println("Inside get all users frm service");
		return userRepo.findAll();
	}

	@Override
	public String login(String email, String password) {
	    User user = userRepo.findByEmail(email);
	    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
	        return jwtTokenUtil.generateToken(email);
	    }
	    throw new RuntimeException("Invalid email or password");
	}


	@Override
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user;
	}

}