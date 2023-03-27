package com.acc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acc.domain.Task;
import com.acc.domain.User;

public interface TaskRepository extends JpaRepository<Task, Integer>{

	List<Task> findByUser(User user);
}
