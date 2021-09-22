package com.cloud.farmappapi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.exception.ComplaintAlreadyExistException;
import com.cloud.farmappapi.exception.ComplaintNotFoundException;
import com.cloud.farmappapi.repository.ComplaintRepository;
import com.cloud.farmappapi.service.ComplaintService;
import com.cloud.farmappapi.service.MapValidationErrorService;
import com.cloud.farmappapi.utilities.GlobalLogger;

/**
 * 
 * @author Ragini
 *
 */
@RestController
@RequestMapping(path= "complaint")
public class ComplaintController {
	// Creating reference (it creates loosely coupled application)
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	/*
	 * Logger used o track the log.
	 */
	private static final Logger logger = GlobalLogger.getLogger(ComplaintController.class);
	
	/**
	 * http://localhost:9090/Farm-api/complaint/viewAllComplaint     
	   View all the complaints
	 */
	@GetMapping(path="/viewAllComplaint")    
	public ResponseEntity<?> getAllComplaints(){
		logger.info("Inside Controller View all Complaint");
		List<Complaint> complaint=complaintService .getAllComplaint();
		return new ResponseEntity<List<Complaint>>(complaint,HttpStatus.OK);
	}
	
	/**
	 * http://localhost:9090/Farm-api/complaint/addComplaint   
	 * Adding complaint
	 */
	@PostMapping(path="/addComplaint")
	public ResponseEntity<?> addComplaint(@RequestBody Complaint complaint,BindingResult result){
		logger.info("Inside Controller Add Complaint");
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)
			return errorMap;
		try {
			Complaint addComplaint=complaintService.addComplaint(complaint);
			return new ResponseEntity<Complaint>(addComplaint, HttpStatus.CREATED);
		}
		catch(Exception e) {
			throw new ComplaintAlreadyExistException("Complaint Already Exist! Please Add new Complaint ");
		}
	}
	
	/**
	 * http://localhost:9090/Farm-api/complaint/{complaintId}   
	 * detete mapping
	 */
	@DeleteMapping("/{complaintId}")
	public ResponseEntity<?> deleteComplaint(@PathVariable Long complaintId){
		logger.info("Inside Controller Delete Complaint");
		complaintService.deleteComplaintById(complaintId);
		return new ResponseEntity<String>("Complaint Id  " + complaintId + " is deleted", HttpStatus.OK);
	}

	
	/**
	 * http://localhost/Farm-api/complaint/updateComplaint     
	 * Update Complaint
	 */
	@PutMapping("/updateComplaint")
	public ResponseEntity<?> updateComplaint(@RequestBody Complaint complaint, BindingResult result){
		logger.info("Inside Controller Update Complaint");
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)
			return errorMap;
		Complaint updateComplaint=complaintService.updateComplaint(complaint);
		return new ResponseEntity<Complaint>(updateComplaint, HttpStatus.CREATED);
	}
		
	
	/**
	 *  http://localhost:9090/Farm-api/complaint/{complaintId}   
	 *  View complaint by id
	 */
		@GetMapping("/{complaintId}")
		public ResponseEntity<?> getComplaintById(@PathVariable int complaintId){
			logger.info("Inside Controller View Complaint ById");
			Complaint foundcomplaint = complaintService.getComplaintById(complaintId);
			return new ResponseEntity<Complaint>(foundcomplaint, HttpStatus.OK);
		}
}
