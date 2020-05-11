package com.example.microservice.springeurekaclientschoolservice.mdc;

import org.springframework.stereotype.Component;

@Component
public class TokenInfo {

	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
