package com.douzone.service;

import org.springframework.stereotype.Service;

import com.douzone.dto.LoginDTO;
import com.douzone.dto.RegisterDTO;
import com.douzone.entity.User;
import com.douzone.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User login(LoginDTO.Request request) {
		User user = userRepository.findOneByUserId(request.getUserId()).orElseThrow(IllegalArgumentException::new);
		if (user.getPassword().equals(request.getPassword())) return user;
		throw new IllegalArgumentException();
	}
	
	public boolean register(RegisterDTO registerDTO) {
		return userRepository.insert(registerDTO) == 1;
	}
	
	public User get(String userId) {
		return userRepository.findOneByUserId(userId).orElseThrow(() -> new IllegalArgumentException());
	}
}
