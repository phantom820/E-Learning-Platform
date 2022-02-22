package com.api.platform.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.api.platform.entity.PastPaper;
import com.api.platform.entity.School;
import com.api.platform.repository.PastPaperRepository;
import com.api.platform.util.ResponseCreator;

@Service
public class PastPaperService {

	@Autowired
	private PastPaperRepository pastPaperRepo;
	
	@SuppressWarnings("unchecked")
	private JSONObject toJSON(PastPaper pastPaper) {
		JSONObject json = new JSONObject();
		json.put("subject",pastPaper.getSubject());
		json.put("grade",pastPaper.getGrade());
		json.put("paper_no",pastPaper.getPaperNo());
		json.put("paper_id",pastPaper.getPaperId());
		json.put("date",pastPaper.getDate().toString());
		json.put("province",pastPaper.getProvince());
		json.put("paper",pastPaper.getPaperUrl());
		return json;
	}
	
	@SuppressWarnings("unchecked")
	private List<JSONObject> toJSON(List<PastPaper> pastPapers) {
		List<JSONObject> response = new ArrayList<>();
		pastPapers.forEach(pastPaper->{
			response.add(toJSON(pastPaper));
		});
		return response;
	}
	
	public ResponseEntity<List<JSONObject>> getPastPapers(short grade,String subject,Date date) {
		if(date==null) {
			List<PastPaper> pastPapers = pastPaperRepo.findByGradeAndSubject(grade, subject);
			List<JSONObject> response = toJSON(pastPapers);
			return new ResponseEntity<List<JSONObject>>(response, HttpStatus.OK);
		}
		
		List<PastPaper> pastPapers = pastPaperRepo.findByGradeAndSubjectAndDate(grade, subject, date);
		System.out.println(pastPapers.size());
		List<JSONObject> response = ResponseCreator.JSONResponse(pastPapers);
		return new ResponseEntity<List<JSONObject>>(response, HttpStatus.OK);
	}
}
