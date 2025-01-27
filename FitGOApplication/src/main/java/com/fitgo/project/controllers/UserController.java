package com.fitgo.project.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitgo.project.entities.User;
import com.fitgo.project.services.UserService;

@RequestMapping("/users")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		userService.signUp(user);
		return new ResponseEntity<>(user.getName() + " Signed Up Successfully", HttpStatus.ACCEPTED);

	}

// @PostMapping("/login")
// public ResponseEntity<String> login(@RequestBody User user) {
// User fetchedUser = userService.login(user.getEmail());
//
// if (fetchedUser == null) {
// return new ResponseEntity<>("No User Found, Please Sign Up", HttpStatus.FORBIDDEN);
// } else if (user.getPassword().equals(fetchedUser.getPassword())) {
// return new ResponseEntity<>("Login Success", HttpStatus.ACCEPTED);
// } else {
// return new ResponseEntity<>("Login Failure,Enter Correct Password", HttpStatus.FORBIDDEN);
// }
//
// }

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		try {
			String token = userService.login(user.getEmail(), user.getPassword());
			return ResponseEntity.ok("Bearer " + token);
		} catch (RuntimeException e) {
			return ResponseEntity.status(401).body(e.getMessage());
		}
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "I am New User From ksssss";

	}

	@GetMapping("/demo")
	public String demo() {
		return "i am demo";
	}

	@GetMapping("/allusers")
	public ResponseEntity<List<User>> getAllUsers() {
		System.out.println("Inside all users ::::::");

		List<User> users = userService.getAllUsers();
		System.out.println("all users ::::::" + users);

		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		System.out.println("all users ::::::" + users);
		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	@GetMapping("/allprofiles")
	public ResponseEntity<List<User>> getAllProfiles() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
		Optional<User> fetchedUser = userService.getUserById(id);
		if (fetchedUser.isPresent()) {
			return new ResponseEntity<>(fetchedUser, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}