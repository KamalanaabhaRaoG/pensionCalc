package com.vitech.pensionCalc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CalcService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void executeCalc() {
		jdbcTemplate.execute("select (*) from pension_history");
	}

}
