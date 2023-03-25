package com.acc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acc.domain.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {

}
