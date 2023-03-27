package com.acc.service;

import com.acc.exception.EmailAlreadyExistsException;
import com.acc.exception.EmailNotFoundException;
import com.acc.exception.UserNotFoundException;
import com.acc.model.UserDTO;
import com.acc.model.UserUpdateRequest;

public interface UserService {

	public void createUser(UserDTO userDTO) throws EmailAlreadyExistsException;

	public void updateUser(Integer id, UserUpdateRequest userUpdateRequest)
			throws UserNotFoundException, EmailAlreadyExistsException;

	public boolean validateCredentials(UserDTO userDTO) throws EmailNotFoundException;
}
