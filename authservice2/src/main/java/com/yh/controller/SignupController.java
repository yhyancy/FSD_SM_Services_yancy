package com.yh.controller;

import java.util.Date;

import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.yh.data.entity.SignupEntity;
import com.yh.data.entity.SignupReturn;
import com.yh.data.entity.UserInfoDB;
import com.yh.data.mapper.UserInfoMapper;

@RestController
@CrossOrigin("*") //允许跨域
public class SignupController {
	public final UserInfoMapper userInfoMapper;
	
	
	public SignupController(UserInfoMapper userInfoMapper) {
	this.userInfoMapper = userInfoMapper;
	}


	@PostMapping("signup")
	public SignupReturn signup(@RequestBody(required = true) SignupEntity signupEntity) {
		//1. 校验signupEntity是否为空
		if(signupEntity== null) {
			return new SignupReturn("FAILED");
		}
		// 不为空
		//2.生成 verify code
		int veriCode = validateGenerator();
		
		// 3.格式化 前端传来的参数
		UserInfoDB userInfoDB = formatUserInfo(signupEntity,veriCode);	
		
		//4. 插入数据库
		userInfoMapper.addUser(userInfoDB);
		
		//5. 给用户邮箱发link
		
		//6.返回给前台
		return new SignupReturn("OK");
		
	}


	private int validateGenerator() {
		return (int)((Math.random()*9+1)*1000);
	}


	private UserInfoDB formatUserInfo(SignupEntity signupEntity, int veriCode ) {
		return new UserInfoDB(0, signupEntity.getUsername(),signupEntity.getPasswordsGroup().getPassword(),signupEntity.getEmail(), signupEntity.getMobile(), signupEntity.getUsertype(), Integer.toString(veriCode), "N");
		
	}

}
