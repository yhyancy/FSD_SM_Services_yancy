package com.yh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.yh.data.entity.SignupEntity;
import com.yh.data.entity.SignupReturn;
import com.yh.data.entity.UserInfoDB;
import com.yh.data.mapper.UserInfoMapper;
import com.yh.tools.MailMan;

@RestController
@CrossOrigin("*") //允许跨域
@RequestMapping(value = "signup", method = RequestMethod.POST)
public class SignupController {
	
	public final UserInfoMapper userInfoMapper;
	public final MailMan mailMan;
	
	@Value("${fsd.frontend.hostname}")
	private String feHostname;
	
	@Autowired
	public SignupController(UserInfoMapper userInfoMapper, MailMan mainMan) {
		this.userInfoMapper = userInfoMapper;
		this.mailMan= mainMan;	
	}

	@PostMapping("")
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
		System.out.println(userInfoDB);
		
		//4. 插入数据库
		userInfoMapper.addUser(userInfoDB);
		
		//5. 给用户邮箱发link
		String mailSendtoAddress= userInfoDB.getEmail();
		String subject ="Please confirm your code:";
		String text = feHostname + "/email-confirm"+ "?Uname=" + userInfoDB.getUser_name() + "&code=" + veriCode;
		mailMan.sender(mailSendtoAddress, subject, text);
		
		//6.返回给前台
		return new SignupReturn("OK");
			
	}
	
	@GetMapping("validate")
	public SignupReturn validateSignup(@RequestParam (required = true) String uname, @RequestParam(required = true) String code) {
		if(uname.length()>=10) {
			return new SignupReturn("NOT ALLOWED");
		}
		String uName = uname;
		UserInfoDB userInfoDB = userInfoMapper.selectUserByName(uName);
		if(code.equals(userInfoDB.getVeri_code())) {
			userInfoMapper.updateValidate(uName);	
			return new SignupReturn("OK");
		}
		return new SignupReturn("FAILED");
	}
	
	// utils
	private int validateGenerator() {
		return (int)((Math.random()*9+1)*1000);
	}


	private UserInfoDB formatUserInfo(SignupEntity signupEntity, int veriCode ) {
		return new UserInfoDB(0, signupEntity.getUsername(),signupEntity.getPasswordsGroup().getPassword(),signupEntity.getEmail(), signupEntity.getMobile(), signupEntity.getUsertype(), Integer.toString(veriCode), "N");
			}

}
