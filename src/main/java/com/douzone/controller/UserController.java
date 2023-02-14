package com.douzone.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.dto.LoginDTO;
import com.douzone.dto.RegisterDTO;
import com.douzone.dto.ResponseDTO;
import com.douzone.entity.User;
import com.douzone.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO<?>> login(@RequestBody LoginDTO.Request userRequest, HttpServletRequest httpRequest) {
		User user = userService.login(userRequest);
		httpRequest.getSession().setAttribute("user", user);
		return ResponseEntity.ok(ResponseDTO.of(LoginDTO.Response.of(user)));
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<ResponseDTO<?>> logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return ResponseEntity.ok(ResponseDTO.of(true));
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<?>> register(@RequestBody RegisterDTO registerRequest) {
		userService.register(registerRequest);
		return ResponseEntity.ok(ResponseDTO.of(true));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<ResponseDTO<?>> detail(@PathVariable String userId) {
		User user = userService.get(userId);
		return ResponseEntity.ok(ResponseDTO.of(user));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String illegalArgument() {
		return "잘못된 인자";
	}
}
