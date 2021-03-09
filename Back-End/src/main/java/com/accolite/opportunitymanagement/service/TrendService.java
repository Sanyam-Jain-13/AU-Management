package com.accolite.opportunitymanagement.service;

import java.util.List;

public interface TrendService {
	public List<String> getByLocation();
	public List<Long> getByLocationCount();
	public List<String> getBySkill();
	public List<Integer> getBySkillCount();
	public List<String> getByYoY();
	public List<Integer> getByYoYCount();
	public List<String> getByYoY(String loc);
	public List<Integer> getByYoYCount(String loc);
}
