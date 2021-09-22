package com.cloud.farmappapi.service;

import java.util.List;
import java.util.Optional;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.exception.ComplaintNotFoundException;

/**
 * 
 * @author Ragini
 *
 */
public interface ComplaintService {
	
	public Complaint addComplaint(Complaint complaint);
	
	public Complaint getComplaintById(long complaintId) throws ComplaintNotFoundException;
	
	public void deleteComplaintById(long complaintId) throws ComplaintNotFoundException;
	
	public Complaint updateComplaint(Complaint complaint) throws ComplaintNotFoundException;
	
	public List<Complaint> getAllComplaint();

}
