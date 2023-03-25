package com.acc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.dao.UserRepository;
import com.acc.domain.User;
import com.acc.exception.EmailAlreadyExistsException;
import com.acc.exception.UserNotFoundException;
import com.acc.model.UserDTO;
import com.acc.model.UserUpdateRequest;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void createUser(UserDTO userDTO) throws EmailAlreadyExistsException {
		User user = new User();
		User existingUser = userRepository.findByEmail(userDTO.getEmail());
		if (existingUser != null) {
			throw new EmailAlreadyExistsException();
		}
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		userRepository.save(user);
	}

	public void updateUser(Integer id, UserUpdateRequest userUpdateRequest)
			throws UserNotFoundException, EmailAlreadyExistsException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (userUpdateRequest.getEmail() != null) {
				// Check if the new email already exists in the database
				User optionalExistingUser = userRepository.findByEmail(userUpdateRequest.getEmail());
				if (optionalExistingUser != null && !optionalExistingUser.getId().equals(id)) {
					throw new EmailAlreadyExistsException();
				}
				user.setEmail(userUpdateRequest.getEmail());
			}
			if (userUpdateRequest.getPassword() != null) {
				user.setPassword(userUpdateRequest.getPassword());
			}
			userRepository.save(user);
		} else {
			throw new UserNotFoundException();
		}
	}
}