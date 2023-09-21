package com.vitech.pensionCalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vitech.pensionCalc.service.CalcService;

@RestController
@RequestMapping("/api")
public class CalcController {
	@Autowired
	CalcService calcservice;
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String testEndPoint() {
		return "ok";
	}

}
