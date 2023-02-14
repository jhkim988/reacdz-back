package com.douzone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douzone.dto.ArticleDTO;
import com.douzone.entity.Article;
import com.douzone.entity.User;
import com.douzone.repository.ArticleRepository;

@Service
public class ArticleService {

	private final ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public List<ArticleDTO.ListResponse> list() {
		return articleRepository.list();
	}
	
	public Article get(String id) {
		return articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}
	
	public void post(User user, ArticleDTO articleInfo) {
		articleRepository.insert(user.getUserId(), articleInfo);
	}
	
	public void update(String id, ArticleDTO articleInfo) {
		articleRepository.update(id, articleInfo);
	}
	
	public void delete(String id, User user) {
		Article article = articleRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		if (article.getUserId().equals(user.getUserId())) {
			articleRepository.delete(id);
			return;
		}
		throw new IllegalArgumentException();
	}
}
