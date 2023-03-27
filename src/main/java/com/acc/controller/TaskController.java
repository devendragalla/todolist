package com.acc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acc.domain.Task;
import com.acc.exception.ResourceNotFoundException;
import com.acc.exception.TasksNotFoundException;
import com.acc.exception.UserNotFoundException;
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
		return new ResponseEntity<Task>(task, HttpStatus.CREATED);
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
	public ResponseEntity<?> getAllTasks(@RequestHeader("userId") Integer userId, @RequestParam(name = "sortBy") String sortBy) throws UserNotFoundException {
		try {
			return ResponseEntity.ok(taskService.getAllTasks(userId, sortBy));
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no user with Id " + userId);
		} catch (TasksNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getErrorMessage()));
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getTasks(@RequestHeader("userId") Integer userId, @RequestParam(name = "searchKey") String searchKey) {
		try {
			return ResponseEntity.ok(taskService.getTasks(userId, searchKey));
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no user with Id " + userId);
		} catch (TasksNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getErrorMessage()));
		}
	}
	
	@GetMapping("/filter")
	public ResponseEntity<?> filterTasks(@RequestHeader("userId") Integer userId, @RequestParam(name = "categoryKey") String categoryKey) throws TasksNotFoundException {
		try {
			return ResponseEntity.ok(taskService.getFilteredTasks(userId, categoryKey));
		} catch (TasksNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getErrorMessage()));
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no user with Id " + userId);
		}
	}
	
	@GetMapping("/notify")
	public ResponseEntity<?> notifyTasks(@RequestHeader("userId") Integer userId) throws TasksNotFoundException, UserNotFoundException {
		try {
			return ResponseEntity.ok(taskService.getNotifiedTasks(userId));
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no user with Id " + userId);
		} catch (TasksNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getErrorMessage()));
		}
	}
}