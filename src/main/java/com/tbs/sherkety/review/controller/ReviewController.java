package com.tbs.sherkety.review.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tbs.sherkety.common.exception.GeneralInputException;
import com.tbs.sherkety.review.dao.ReviewDao;
import com.tbs.sherkety.review.exception.ReviewAlreadyExistException;
import com.tbs.sherkety.review.model.Review;

@RestController
public class ReviewController {
  private final Logger logger = LogManager.getLogger(ReviewController.class);

  @Autowired
  private ReviewDao reviewDao;

  @PostMapping("/review/save")
  public ResponseEntity<HttpStatus> saveReview(@RequestBody @Valid Review review) {
    Integer idUser = null;
    Integer idCompany = null;
    try {
      idUser = Optional.ofNullable(review.getUser().getIdUser()).orElseThrow(() -> {
        logger.error("saveReview null idUser input ");
        return new GeneralInputException();
      });

      idCompany = Optional.ofNullable(review.getCompany().getIdCompany()).orElseThrow(() -> {
        logger.error("saveReview null idCompany input ");
        return new GeneralInputException();
      });

      Optional<Review> dbReview = Optional
          .ofNullable(reviewDao.findByCompanyAndUser(review.getCompany(), review.getUser()));

      if (dbReview.isPresent()) {
        logger.error("The following user {} has already review the company {}", idUser, idCompany);
        throw new ReviewAlreadyExistException();
      }

      reviewDao.save(review);

      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    } catch (GeneralInputException | ReviewAlreadyExistException e) {
      logger.error("saveReview , Exception with the following userId : {} and companyId : {}",
          idUser, idCompany, e);
    }
    return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
  }



}
