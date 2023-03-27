package com.acc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.domain.Task;
import com.acc.service.SortingService;

@Service
public class SortingServiceImpl implements SortingService {

	@Autowired
	TaskSorter taskSorter;

	@Override
	public List<Task> sort(List<Task> tasks, String sortBy) {
		if (sortBy.equals("Priority")) {
			taskSorter.sortByPriority(tasks);
		} else if (sortBy.equals("lastdate")) {
			taskSorter.sortbyUpdatedTime(tasks);
		} else if (sortBy.equals("lastdate")) {
			taskSorter.sortByDueDate(tasks);
		}
		return tasks;
	}
}
