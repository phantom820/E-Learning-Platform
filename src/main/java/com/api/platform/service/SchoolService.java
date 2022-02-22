package com.api.platform.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.platform.entity.School;
import com.api.platform.entity.Student;
import com.api.platform.repository.SchoolRepository;
import com.api.platform.util.ResponseCreator;


@Service
public class SchoolService {
	
	@Autowired
	private SchoolRepository schoolRepo;
	
	public ResponseEntity<List<JSONObject>> getSchools(String province,String name) {
		List<School> schools = schoolRepo.findByProvinceAndNameContaining(province, name);
		List<JSONObject> response = ResponseCreator.JSONResponse(schools);
		return new ResponseEntity<List<JSONObject>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<List<JSONObject>> getSchools(String province,String postalCode,String name) {
		List<School> schools = schoolRepo.findByProvinceAndPostalCodeContainingAndNameContaining(province,postalCode, name);
		List<JSONObject> response =  ResponseCreator.JSONResponse(schools);
		return new ResponseEntity<List<JSONObject>>(response, HttpStatus.OK);
	}
	
}
