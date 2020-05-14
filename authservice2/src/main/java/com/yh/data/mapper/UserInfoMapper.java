package com.yh.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yh.data.entity.UserInfoDB;

@Mapper
@Repository
public interface UserInfoMapper {	
	   UserInfoDB selectUserByName(String uName);
	   int addUser( UserInfoDB userInfoDB);
	    int updateValidate(String uName);
	 
}
