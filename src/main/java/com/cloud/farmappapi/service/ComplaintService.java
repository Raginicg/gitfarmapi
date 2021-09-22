package com.cloud.farmappapi.service;

import java.util.List;
import java.util.Optional;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.exception.ComplaintNotFoundException;
import com.cloud.farmappapi.exception.MyComplaintException;

/**
 * 
 * @author Ragini
 *
 */
public interface ComplaintService {
	
	public Complaint addComplaint(Complaint complaint) throws MyComplaintException;
	
	public Optional<Complaint> getComplaintById(long complaintId) throws MyComplaintException;
	
	public void deleteComplaintById(Long complaintId) throws MyComplaintException;
	
	public Complaint updateComplaint(Complaint complaint) throws MyComplaintException;
	
	public List<Complaint> getAllComplaint();

}
