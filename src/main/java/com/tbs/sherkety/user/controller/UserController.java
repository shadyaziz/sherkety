package com.tbs.sherkety.user.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tbs.sherkety.service.EmailService;
import com.tbs.sherkety.token.TokenService;
import com.tbs.sherkety.token.exception.TokenInvalidException;
import com.tbs.sherkety.token.exception.TokenNotFoundException;
import com.tbs.sherkety.user.dao.UserDao;
import com.tbs.sherkety.user.login.LoginUtils;
import com.tbs.sherkety.user.login.exception.PasswordIncorrectException;
import com.tbs.sherkety.user.login.exception.UserAlreadyExistException;
import com.tbs.sherkety.user.login.exception.UserNotActiveException;
import com.tbs.sherkety.user.login.exception.UserNotFoundException;
import com.tbs.sherkety.user.model.User;
import com.tbs.sherkety.user.model.constant.UserOperation;
import com.tbs.sherkety.user.model.constant.UserStatus;
import com.tbs.sherkety.user.model.view.UserSignIn;
import com.tbs.sherkety.user.validation.TransientGroup;

@RestController
public class UserController {
  private final Logger logger = LogManager.getLogger(UserController.class);

  @Autowired
  private UserDao userDao;

  @Autowired
  private EmailService emailService;

  @Autowired
  private TokenService tokenService;


  @PostMapping("/signin")
  public ResponseEntity<HttpStatus> signin(@RequestBody @Valid User user) {

    try {
      UserSignIn dbUser = Optional.ofNullable(userDao.findByEmail(user.getEmail()))
          .orElseThrow(() -> new UserNotFoundException());

      if (dbUser.getUserStatus() != UserStatus.ACTIVE) {
        logger.error("User {} isnot aktive user", dbUser.getEmail());
        throw new UserNotActiveException();
      }

      if (!LoginUtils.comparePasswords(user.getPassword(), dbUser.getEncodedPassword())) {
        logger.error("User {} entered inncorrect password", dbUser.getEmail());
        throw new PasswordIncorrectException();
      }
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    } catch (UserNotFoundException e) {
      logger.error("user email {} is invalid or not found", user.getEmail(), e);
    } catch (PasswordIncorrectException e) {
      logger.error("Password is invalid for user {}", user.getEmail(), e);
    } catch (UserNotActiveException e) {
      logger.error("User {} has no active status", user.getEmail(), e);
    }

    return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/signup")
  public ResponseEntity<HttpStatus> signup(@RequestBody @Validated(TransientGroup.class) User user,
      HttpServletRequest request) {

    try {
      if (Optional.ofNullable(userDao.findByEmail(user.getEmail())).isPresent()) {
        logger.error("User {} already exists ", user.getEmail());
        throw new UserAlreadyExistException();
      }

      user.setEncodedPassword(LoginUtils.getEncodedString(user.getPassword()));
      user.setUserStatus(UserStatus.PENDING);
      userDao.save(user);

      String token = tokenService.buildAndSaveToken(user, UserOperation.REGISTRATION);
      String url = LoginUtils.buildUrlRegisterToken(request.getScheme(), request.getServerName(),
          request.getServerPort(), token);

      emailService.sendRegistrationEmail(user.getEmail(), url);


      return new ResponseEntity<HttpStatus>(HttpStatus.OK);

    } catch (UserAlreadyExistException e) {
      logger.error("user : {} already exists", user.getEmail(), e);
    }
    return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/register/{token}")
  public ResponseEntity<HttpStatus> confirmEmailRegistration(@PathVariable @NotEmpty String token) {
    try {
      User user = tokenService.isTokenValidAndGetUser(token);
      user.setUserStatus(UserStatus.ACTIVE);
      userDao.save(user);

      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    } catch (TokenNotFoundException e) {
      logger.error("Token {} cannot be found ", token, e);
    } catch (TokenInvalidException e) {
      logger.error("Token {} is invalid or expired", token, e);
    }

    return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
  }
}
