package com.vitech.pensionCalc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/historyCalc/{memberId}",method=RequestMethod.GET)
	public String testEndPoint(@PathVariable String memberId) {
		long startTime = System.currentTimeMillis();
		Map<String,String> result =  calcservice.calcPensionHistory(memberId);
		long endTime = System.currentTimeMillis();
		Long executionTime = endTime - startTime;
		return ""+executionTime.toString()+" \n /n "+ result.toString();
	}

}
