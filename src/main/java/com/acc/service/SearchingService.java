package com.acc.service;

import java.util.List;

import com.acc.domain.Task;

public interface SearchingService {

	public List<Task> search(List<Task> tasks, String searchString);

}
