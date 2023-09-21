package com.vitech.pensionCalc.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcService {
	@Autowired
	private EntityManager entityManager;
	
	public Map<String,String> calcPensionHistory(String memberId) {
		Map<String,String> result = new HashMap<>();
		StringBuilder query = null;
		for(int i=1;i<=50;i++) {
			query = new StringBuilder();
			query.append("select sum(column_amount_");
			if(i<10)
				query.append("0").append(i);
			else
				query.append(i);
			query.append(") from pension_history where member_id=").append(memberId);
			String value = entityManager.createNativeQuery(query.toString()).getResultList().get(0).toString();
			result.put("column_amount_"+"i", value);
		}
		for(int i=1;i<=50;i++) {
			query = new StringBuilder();
			query.append("select sum(col_num_");
			if(i<10)
				query.append("0").append(i);
			else
				query.append(i);
			query.append(") from pension_history where member_id=").append(memberId);
			String value = entityManager.createNativeQuery(query.toString()).getResultList().get(0).toString();
			result.put("col_num_"+"i", value);
		}
		return result;
		
	}

}
