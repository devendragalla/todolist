package com.acc.service;

import java.util.List;

import com.acc.domain.Task;
import com.acc.exception.ResourceNotFoundException;
import com.acc.exception.TasksNotFoundException;
import com.acc.exception.UserNotFoundException;
import com.acc.model.TaskDTO;

public interface TaskService {

	public Task save(TaskDTO taskDTO);

	public Task updateTask(Integer taskId, TaskDTO taskDTO) throws ResourceNotFoundException;

	public List<Task> getAllTasks(Integer userId, String sortBy) throws UserNotFoundException, TasksNotFoundException;

	public List<Task> getTasks(Integer userId, String searchKey) throws TasksNotFoundException, UserNotFoundException;

	List<Task> getFilteredTasks(Integer userId, String CategoryKey) throws TasksNotFoundException, UserNotFoundException;

	public List<Task> getNotifiedTasks(Integer userId) throws UserNotFoundException, TasksNotFoundException;

	public void deleteTask(Integer taskId, Integer userId) throws UserNotFoundException, TasksNotFoundException;
}

