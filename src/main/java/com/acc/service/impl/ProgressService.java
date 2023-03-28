package com.acc.service.impl;

import java.time.LocalDateTime;
import com.acc.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acc.domain.Task;

@Service
public class ProgressService {

	private static final String IN_PROGRESS = "INPROGRESS";

	public ArrayList<Task> getPendingTasksAfterDueDate(List<Task> tasks) {
		
		ArrayList<Task> inProgressTasks = new ArrayList<>();
		
		LocalDateTime currentDate = LocalDateTime.now();
		
		for (Task task : tasks) {
			if (task.getStatus() != null && task.getStatus().getAction().equals(IN_PROGRESS)
					&& task.getDueDate().isBefore(currentDate)) {
				inProgressTasks.add(task);
			}
		}
		
		return inProgressTasks;
	}
}
