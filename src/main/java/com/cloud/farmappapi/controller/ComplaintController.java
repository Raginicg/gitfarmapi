package com.cloud.farmappapi.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.exception.ComplaintNotFoundException;
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
	/*
	 * Logger used o track the log.
	 */
	private static final Logger logger = GlobalLogger.getLogger(ComplaintController.class);
	
	// Creating reference (it creates loosely coupled application)
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
		/**
	 * http://localhost:9090/Farm-api/complaint/viewAllComplaint     
	   View all the complaints
	 */
	@GetMapping(path="/viewAllComplaint", produces = "application/json")    
	public ResponseEntity<List<Complaint>> getAllComplaints() throws ComplaintNotFoundException{
		logger.info("Trying to fetch Complaint list");
		try {
			List<Complaint> complaints=complaintService.getAllComplaint();
			
			if(complaints.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<>(complaints,HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("Record NOT found : ");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	
	/**
	 * http://localhost:9090/Farm-api/complaint/addComplaint   
	 * Adding complaint
	 */
	@PostMapping(path="/addComplaint")
	public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) throws ComplaintNotFoundException{
		try {
		logger.info("Trying to add Record  : " + complaint);
		Complaint addedComplaint=complaintService.addComplaint(complaint);
		return new ResponseEntity<>(complaint,HttpStatus.CREATED);
	}catch(Exception e) {
		logger.error("Record NOT Added  : " + complaint);
		return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	}
}
	
	/**
	 * http://localhost:9090/Farm-api/complaint/complaint/{complaintId}   
	 * detete mapping
	 */
	@DeleteMapping("/complaint/{complaintId}")
	public ResponseEntity<String> deleteComplaint(@PathVariable Long complaintId) throws ComplaintNotFoundException{
		try{
			complaintService.deleteComplaintById(complaintId);
			Optional<Complaint> deleComplaint=complaintService.getComplaintById(complaintId);
			logger.info("Record Deleted with Id : " + complaintId);
			return new ResponseEntity<>("Record Deleted...with id : "+complaintId,HttpStatus.OK);
		}
		catch (Exception e) {
			logger.error("Record NOT Deleted with Id : " + complaintId);
			return new ResponseEntity<>("Record not found with id : "+complaintId,HttpStatus.EXPECTATION_FAILED);
		}
		
	}

	
	/**
	 * http://localhost/Farm-api/complaint/updateComplaint     
	 * Update Complaint
	 */
	@PutMapping(path="/updateComplaint")
	public ResponseEntity<?> updateComplaint(@RequestBody Complaint complaint, @PathVariable Long complaintId) throws ComplaintNotFoundException {
			logger.info("trying to update complaint: " + complaint);
			try {
				Optional<Complaint> complaintFound=complaintService.getComplaintById(complaintId);
				
				if(complaintFound.isPresent()) {
					complaintService.updateComplaint(complaint);
					System.out.println("Record Updated: " + complaint);
					return ResponseEntity.ok(complaint);
				}
				else {
					return new ResponseEntity<String>("Record Not updated with Id: "+ complaint,HttpStatus.NO_CONTENT);
				}
			}
			catch (Exception e) {
				logger.error("Record NOT updated with Id : " + complaint);
				return new ResponseEntity<String>("Record NOT updated with Id : " + complaint, HttpStatus.EXPECTATION_FAILED);
			}
		}
	/**
	 *  http://localhost:9090/Farm-api/complaint/complaint/{complaintId}   
	 *  View complaint by id
	 */
		@GetMapping("/complaint/{complaintId}")
		public ResponseEntity<Complaint> getComplaintById(@PathVariable Long complaintId) throws ComplaintNotFoundException {
			Optional<Complaint> complaint=null;
			logger.info("Trying to serach with Id: "+ complaintId );
			try {
				complaint=complaintService.getComplaintById(complaintId);
				if(complaint.isPresent()) {
					return new ResponseEntity<>(complaint.get(),HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}	
			}catch (Exception e) {
				logger.error("Record NOT Found with Id : " + complaintId);
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
			}
			
		}
}
