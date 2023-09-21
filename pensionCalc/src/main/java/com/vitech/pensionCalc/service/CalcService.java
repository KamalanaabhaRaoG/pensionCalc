package com.vitech.pensionCalc.service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		List<String> queryList = new ArrayList<>();
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for(int i=1;i<=50;i++) {
			query = new StringBuilder();
			query.append("select sum(column_amount_");
			if(i<10)
				query.append("0").append(i);
			else
				query.append(i);
			query.append(") from pension_history where member_id=").append(memberId);
			queryList.add(query.toString());
			
		}
		for(int i=1;i<=50;i++) {
			query = new StringBuilder();
			query.append("select sum(col_num_");
			if(i<10)
				query.append("0").append(i);
			else
				query.append(i);
			query.append(") from pension_history where member_id=").append(memberId);
			queryList.add(query.toString());
		}
		for(String q:queryList)
			executorService.submit(() -> executeQuery(q,result));
		executorService.shutdown();
		return result;
		
	}
	
	public void executeQuery(String query, Map<String,String> resultMap) {
		String value = entityManager.createNativeQuery(query).getResultList().get(0).toString();
		resultMap.put(query, value);
	}
	
	

}
