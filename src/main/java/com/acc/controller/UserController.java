package com.acc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acc.domain.User;
import com.acc.exception.EmailAlreadyExistsException;
import com.acc.exception.EmailNotFoundException;
import com.acc.exception.TodolistException;
import com.acc.exception.UserNotFoundException;
import com.acc.model.ErrorResponse;
import com.acc.model.UserDTO;
import com.acc.model.UserUpdateRequest;
import com.acc.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
		try {
			userService.createUser(userDTO);
			return ResponseEntity.ok(userDTO);
		} catch (EmailAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Email already exists"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserUpdateRequest userUpdateRequest) {
		try {
			userService.updateUser(id, userUpdateRequest);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found"));
		} catch (EmailAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Email already exists"));
		}
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws EmailNotFoundException, TodolistException {
	        try {
				User user = userService.validateCredentials(userDTO);
				    return ResponseEntity.ok(user);
			} catch (EmailNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getErrorMessage()));
			} catch (TodolistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getErrorMessage()));
			}
	    }
}