package com.douzone.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Digits;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.douzone.dto.ArticleDTO;
import com.douzone.dto.ResponseDTO;
import com.douzone.entity.User;
import com.douzone.service.ArticleService;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {

	private final ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<ResponseDTO<?>> list() {
		return ResponseEntity.ok(ResponseDTO.of(articleService.list()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO<?>> get(@PathVariable String id) {
		return ResponseEntity.ok(ResponseDTO.of(articleService.get(id)));
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<?>> post(@RequestBody ArticleDTO articleInfo, @SessionAttribute(name="user") User user) {
		articleService.post(user, articleInfo);
		return ResponseEntity.ok(ResponseDTO.of(Boolean.valueOf(true)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO<?>> update(
			@PathVariable @Digits(integer=10, fraction=0) String id,
			@RequestBody ArticleDTO articleInfo) {
		articleService.update(id, articleInfo);
		return ResponseEntity.ok(ResponseDTO.of(true));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO<?>> delete(@PathVariable String id, @SessionAttribute(name="user") User user) {
		articleService.delete(id, user);
		return ResponseEntity.ok(ResponseDTO.of(true));
	}
	
	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<ResponseDTO<?>> idException(ConstraintViolationException e) {
		return ResponseEntity.badRequest().body(ResponseDTO.of("유효한 값이 아닙니다."));
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ResponseDTO<?>> illegalArgument(IllegalArgumentException e) {
		return ResponseEntity.badRequest().body(ResponseDTO.of("유효한 값이 아닙니다."));
	}
}
