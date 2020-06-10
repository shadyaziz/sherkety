package com.tbs.sherkety.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbs.sherkety.company.dao.CompanyDao;
import com.tbs.sherkety.company.model.Company;
import com.tbs.sherkety.company.review.dao.ReviewDao;
import com.tbs.sherkety.company.review.model.Review;
import com.tbs.sherkety.login.dao.UserDao;
import com.tbs.sherkety.login.model.User;

@RestController
public class CompanyController {

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private UserDao userDao;
	
//	@RequestMapping("/company/{companyId}")
//	public String getCompanyProfile(@PathVariable String companyId, Model model) {
//		Optional<Company> company =  companyDao.findById(NumberUtils.toInt(companyId));
//		
//		if(company.isPresent()) {
//			model.addAttribute(company.get());
//			return PageName.COMPANY_PAGE.getValue() ;
//		}else {
//			return "error";
//		}
//	}
	
	@GetMapping("/company")
	public List<Company> getAllCompanies(){
		return companyDao.findAll();
	}
	
	@GetMapping("/review")
	public List<Review> getAllReviews(){
		return reviewDao.findAll();
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers(){
		return userDao.findAll();
	}
}
