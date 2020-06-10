package com.tbs.sherkety.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbs.sherkety.login.model.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
