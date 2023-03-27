package com.acc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.acc.domain.Task;
import com.acc.exception.TasksNotFoundException;

@Service
public class CategoryFilterService {

	public List<Task> filterByCategory(List<Task> tasks, String categoryKey) throws TasksNotFoundException {
		// Create a HashMap to store tasks by category
		Map<String, List<Task>> tasksByCategory = new HashMap<>();

		for (Task task : tasks) {
			String category = task.getCategoryType() != null ? task.getCategoryType() : null ;
			if (category != null) {
				if (!tasksByCategory.containsKey(category.toLowerCase())) {
					tasksByCategory.put(category.toLowerCase(), new ArrayList<>());
				}
				tasksByCategory.get(category.toLowerCase()).add(task);
			}
		}
		
		List<Task> filteredTasks = tasksByCategory.get(categoryKey);
		
		if (filteredTasks != null && !filteredTasks.isEmpty()) {
			return filteredTasks;
		} else {
			throw new TasksNotFoundException("No Tasks Found matching with category key " + categoryKey);
		}
	}
}
