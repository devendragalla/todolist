package com.acc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acc.domain.Task;
import com.acc.exception.ResourceNotFoundException;
import com.acc.model.ErrorResponse;
import com.acc.model.TaskDTO;
import com.acc.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/add")
	public ResponseEntity<Task> addTask(@RequestBody TaskDTO taskDTO) {
		Task task = taskService.save(taskDTO);
		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@PathVariable("id") Integer taskId, @RequestBody TaskDTO taskDto) {
		Task updatedTask;
		try {
			updatedTask = taskService.updateTask(taskId, taskDto);
			return ResponseEntity.ok(updatedTask);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getErrorMessage()));
		}
	}

	@GetMapping()
	public List<Task> getAllTasks(@RequestParam(name = "sortBy") String sortBy) {
		return taskService.getAllTasks(sortBy);
	}
	
	@GetMapping("/search")
	public List<Task> getTasks(@RequestParam(name = "searchKey") String searchKey) {
		return taskService.getTasks(searchKey);
	}
}