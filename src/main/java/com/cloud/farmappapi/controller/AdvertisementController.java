/**
 * 
 */
package com.cloud.farmappapi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.farmappapi.entity.Advertisement;
import com.cloud.farmappapi.exception.AdvertisementAlreadyExistException;
import com.cloud.farmappapi.service.AdvertisementService;
import com.cloud.farmappapi.service.MapValidationErrorService;
import com.cloud.farmappapi.utilities.GlobalLogger;

/**
 * @author Sanjeet
 * 
 */
@RestController
@RequestMapping("/api/user/advertisement")
public class AdvertisementController {
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	/*
	 * Logger used o track the log.
	 */
	private static final Logger logger = GlobalLogger.getLogger(AdvertisementController.class);
	
	@PostMapping("/create")
	public ResponseEntity<?> createNewAdvertisement(@RequestBody Advertisement advertisement, BindingResult result) {
		
		logger.info("Inside Controller Add Advertisement");
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		
		try {
			Advertisement savedAdvertisement = advertisementService.saveAdvertisement(advertisement);
			return new ResponseEntity<Advertisement>(savedAdvertisement, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new AdvertisementAlreadyExistException("Advertisement Already exists! Please Add New Advertisement");
		}	
	}
	
	
	@GetMapping("/{title}")
	public ResponseEntity<?> findAdvertisementByTitle(@PathVariable String title,HttpSession session) 
	{
		//if (session.getAttribute("Role") != null && session.getAttribute("userType").equals("Client"))
		//{
		
		logger.info("Inside Controller View Advertisement by Title");
		// Spring Boot makes use of Apache Commons' Logging for its system logs by default (SLF4J)
		
		Advertisement foundAdvertisement=advertisementService.findBytitle(title);
		
		return new ResponseEntity<Advertisement>(foundAdvertisement, HttpStatus.OK);
		//}
		//return new ResponseEntity<String>("You do not have Access!!!", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllAdvertisements()
	{
		
		logger.info("Inside Advertisement View all Advertisements");
		
		List<Advertisement> advertisements = advertisementService.findAll();
		return new ResponseEntity<List<Advertisement>>(advertisements, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8084/Farm-api/advertisement/advertisement/{advertiseIdentifier}   
	 * detete mapping
	 */
	
	@DeleteMapping("/{advertiseIdentifier}")
	public ResponseEntity<?> deleteAdvertisement(@PathVariable String advertiseIdentifier, HttpSession session){
		
		logger.info("Inside Controller Delete Advertisement");
		
		advertisementService.deleteByadvertiseIdentifier(advertiseIdentifier);
		return new ResponseEntity<String>("Advertisement deleted", HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8085/Farm-api/advertisement/updateAdvertisement     
	 * Update Advertisement
	 */
	@PatchMapping(path="/updateAdvertisement")
	public ResponseEntity<?> updateAdvertisement(@RequestBody Advertisement advertisement, BindingResult result,HttpSession session){
		
		logger.info("Inside Controller Update Advertisement"); // Simple Logging Facade for Java
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)
			return errorMap;
		
		Advertisement updateAdvertisement=advertisementService.updateAdvertisement(advertisement);
		return new ResponseEntity<Advertisement>(updateAdvertisement, HttpStatus.CREATED);
		
	}
}
