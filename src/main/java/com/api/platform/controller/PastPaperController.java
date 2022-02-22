package com.api.platform.controller;

import java.sql.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.platform.service.PastPaperService;

@CrossOrigin(
		allowCredentials = "false",
		origins = "*",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@SuppressWarnings("unchecked")
@RestController
public class PastPaperController {

		@Autowired
		private PastPaperService pastPaperService;
		
		@GetMapping("/papers")
		ResponseEntity<List<JSONObject>> getPastPapers(@RequestParam short grade,@RequestParam String subject,@RequestParam(required=false) Date date){
				return pastPaperService.getPastPapers(grade, subject, date);
		}
}
