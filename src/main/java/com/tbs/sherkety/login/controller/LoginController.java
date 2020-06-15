package com.tbs.sherkety.login.controller;

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
import com.tbs.sherkety.login.LoginUtils;
import com.tbs.sherkety.login.dao.UserDao;
import com.tbs.sherkety.login.exception.PasswordIncorrectException;
import com.tbs.sherkety.login.exception.UserAlreadyExistException;
import com.tbs.sherkety.login.exception.UserNotFoundException;
import com.tbs.sherkety.login.model.User;
import com.tbs.sherkety.login.model.view.UserSignIn;

@RestController
public class LoginController {

  private final Logger logger = LogManager.getLogger(LoginController.class);

  @Autowired
  private UserDao userDao;

  @PostMapping("/signin")
  public ResponseEntity<HttpStatus> signin(@RequestBody User user) {

    try {
      UserSignIn dbUser = Optional.ofNullable(userDao.findByEmail(user.getEmail()))
          .orElseThrow(() -> new UserNotFoundException());

      if (!LoginUtils.comparePasswords(user.getPassword(), dbUser.getEncodedPassword())) {
        throw new PasswordIncorrectException();
      }
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    } catch (UserNotFoundException | PasswordIncorrectException e) {
      logger.error("user : {} is invalid or entered wrong password", user.getEmail(), e);
    }

    return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/signup")
  public ResponseEntity<HttpStatus> signup(@RequestBody @Valid User user) {

    try {
      if (Optional.ofNullable(userDao.findByEmail(user.getEmail())).isPresent()) {
        throw new UserAlreadyExistException();
      }

      user.setEncodedPassword(LoginUtils.getEncodedString(user.getPassword()));
      userDao.save(user);
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);

    } catch (UserAlreadyExistException e) {
      logger.error("user : {} already exists", user.getEmail(), e);
    }
    return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
  }
}
