package com.douzone.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.douzone.dto.ArticleDTO;
import com.douzone.entity.Article;

@Mapper
public interface ArticleRepository {
	List<ArticleDTO.ListResponse> list();
	Optional<Article> findById(String id);
	int insert(@Param("userId") String userId, @Param("articleDTO") ArticleDTO articleInfo);
	int update(@Param("articleId") String id, @Param("articleDTO") ArticleDTO articleInfo);
	int delete(String id);
}
