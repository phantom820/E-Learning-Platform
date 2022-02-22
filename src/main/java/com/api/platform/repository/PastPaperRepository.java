package com.api.platform.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.platform.entity.PastPaper;

public interface PastPaperRepository  extends JpaRepository<PastPaper,Long> {
	List<PastPaper>findByGradeAndSubject(short grade,String subject);
	List<PastPaper>findByGradeAndSubjectAndDate(short grade,String subject,Date date);
}
