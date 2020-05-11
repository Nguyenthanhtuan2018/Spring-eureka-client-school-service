package com.example.microservice.springeurekaclientschoolservice.mdc;

import org.slf4j.MDC;

public class MDCUtil {
	
	 private MDCUtil() {
	    }
	 
	 public static final String ACCESS_TOKEN_KEY = "access_token_key";
	 
	 public static String getAccessToken() {
	        return MDC.get(ACCESS_TOKEN_KEY);
	    }

     public static void setAccessToken(String accessTokenKey) {
        MDC.put(ACCESS_TOKEN_KEY, accessTokenKey);
     }

}
