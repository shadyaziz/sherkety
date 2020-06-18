package com.tbs.sherkety.token;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tbs.sherkety.token.dao.TokenDao;
import com.tbs.sherkety.token.exception.TokenInvalidException;
import com.tbs.sherkety.token.exception.TokenNotFoundException;
import com.tbs.sherkety.token.model.Token;
import com.tbs.sherkety.user.model.User;
import com.tbs.sherkety.user.model.constant.UserOperation;

@Service
public class TokenService {
  private final Logger logger = LogManager.getLogger(TokenService.class);

  @Autowired
  private TokenDao tokenDao;

  public User isTokenValidAndGetUser(String code)
      throws TokenNotFoundException, TokenInvalidException {
    Token dbToken = Optional.ofNullable(tokenDao.findByCode(code))
        .orElseThrow(() -> new TokenNotFoundException());
    if (LocalDateTime.now().isAfter(dbToken.getExpiryDate())) {
      throw new TokenInvalidException();
    }
    return dbToken.getUser();
  }

  public String buildAndSaveToken(User user, UserOperation userOperation) {
    logger.debug("Building Token with operation {}", userOperation.getValue());
    Token token = new Token();
    token.setUser(user);
    token.setExpiryDate(LocalDateTime.now().plusDays(1));
    token.setCode(UUID.randomUUID().toString());
    token.setOperation(userOperation);
    token.setTokenCount(1);
    tokenDao.save(token);
    return token.getCode();
  }
}
