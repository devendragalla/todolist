package com.acc.service.impl;

import java.time.LocalDateTime;
import java.util.LinkedList;
import com.acc.domain.Task;

public class ProgressService {
	private LinkedList<Task> taskList;

	public ProgressService() {
		this.taskList = new LinkedList<>();
	}

	public void addTask(Task task) {
		taskList.add(task);
	}

	public int getPendingTasksAfterDueDate(int userId, LocalDateTime currentDate, String status) {
		int count = 0;
		for (Task task : taskList) {
			if (task.getStatus().equals(status) && task.getDueDate().isBefore(currentDate)) {
				if (task.getStatus().equals("Pending")) {
					count++;
				}
			}
		}
		return count;
	}
}
