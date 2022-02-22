package com.api.platform.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.platform.service.SchoolService;


@CrossOrigin(
		allowCredentials = "false",
		origins = "*",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@SuppressWarnings("unchecked")
@RestController
public class SchoolController {
	
	
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping("/schools")
	ResponseEntity<List<JSONObject>> 
	getFilteredSchools(@RequestParam String province,@RequestParam(required=false) String postalCode,
			@RequestParam String name) {
		if(postalCode==null) {
			return schoolService.getSchools(province,name);
		}
		
		return schoolService.getSchools(province,postalCode,name);
		
	}
}
