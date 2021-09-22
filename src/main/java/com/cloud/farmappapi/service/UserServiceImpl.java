package com.cloud.farmappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.farmappapi.entity.User;
import com.cloud.farmappapi.exception.UserAlreadyExistException;
import com.cloud.farmappapi.exception.UserNotFoundException;
import com.cloud.farmappapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public User saveUser(User user) {
		if (user.getUserName() == null || user.getLoginName() == null || user.getPwd() == null) {
			throw new NullPointerException("Please Enter All Values in the field");
		}
		
		if ((userRepository.findByLoginName(user.getLoginName())) != null) {
			
			throw new UserAlreadyExistException("User already exists");
		} 
		return userRepository.save(user);
		
	}

	/**
	 *
	 */
	@Override
	public User findByLoginName(String loginName) {
		
		User user=null;
		if (loginName == null) {
			
			throw new NullPointerException("Please Provide Login Name");
		}
		
		if ((user = userRepository.findByLoginName(loginName)) == null) {
			
			throw new UserNotFoundException("User with loginName : " + loginName + " does not exists");
		}
		return userRepository.findByLoginName(loginName);
		
	}

	/**
	 *
	 */
	@Override
	public List<User> findAll() {
		try {
			
			return userRepository.findAll();
		} catch (Exception e) {
			
			throw new UserNotFoundException("No User Found");
		}
	}

}
