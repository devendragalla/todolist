package com.acc.service;

import java.util.List;

import com.acc.domain.Task;
import com.acc.exception.ResourceNotFoundException;
import com.acc.model.TaskDTO;

public interface TaskService {

	public Task save(TaskDTO taskDTO);

	public Task updateTask(Integer taskId, TaskDTO taskDTO) throws ResourceNotFoundException;

	public List<Task> getAllTasks(String sortBy);

	public List<Task> getTasks(String searchKey);
}
