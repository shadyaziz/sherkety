package com.tbs.sherkety.company.controller;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tbs.sherkety.company.dao.CompanyDao;
import com.tbs.sherkety.company.exception.CompanyNotFoundException;
import com.tbs.sherkety.company.model.Company;
import com.tbs.sherkety.company.model.CompanyView;
import com.tbs.sherkety.company.review.dao.ReviewDao;
import com.tbs.sherkety.company.review.model.Review;

@RestController
public class CompanyController {

  private final Logger logger = LogManager.getLogger(CompanyController.class);

  @Autowired
  private CompanyDao companyDao;

  @Autowired
  private ReviewDao reviewDao;

  @GetMapping("/company")
  public List<Company> getAllCompanies() {
    return companyDao.findAll();
  }

  @GetMapping("/review")
  public List<Review> getAllReviews() {
    return reviewDao.findAll();
  }

  @GetMapping("/company/{companyId}")
  public ResponseEntity<Company> getCompany(@PathVariable @NotNull Integer companyId) {
    try {
      Company company =
          companyDao.findById(companyId).orElseThrow(() -> new CompanyNotFoundException());
      return new ResponseEntity<Company>(company, HttpStatus.OK);
    } catch (CompanyNotFoundException e) {
      logger.error("Company : {} is not found", companyId, e);
    }

    return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);

  }

  @GetMapping("/company/search/{name}")
  public ResponseEntity<List<CompanyView>> getCompanyList(@PathVariable @NotNull String name) {
    List<CompanyView> companyList = companyDao.findByName(name);
    return new ResponseEntity<List<CompanyView>>(companyList, HttpStatus.OK);
  }

}
