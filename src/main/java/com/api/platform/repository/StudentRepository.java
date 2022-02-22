package com.api.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.platform.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	Student findByStudentId(int userId);
	Student findByContact(String contact);
	Student findByEmail(String email);
}
