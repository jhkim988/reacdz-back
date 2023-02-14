package com.douzone.dto;

import javax.validation.constraints.NotBlank;

import com.douzone.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LoginDTO {
	
	@Data
	public static class Request {
		@NotBlank
		private String userId;
		@NotBlank
		private String password;		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Response {
		private String userId;
		private String userName;
		
		public static Response of(User user) {
			return new Response(user.getUserId(), user.getUserName());
		}
	}
}
