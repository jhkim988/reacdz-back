package com.douzone.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.douzone.dto.RegisterDTO;
import com.douzone.entity.User;

@Mapper
public interface UserRepository {
	Optional<User> findOneByUserId(String userId);
	int insert(RegisterDTO registerDTO);
}
