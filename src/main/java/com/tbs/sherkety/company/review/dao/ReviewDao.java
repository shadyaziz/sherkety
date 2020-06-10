package com.tbs.sherkety.company.review.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbs.sherkety.company.review.model.Review;

public interface ReviewDao extends JpaRepository<Review, Integer>{
	
//	List<Review> findByCompany(Integer idCompany);
	
//	List<Review> findByUser(Integer idUser);
}
