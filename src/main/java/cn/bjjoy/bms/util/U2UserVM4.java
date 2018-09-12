package cn.bjjoy.bms.util;

import java.io.Serializable;

public class U2UserVM4 implements Serializable {

	private static final long serialVersionUID = 507268731779405739L;
	
	public String userId;
	public String nickName;
	public String hideMobile;
	public String hideEmail;
	public String mobile;
	public String email;
	public String loginToken;
	public String sign;
	public String accessToken;
	public String accessTokenExpireTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHideMobile() {
		return hideMobile;
	}

	public void setHideMobile(String hideMobile) {
		this.hideMobile = hideMobile;
	}

	public String getHideEmail() {
		return hideEmail;
	}

	public void setHideEmail(String hideEmail) {
		this.hideEmail = hideEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenExpireTime() {
		return accessTokenExpireTime;
	}

	public void setAccessTokenExpireTime(String accessTokenExpireTime) {
		this.accessTokenExpireTime = accessTokenExpireTime;
	}

}