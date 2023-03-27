package com.acc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acc.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
