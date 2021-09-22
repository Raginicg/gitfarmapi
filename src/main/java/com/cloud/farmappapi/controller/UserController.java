package com.cloud.farmappapi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.farmappapi.entity.User;
import com.cloud.farmappapi.exception.UserAlreadyExistException;
import com.cloud.farmappapi.repository.UserRepository;
import com.cloud.farmappapi.service.MapValidationErrorService;
import com.cloud.farmappapi.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	
	
	/**
	 * This mapping adds user to data base just change Validated to valid if problem on frontend
	 * @param user-  user object
	 * @param result- Result without errors
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<?> createNewDeveloper(@RequestBody User user, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		
		try {
			User savedUser = userService.saveUser(user);
			return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new UserAlreadyExistException("Client Already exists ! Please Login");
		}
		
	}
	
	@GetMapping("/{loginName}")
	public ResponseEntity<?> findUserByLoginName(@PathVariable String loginName,HttpSession session) 
	{
		//if (session.getAttribute("Role") != null && session.getAttribute("userType").equals("Client"))
		//{
		
		User foundUser=userService.findByLoginName(loginName);
		
		return new ResponseEntity<User>(foundUser, HttpStatus.OK);
		//}
		//return new ResponseEntity<String>("You do not have Access!!!", HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	/**
	 * Finds all the users in table
	 * @return
	 */
	@GetMapping("/all")
	public List<User> getAllUsers()
	{
		
		return userRepository.findAll();
	}
	
}
