package com.example.microservice.springeurekaclientschoolservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.springeurekaclientschoolservice.service.StudentServiceDelegateService;
 
@RestController
public class SchoolServiceController {
	@Autowired
    StudentServiceDelegateService studentServiceDelegateService;
 
    @RequestMapping(value = "/getSchoolDetails/{schoolname}", method = RequestMethod.GET)
    public String getStudents(@PathVariable String schoolname) {
        System.out.println("Going to call student service to get data!");
        return studentServiceDelegateService.callStudentServiceAndGetData(schoolname);
    }
}
