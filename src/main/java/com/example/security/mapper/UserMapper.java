package com.example.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.security.vo.AuthorityVO;
import com.example.security.vo.UserVO;

@Mapper
public interface UserMapper {
	
	public UserVO selectUserInfo(@Param("userId") String userId);
	public List<AuthorityVO> selectUserAuth(@Param("userId") String userId);

	public int insertUser(UserVO userVO);
}
