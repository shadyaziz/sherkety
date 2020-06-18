package com.tbs.sherkety.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tbs.sherkety.user.model.User;
import com.tbs.sherkety.user.model.view.UserSignIn;

public interface UserDao extends JpaRepository<User, Integer> {

  UserSignIn findByEmail(String email);

}
