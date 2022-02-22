package com.api.platform.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.platform.entity.Student;
import com.api.platform.repository.StudentDetailsRepository;
import com.api.platform.repository.StudentRepository;
import com.api.platform.security.SecurityUtil;
import com.api.platform.util.ResponseCreator;
import com.google.gson.Gson;


@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired StudentDetailsRepository detailsRepo;
	
	private JSONObject studentJSON (Student student) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put("studentId",student.getStudentId());
		response.put("name", student.getName());
		response.put("surname", student.getSurname());
		response.put("contact", student.getContact());
		response.put("email", student.getEmail());
		JSONObject json  = new JSONObject(response);
		return json;
	}
	
	private Student extractStudent(JSONObject data) {
		Student student = new Student();
		student.setName((String)data.get("name"));
		student.setSurname((String)data.get("surname"));
		student.setContact((String)data.get("contact"));
		student.setEmail((String)data.get("email"));
		student.setPassword((String)data.get("password"));
		return student;
	}

	public ResponseEntity<JSONObject> getStudentById(int studentId) {
		Student student = studentRepo.findByStudentId(studentId);
		if(student!=null) {
			JSONObject responseJSON = studentJSON(student);
			return new ResponseEntity<JSONObject>(responseJSON, HttpStatus.OK);
		}
		JSONObject responseJSON = ResponseCreator.ErrorResponse("student does not exist.");
		return new ResponseEntity<JSONObject>(responseJSON, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<JSONObject> loginStudent(Student student) {
		Student dbStudent;
		dbStudent = studentRepo.findByContact(student.getContact());
		if(dbStudent != null && SecurityUtil.verifyPassword(student.getPassword(),dbStudent.getPassword())) {
			JSONObject responseJSON = studentJSON(dbStudent);
			return new ResponseEntity<JSONObject>(responseJSON, HttpStatus.OK);
		}
		dbStudent = studentRepo.findByEmail(student.getEmail());
		if (dbStudent != null && SecurityUtil.verifyPassword(student.getPassword(),dbStudent.getPassword())) {
			JSONObject responseJSON = studentJSON(dbStudent);
			return new ResponseEntity<JSONObject>(responseJSON, HttpStatus.OK);
		}
		JSONObject responseJSON = ResponseCreator.ErrorResponse("student does not exist.");
		return new ResponseEntity<JSONObject>(responseJSON, HttpStatus.NOT_FOUND);
	}
	
	@Transactional
	public ResponseEntity<JSONObject> addStudent(Student student) {
		if(studentRepo.findByContact(student.getContact())!=null || studentRepo.findByEmail(student.getEmail())!=null ) {
			JSONObject responseJSON = ResponseCreator.ErrorResponse("student already registered.");
			return new ResponseEntity<JSONObject>(responseJSON, HttpStatus.BAD_REQUEST);
		}
		student.setPassword(SecurityUtil.encodePassword(student.getPassword()));
		studentRepo.save(student);
		student.getStudentDetails().setStudent(student);
		detailsRepo.save(student.getStudentDetails());
		JSONObject responseJSON = studentJSON(student);
		return new ResponseEntity<JSONObject>(responseJSON, HttpStatus.OK);
	}
}
