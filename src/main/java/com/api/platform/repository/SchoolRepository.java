package com.api.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.platform.entity.School;

public interface SchoolRepository extends  JpaRepository<School, Long> {
	List<School> findByProvinceAndNameContaining(String province,String name);
	List<School> findByProvinceAndPostalCodeContainingAndNameContaining(String province,String postalCode,String name);
}
