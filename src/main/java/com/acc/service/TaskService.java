package com.acc.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.dao.PriorityRepository;
import com.acc.dao.StatusRepository;
import com.acc.dao.TaskRepository;
import com.acc.dao.UserRepository;
import com.acc.domain.Status;
import com.acc.domain.Task;
import com.acc.exception.ResourceNotFoundException;
import com.acc.model.TaskDTO;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private StatusRepository statusRepository;

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
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
