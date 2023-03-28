package com.acc.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acc.domain.Task;

@Service
public class ProgressService {

	private static final String IN_PROGRESS = "INPROGRESS";

	public List<Task> getPendingTasksAfterDueDate(List<Task> tasks) {
		
		List<Task> inProgressTasks = new ArrayList<>();
		
		LocalDateTime currentDate = LocalDateTime.now();
		
		for (Task task : tasks) {
			if (task.getStatus().equals(IN_PROGRESS) && task.getDueDate().isBefore(currentDate)) {
				inProgressTasks.add(task);
			}
		}
		
		return inProgressTasks;
	}
}
