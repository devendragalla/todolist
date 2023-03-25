package com.acc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acc.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
