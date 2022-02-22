package com.api.platform.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.platform.entity.Student;
import com.api.platform.repository.StudentRepository;
import com.api.platform.service.StudentService;


@CrossOrigin(
		allowCredentials = "false",
		origins = "*",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@SuppressWarnings("unchecked")
@RestController
public class StudentController {
	
	@Autowired
	private StudentService userService;
	
	@GetMapping("")
	public void home(HttpServletResponse httpServletResponse) {
		 httpServletResponse.setHeader("Location", "https://google.com");
		 httpServletResponse.setStatus(302);
	}
	
	@GetMapping("/users/{userId}")
	ResponseEntity<JSONObject> getStudent(@PathVariable String studentId) {
		return userService.getStudentById(Integer.parseInt(studentId));
	}
	
	@PostMapping("/login")
	ResponseEntity<JSONObject> loginStudent(@RequestBody Student student) {
		return userService.loginStudent(student);
	}
	
	@PostMapping("/users")
	ResponseEntity<JSONObject> addStudent(@RequestBody Student student) {
		return userService.addStudent(student);
	}
	
}
