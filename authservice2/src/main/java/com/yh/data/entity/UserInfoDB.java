package com.yh.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class UserInfoDB {
	private int id;
	private String user_name;
	private String password;
	private String email;
	private String mobile_num;
	private String user_type;
	private String veri_code;	
	private String confirmed;

}
