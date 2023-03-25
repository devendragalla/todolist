package com.acc.service;

import java.util.List;

import com.acc.domain.Task;

public interface SortingService {

	public List<Task> sort(List<Task> tasks, String sortBy);
}
