package com.accolite.opportunitymanagement.service.Impl;

import java.util.*;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("TrendService")
@Transactional
public class TrendServiceImpl {
	
	
	@Autowired
    private DataSource datasource;
	
	public List<String> getByLocation(){
		Set<String> LocationList = new HashSet<String>() {};
		String query = "select distinct location from Opportunity";

		JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
		List<Map<String,Object>> Rows = jdbcTemplate.queryForList(query);
		for(Map<String,Object> row : Rows){
			String loc = (String.valueOf(row.get("location")));
			loc = loc.toUpperCase();
			String[] arrOfLoc = loc.split(",");
			for(String s:arrOfLoc)
			{
				s=s.trim();
				LocationList.add(s);
			}
		}
		List<String>NewLocationList = new ArrayList<>(LocationList);
		return NewLocationList;
	}
	
	public List<Long> getByLocationCount(){
		List<Long> locationCount = new ArrayList<Long>();
		HashMap<String,Long> hMap= new HashMap<>();
		String query = "select count(*),location from Opportunity group by location";

		JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
		List<Map<String,Object>> Rows = jdbcTemplate.queryForList(query);

		for(Map<String,Object> row : Rows){
			Long val = (Long) row.get("count(*)");
			String loc = (String.valueOf(row.get("location")));
			loc = loc.toUpperCase();

			String[] arrOfLoc = loc.split(",");

			for(String s: arrOfLoc){
				s = s.trim();
				System.out.println(s);
				if (!hMap.containsKey(s)) {
					hMap.put(s, val);
				}
				else {
					Long count = hMap.get(s);
					hMap.put(s, count + val);
				}
			}
		 }

		for (HashMap.Entry<String, Long> entry : hMap.entrySet())
		{
			locationCount.add(entry.getValue());
		}
		System.out.println(hMap);
		return locationCount;
	}
	
public List<String> getBySkill(){
	List<String> skillList = new ArrayList<String>();
	HashMap<String,Integer> hMap= new HashMap<>();
	String query = "select skills from Opportunity";
	JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
	List<Map<String,Object>> Rows = jdbcTemplate.queryForList(query);
	for(Map<String,Object> row : Rows){
		String skill = (String.valueOf(row.get("skills")));
		String[] arrOfStr = skill.split(",");
		for(String s: arrOfStr) {
			s=s.trim();
			if (!hMap.containsKey(s)) {  
			      hMap.put(s, 1);
			    }
			    else {
			      int count = hMap.get(s);
			      hMap.put(s, count + 1);
			    }
		}	
		System.out.println(row);
	}
	 for (HashMap.Entry<String, Integer> entry : hMap.entrySet())  
         skillList.add(entry.getKey()); 
	return skillList;
}
	public List<Integer> getBySkillCount(){
		List<Integer> skillList = new ArrayList<Integer>();
		HashMap<String,Integer> hMap= new HashMap<>();
		String query = "select skills from Opportunity";
		JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
		List<Map<String,Object>> Rows = jdbcTemplate.queryForList(query);
		for(Map<String,Object> row : Rows){
			String skill = (String.valueOf(row.get("skills")));
			String[] arrOfStr = skill.split(",");
			for(String s: arrOfStr) {
				s=s.trim();
				if (!hMap.containsKey(s)) {  
				      hMap.put(s, 1);
				    }
				    else {
				      int count = hMap.get(s);
				      hMap.put(s, count + 1);
				    }
			}	
			System.out.println(row);
		}
		 for (HashMap.Entry<String, Integer> entry : hMap.entrySet())  
	         skillList.add(entry.getValue()); 
		return skillList;
	}
	
	public List<String> getByYoY(){
		List<String> yearList = new ArrayList<String>();
		TreeMap<String,Integer> hMap= new TreeMap<>();
		String query = "select date from Opportunity";	
		JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
		List<Map<String,Object>> Rows = jdbcTemplate.queryForList(query);
		for(Map<String,Object> row : Rows){
			String year = (String.valueOf(row.get("date"))).substring(0, 4);
			if (!hMap.containsKey(year)) {  
			      hMap.put(year, 1);
			    }
			    else {
			      int count = hMap.get(year);
			      hMap.put(year, count + 1);
			    }
		}
		for (Map.Entry<String, Integer> entry : hMap.entrySet())  
	         yearList.add(entry.getKey()); 
		return yearList;
	}
	
	public List<Integer> getByYoYCount(){
		List<Integer> yearList = new ArrayList<Integer>();
		TreeMap<String,Integer> hMap= new TreeMap<>();
		String query = "select date,demand from Opportunity";	
		JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
		List<Map<String,Object>> Rows = jdbcTemplate.queryForList(query);
		for(Map<String,Object> row : Rows){
			String year = (String.valueOf(row.get("date"))).substring(0, 4);
			int demand = (int) ((row.get("demand")));
			if (!hMap.containsKey(year)) {  
			      hMap.put(year, demand);
			    }
			    else {
			      int count = hMap.get(year);
			      hMap.put(year, count + demand);
			    }
			System.out.println(year+" "+demand);
		}
//		Map<Integer, String> map = new TreeMap<Integer, String>(hmap);
		for (Map.Entry<String, Integer> entry : hMap.entrySet())  
	         yearList.add(entry.getValue()); 
		return yearList;
	}
	
	public List<String> getByYoY(String loc){
		List<String> yearList = new ArrayList<String>();
		TreeMap<String,Integer> hMap= new TreeMap<>();
		String query = "select date from Opportunity where location=?";	
		JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
		List<Map<String,Object>> Rows = jdbcTemplate.queryForList(query, new Object[]{loc});
		for(Map<String,Object> row : Rows){
			String year = (String.valueOf(row.get("date"))).substring(0, 4);
			if (!hMap.containsKey(year)) {  
			      hMap.put(year, 1);
			    }
			    else {
			      int count = hMap.get(year);
			      hMap.put(year, count + 1);
			    }
			System.out.println(row);
		}
		for (Map.Entry<String, Integer> entry : hMap.entrySet())  
	         yearList.add(entry.getKey()); 
		return yearList;
	}
	
	public List<Integer> getByYoYCount(String location){
		List<Integer> yearList = new ArrayList<Integer>();
		TreeMap<String,Integer> hMap= new TreeMap<>();
		String query = "select date,demand from Opportunity where location=?";	
		JdbcTemplate jdbcTemplate= new JdbcTemplate(datasource);
		List<Map<String,Object>> Rows = jdbcTemplate.queryForList(query, new Object[] {location});
		for(Map<String,Object> row : Rows){
			String year = (String.valueOf(row.get("date"))).substring(0, 4);
			int demand = (int) ((row.get("demand")));
			if (!hMap.containsKey(year)) {  
			      hMap.put(year, demand);
			    }
			    else {
			      int count = hMap.get(year);
			      hMap.put(year, count + demand);
			    }
			System.out.println(year+" "+demand);
		}
		for (Map.Entry<String, Integer> entry : hMap.entrySet())  
	         yearList.add(entry.getValue()); 
		return yearList;
	}
	
}
