package com.api.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.platform.entity.StudentDetails;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long>{
}
