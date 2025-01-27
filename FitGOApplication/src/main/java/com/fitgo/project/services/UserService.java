package com.fitgo.project.services;

import java.util.List;
import java.util.Optional;

import com.fitgo.project.entities.User;

public interface UserService {

void signUp(User user);

List<User> getAllUsers();

Optional<User> getUserById(Long id);

String login(String email, String password);

}