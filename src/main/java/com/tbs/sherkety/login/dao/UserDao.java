package com.tbs.sherkety.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tbs.sherkety.login.model.User;
import com.tbs.sherkety.login.model.view.UserSignIn;

public interface UserDao extends JpaRepository<User, Integer> {

  UserSignIn findByEmail(String email);

}
