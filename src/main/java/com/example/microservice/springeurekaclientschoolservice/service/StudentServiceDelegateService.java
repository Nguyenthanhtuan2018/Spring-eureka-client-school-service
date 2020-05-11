package com.example.microservice.springeurekaclientschoolservice.service;

import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.microservice.springeurekaclientschoolservice.mdc.MDCUtil;
import com.example.microservice.springeurekaclientschoolservice.mdc.TokenInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
 
@Service
@Slf4j
public class StudentServiceDelegateService {
 
    @Autowired
    RestTemplate restTemplate;
    @Autowired
	public TokenInfo tokenInfo;
     
    @HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback")
    public String callStudentServiceAndGetData(String schoolname) {
 
        System.out.println("Getting School details for " + schoolname);
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        //String authorization = MDCUtil.getAccessToken();
        String accessToken = tokenInfo.getAccessToken();
        headers.set("Authorization", accessToken);
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        System.out.println("==============================================");
        System.out.println(MDCUtil.getAccessToken());
        //System.out.println(authorization);
        System.out.println(accessToken);
        System.out.println(headers);
 
        String response = restTemplate
                .exchange("http://localhost:8080/student-service/getStudentDetailsForSchool/{schoolname}"
                , HttpMethod.GET
                , entity
                , new ParameterizedTypeReference<String>() {
            }, schoolname).getBody();
 
        System.out.println("Response Received as " + response + " -  " + new Date());
 
        return "NORMAL FLOW !!! - School Name -  " + schoolname + " :::  " +
                    " Student Details " + response + " -  " + new Date();
    }
     
    @SuppressWarnings("unused")
    private String callStudentServiceAndGetData_Fallback(String schoolname) {
 
        System.out.println("Student Service is down!!! fallback route enabled...");
 
        return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
                    " Service will be back shortly - " + new Date();
    }
 
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
