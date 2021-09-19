package com.cloud.farmappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.exception.ComplaintNotFoundException;
import com.cloud.farmappapi.repository.ComplaintRepository;

/**
 * 
 * @author Ragini
 *
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {
	// Creating reference (it creates loosely coupled application)
	@Autowired
	private ComplaintRepository complaintRepository;
	
	/**
	 * Method for save or adding complaint with their details
	 */
	@Override
	public Complaint addComplaint(Complaint complaint) {
		return complaintRepository.save(complaint);
	}
	
	/**
	 * Method for getcomplaint by using complaintId here you can find complaint details using their id.
	 */

	@Override
	public Optional<Complaint> getComplaintById(long complaintId) throws ComplaintNotFoundException {
		return complaintRepository.findById(complaintId);
	}

	/**
	 * Method for delete complaint by using complaintId
	 */
	@Override
	public void deleteComplaintById(long complaintId) throws ComplaintNotFoundException{
		complaintRepository.deleteById(complaintId);
	}
	
	/**
	 * Viewing all the complaints
	 */
	@Override
	public List<Complaint> getAllComplaint() {
		return complaintRepository.findAll();
	}

	/**
	 * Updating or modifying complaint details
	 */
	@Override
	public Complaint updateComplaint(Complaint complaint) throws ComplaintNotFoundException {
		return complaintRepository.save(complaint);
	}

}
