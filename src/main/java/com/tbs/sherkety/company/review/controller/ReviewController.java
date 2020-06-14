package com.tbs.sherkety.company.review.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tbs.sherkety.company.dao.CompanyDao;
import com.tbs.sherkety.company.review.dao.ReviewDao;
import com.tbs.sherkety.company.review.model.Review;

@RestController
public class ReviewController {
  private final Logger logger = LogManager.getLogger(ReviewController.class);

  @Autowired
  private ReviewDao reviewDao;

  @Autowired
  private CompanyDao companyDao;

  @PostMapping("/review/save")
  public ResponseEntity<HttpStatus> saveReview(@RequestBody @Valid Review review) {

    reviewDao.save(review);

    return new ResponseEntity<HttpStatus>(HttpStatus.OK);
  }



}
