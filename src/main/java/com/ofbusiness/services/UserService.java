package com.ofbusiness.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofbusiness.exceptions.UserNotFoundException;
import com.ofbusiness.models.User;
import com.ofbusiness.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public void isUserExists(String userId) throws UserNotFoundException {
		userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

	public void deleteUser(String userId) {
		userRepository.deleteById(userId);
	}

	public void createUser(User user) {
		userRepository.save(user);
	}
}
