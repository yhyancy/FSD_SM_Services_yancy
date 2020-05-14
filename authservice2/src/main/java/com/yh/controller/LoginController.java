package com.yh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.yh.data.entity.LoginEntity;
import com.yh.data.entity.LoginReturn;
import com.yh.data.entity.UserInfoDB;
import com.yh.data.mapper.UserInfoMapper;
import com.yh.tools.TokenTool;

@RestController
@CrossOrigin("*") //允许跨域
public class LoginController {
	 private final UserInfoMapper userInfoMapper;
	 
	 @Autowired
	 private LoginController(UserInfoMapper userInfoMapper) {
		// TODO Auto-generated constructor stub
		 this.userInfoMapper=userInfoMapper;
	}
	
	@PostMapping("/login")
	public LoginReturn authUnamePwd(@RequestBody(required = true) LoginEntity authUser) {
		// 校验authUser是否为空
		if (authUser ==  null ) {
			return new LoginReturn("","",0,"");
		}
		
		//1. 根据authUser名字取数据库查询authUser信息
		UserInfoDB authUserInfoDB =userInfoMapper.selectUserByName(authUser.getUserName());
		System.out.println(authUserInfoDB);
		//2. 校验数据库是否存在authInfoDB信息
		//2-1 不存在
		if(authUserInfoDB == null) {
			return new LoginReturn("","",0,"");
		}
		//2-2 存在
		//3.判断用户名密码是否正确
		if(!authUserInfoDB.getPassword().equals(authUser.getPassWord())) {
			//密码不正确
			return new LoginReturn("","",-1,"");			
		}else {
			//密码正确
			//生成JWT token 
			String token = TokenTool.getToken(authUserInfoDB);		
//			System.out.println(new LoginReturn(token,authUserInfoDB.getUser_name(),1,authUserInfoDB.getUser_type()));
			return new LoginReturn(token,authUserInfoDB.getUser_name(),1,authUserInfoDB.getUser_type());	
		}			
		
	}

}
