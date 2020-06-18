package com.tbs.sherkety.token.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tbs.sherkety.token.model.Token;
import com.tbs.sherkety.user.model.User;

public interface TokenDao extends JpaRepository<Token, Integer> {

  Token findByUser(User user);

  Token findByCode(String code);

}
