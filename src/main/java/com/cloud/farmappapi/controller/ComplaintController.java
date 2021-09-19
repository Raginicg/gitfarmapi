package com.cloud.farmappapi.controller;

import java.util.List;

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
import com.cloud.farmappapi.repository.ComplaintRepository;
import com.cloud.farmappapi.service.ComplaintService;

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
	private ComplaintRepository complaintRepository;
	
	/**
	 * http://localhost:9090/Farm-api/complaint/viewAllComplaint     
	   View all the complaints
	 */
	@GetMapping(path="/viewAllComplaint")    
	public ResponseEntity<List<Complaint>> getAllComplaints(){
		List<Complaint> result=complaintService .getAllComplaint();
		if(result!=null)
			return new ResponseEntity<>(result, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * http://localhost:9090/Farm-api/complaint/addComplaint   
	 * Adding complaint
	 */
	@PostMapping(path="/addComplaint", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint){
		Complaint result=complaintService.addComplaint(complaint);
		if(result!=null)
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * http://localhost:9090/Farm-api/complaint/complaint/{complaintId}   
	 * detete mapping
	 */
	@DeleteMapping("/complaint/{complaintId}")
	public String deleteComplaint(@PathVariable("complaintId") long complaintId) throws ComplaintNotFoundException{
		complaintService.getComplaintById(complaintId).orElseThrow(
				() -> new ComplaintNotFoundException("Complaints not found with this complaint id " + complaintId));
		complaintService.deleteComplaintById(complaintId);
		return " Complaint deleted succesfully";
	}

	/**
	 * http://localhost/Farm-api/complaint/updateComplaint     
	 * Update Complaint
	 */
	@PutMapping(path="/updateComplaint", consumes=MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
		public ResponseEntity<Complaint> updateComplaint(@RequestBody Complaint complaint){
		Complaint result=complaintService.addComplaint(complaint);
		if(result!=null)
			return new ResponseEntity<> (result, HttpStatus.CREATED);
		else
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 *  http://localhost:9090/Farm-api/complaint/complaint/{complaintId}   
	 *  View complaint by id
	 */
		@GetMapping("/complaint/{complaintId}")
		public ResponseEntity<Complaint> getComplaintById(@PathVariable int complaintId) throws ComplaintNotFoundException {
			Complaint complaint = complaintService.getComplaintById(complaintId).orElseThrow(
					() -> new ComplaintNotFoundException("Complaints not found with this complaint id " + complaintId));
			return ResponseEntity.ok().body(complaint);
		}
}
