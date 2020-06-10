package com.tbs.sherkety.company.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tbs.sherkety.company.model.Company;

public interface CompanyDao extends JpaRepository<Company, Integer> {
	
	@Query(value ="SELECT c FROM Company c where lower(c.name) like lower(concat('%',:name,'%'))")
	List<Company> findByName(@Param("name") String name);
}
