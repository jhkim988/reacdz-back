package com.douzone.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ListResponse {
		private int id;
		private String title;
		private String userId;
	}
}
