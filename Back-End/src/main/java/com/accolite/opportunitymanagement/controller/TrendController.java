package com.accolite.opportunitymanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.service.Impl.TrendServiceImpl;

@RestController
@RequestMapping("/trends")
public class TrendController {
	
	@Autowired
	TrendServiceImpl trendServiceImpl;
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value = "/getLocation")
    public List<String> getAllOpportunity(){
        List<String> locationList = new ArrayList<>();
        locationList = trendServiceImpl.getByLocation();
        return locationList;
    }
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value="/getLocationCount")
	public List<Long> getLocationCount(){
		List<Long> locationCount = new ArrayList<Long>();
		locationCount = trendServiceImpl.getByLocationCount();
		return locationCount;
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value="/getSkills")
	public List<String> getSkills(){
		List<String> skillList = new ArrayList<>();
        skillList = trendServiceImpl.getBySkill();
        return skillList;
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value="/getSkillsCount")
	public List<Integer> getSkillCount(){
		List<Integer> skillCount = new ArrayList<Integer>();
		skillCount = trendServiceImpl.getBySkillCount();
		return skillCount;
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value="/getYoY")
	public List<String> getYoY(){
		List<String> yoyList = new ArrayList<>();
        yoyList = trendServiceImpl.getByYoY();
        return yoyList;
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping(value="/getYoYCount")
	public List<Integer> getYoYCount(){
		List<Integer> yoyCount = new ArrayList<>();
        yoyCount = trendServiceImpl.getByYoYCount();
        return yoyCount;
	}

//	@CrossOrigin("http://localhost:4200")
//	@GetMapping(value="/getYoY/{location}")
//	public List<String> getYoY(@PathVariable("location") String location){
//		List<String> yoyList = new ArrayList<>();
//        yoyList = trendServiceImpl.getByYoY(location);
//        return yoyList;
//	}
//
//	@CrossOrigin("http://localhost:4200")
//	@GetMapping(value="/getYoYCount/{location}")
//	public List<Integer> getYoYCount(@PathVariable("location") String location){
//		List<Integer> yoyCount = new ArrayList<>();
//        yoyCount = trendServiceImpl.getByYoYCount(location);
//        return yoyCount;
//	}
}
