package com.tbs.sherkety.review.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tbs.sherkety.company.model.Company;
import com.tbs.sherkety.login.model.User;
import com.tbs.sherkety.review.model.Review;

public interface ReviewDao extends JpaRepository<Review, Integer> {

  List<Review> findByCompany(Company company);

  Review findByCompanyAndUser(Company icompany, User user);
}
