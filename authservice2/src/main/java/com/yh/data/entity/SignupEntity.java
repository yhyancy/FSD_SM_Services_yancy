package com.yh.data.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SignupEntity {	
	private String username;
	private String usertype;
	private String email;
	private String mobile;
	private passwordsGroup passwordsGroup;
	
	@AllArgsConstructor
	@NoArgsConstructor
	public class passwordsGroup {		
		private String password;
		private String pwconfirm;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPwconfirm() {
			return pwconfirm;
		}
		public void setPwconfirm(String pwconfirm) {
			this.pwconfirm = pwconfirm;
		}
		@Override
		public String toString() {
			return "passwordsGroup [password=" + password + ", pwconfirm=" + pwconfirm + "]";
		}
		

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public passwordsGroup getPasswordsGroup() {
		return passwordsGroup;
	}

	public void setPasswordsGroup(passwordsGroup passwordsGroup) {
		this.passwordsGroup = passwordsGroup;
	}

	@Override
	public String toString() {
		return "SignupEntity [username=" + username + ", usertype=" + usertype + ", email=" + email + ", mobile="
				+ mobile + ", passwordsGroup=" + passwordsGroup + "]";
	}
}

