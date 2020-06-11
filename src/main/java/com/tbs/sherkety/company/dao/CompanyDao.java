package com.tbs.sherkety.company.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tbs.sherkety.company.model.Company;
import com.tbs.sherkety.company.model.CompanyView;

public interface CompanyDao extends JpaRepository<Company, Integer> {

  @Query(value = "SELECT c FROM Company c where lower(c.name) like lower(concat('%',:name,'%'))")
  List<CompanyView> findByName(@Param("name") String name);
}
