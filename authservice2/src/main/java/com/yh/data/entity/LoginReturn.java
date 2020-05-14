package com.yh.data.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

public class LoginReturn {	
	private String token;
	private String uName;
	private int  isAuth;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public int getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}
	public String getuType() {
		return uType;
	}
	public void setuType(String uType) {
		this.uType = uType;
	}
	@Override
	public String toString() {
		return "LoginReturn [token=" + token + ", uName=" + uName + ", isAuth=" + isAuth + ", uType=" + uType + "]";
	}
	private String uType;
}
