package com.portifolio.torresprata.dao;

import com.portifolio.torresprata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
