package com.acc.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.dao.PriorityRepository;
import com.acc.dao.StatusRepository;
import com.acc.dao.TaskRepository;
import com.acc.dao.UserRepository;
import com.acc.domain.Status;
import com.acc.domain.Task;
import com.acc.domain.User;
import com.acc.exception.ResourceNotFoundException;
import com.acc.exception.TasksNotFoundException;
import com.acc.exception.UserNotFoundException;
import com.acc.model.TaskDTO;
import com.acc.service.SortingService;
import com.acc.service.TaskService;

@Service
public class TaskServiceImpl  implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private StatusRepository statusRepository;
    
    @Autowired
    private SortingService sortingService;
    
    @Autowired
    private SearchingServiceImpl searchingService;
    
    @Autowired
    private CategoryFilterService categoryService;
    
    @Autowired
    private NotificationService notificationService;

    @Override
    public Task save(TaskDTO taskDTO) {
        Task task = new Task();
        task.setUser(userRepository.findById(taskDTO.getUserId()).orElse(null));
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(priorityRepository.findById(taskDTO.getPriorityId()).orElse(priorityRepository.findById(0).get()));
        task.setDueDate(LocalDateTime.parse(taskDTO.getDueDate()));
        task.setCategoryType(taskDTO.getCategoryType());
        Status status = statusRepository.findByAction(taskDTO.getStatus());
        if(status != null) {
        	 task.setStatus(status);
        } else {
        	task.setStatus(statusRepository.findByAction("TODO"));
        }
        task.setCreatedDate(LocalDateTime.now());
        task.setUpdatedDate(LocalDateTime.now());
        task.setStatusUpdatedDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Integer taskId, TaskDTO taskDTO) throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        if (taskDTO.getTitle() != null) {
			task.setTitle(taskDTO.getTitle());
		}
		if (taskDTO.getDescription() != null) {
			task.setDescription(taskDTO.getDescription());
		}
		if (taskDTO.getPriorityId() != null) {
			task.setPriority(priorityRepository.findById(taskDTO.getPriorityId())
					.orElse(priorityRepository.findById(0).get()));
		}
		if (taskDTO.getDueDate() != null) {
			task.setDueDate(LocalDateTime.parse(taskDTO.getDueDate()));
		}
		if (taskDTO.getCategoryType() != null) {
			task.setCategoryType(taskDTO.getCategoryType());
		}
		if (taskDTO.getStatus() != null) {
			Status status = statusRepository.findByAction(taskDTO.getStatus());
			if (status != null) {
				task.setStatus(status);
			} else {
				task.setStatus(statusRepository.findByAction("TODO"));
			} 
		}
		task.setStatusUpdatedDate(LocalDateTime.now());
        Task updatedTask = taskRepository.save(task);
        return updatedTask;
    }

	@Override
	public List<Task> getAllTasks(Integer userId, String sortBy) throws UserNotFoundException, TasksNotFoundException  {
		List<Task> sortedTasks = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			List<Task> tasks = taskRepository.findByUser(user.get());
			if (tasks != null && !tasks.isEmpty()) {
				sortedTasks =sortingService.sort(tasks, sortBy);
			} else {
				throw new TasksNotFoundException(" There are no avaliable tasks.");
			}
		} else {
			throw new UserNotFoundException();
		}
		
		return sortedTasks;
	}

	@Override
	public List<Task> getTasks(Integer userId, String searchKey) throws TasksNotFoundException, UserNotFoundException {
		List<Task> searchedTasks = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			List<Task> tasks = taskRepository.findByUser(user.get());
			if (tasks != null && !tasks.isEmpty()) {
				searchedTasks = searchingService.search(tasks, searchKey);
			} else {
				throw new TasksNotFoundException(" There are no avaliable tasks.");
			}
		} else {
			throw new UserNotFoundException();
		}

		return searchedTasks;
	}
	
	@Override 
	public List<Task> getFilteredTasks(Integer userId, String CategoryKey) throws TasksNotFoundException, UserNotFoundException{
		List<Task> filteredTasks = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			List<Task> tasks = taskRepository.findByUser(user.get());
			if (tasks != null && !tasks.isEmpty()) {
				filteredTasks = categoryService.filterByCategory(tasks, CategoryKey);
			} else {
				throw new TasksNotFoundException(" There are no avaliable tasks.");
			}
		} else {
			throw new UserNotFoundException();
		}

		return filteredTasks;
	}

	@Override
	public List<Task> getNotifiedTasks(Integer userId) throws UserNotFoundException, TasksNotFoundException {
		List<Task> notifiedTasks = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			List<Task> tasks = taskRepository.findByUser(user.get());
			if (tasks != null && !tasks.isEmpty()) {
				notifiedTasks = notificationService.getTasksDueToday(tasks);
			} else {
				throw new TasksNotFoundException(" There are no avaliable tasks for today");
			}
		} else {
			throw new UserNotFoundException();
		}

		return notifiedTasks;
	}

	@Override
	public void deleteTask(Integer taskId, Integer userId) throws UserNotFoundException, TasksNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			Optional<Task> task = taskRepository.findById(taskId);
			if(task.isPresent()) {
				taskRepository.deleteById(task.get().getTaskId());
			} else {
				throw new TasksNotFoundException("There is no Task with Id " + taskId);
			}
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public List<Task> getInProgressTasks(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
