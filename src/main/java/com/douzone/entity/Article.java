package com.douzone.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Article {
	private int id;
	private String title;
	private String content;
	private String userId;
	private LocalDateTime createdAt;
}
