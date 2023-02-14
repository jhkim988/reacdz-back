package com.douzone.dto;

import lombok.Data;

@Data
public class ResponseDTO <T> {
	
	private T data;
	
	private ResponseDTO(T data) {
		this.data = data;
	}
	public static <S> ResponseDTO<S> of(S data) {
		return new ResponseDTO<S>(data);
	}
}
