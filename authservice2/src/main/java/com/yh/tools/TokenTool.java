package com.yh.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yh.data.entity.UserInfoDB;


public class TokenTool {
	public static String getToken(UserInfoDB userInfoDB) {
        String token="";
        token= JWT.create().withAudience(userInfoDB.getUser_name())
                .sign(Algorithm.HMAC256(userInfoDB.getPassword()));
        return token;
    }

}
