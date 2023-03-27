package com.acc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acc.domain.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
	
	Status findByAction(String action);

}
