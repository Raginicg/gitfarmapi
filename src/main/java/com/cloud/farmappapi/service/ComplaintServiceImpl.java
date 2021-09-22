package com.cloud.farmappapi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.exception.ComplaintNotFoundException;
import com.cloud.farmappapi.exception.MyComplaintException;
import com.cloud.farmappapi.repository.ComplaintRepository;
import com.cloud.farmappapi.utilities.GlobalLogger;

/**
 * 
 * @author Ragini
 *
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {
	private Logger logger = GlobalLogger.getLogger(ComplaintServiceImpl.class);
	// Creating reference (it creates loosely coupled application)
	@Autowired
	private ComplaintRepository complaintRepository;
	
	/**
	 * Method for save or adding complaint with their details
	 */
	@Override
	@Transactional
	public Complaint addComplaint(Complaint complaint) {
		logger.info("Trying to add Complaint in service layer "+ complaint);
		return complaintRepository.save(complaint);
		}	
	
	/**
	 * Method for getcomplaint by using complaintId here you can find complaint details using their id.
	 */

	@Override
	public Optional<Complaint> getComplaintById(long complaintId) throws MyComplaintException{
		logger.info("Trying to fetch Complaint in service layer ");
		return complaintRepository.findById(complaintId);
	}
	
	/**
	 * Method for delete complaint by using complaintId
	 */
	@Override
	@Transactional
	public void deleteComplaintById(Long complaintId) throws MyComplaintException{
		logger.info("Trying to delete Compalint in service layer ");
		complaintRepository.deleteById(complaintId);
	}
	
	/**
	 * Viewing all the complaints
	 */
	@Override
	public List<Complaint> getAllComplaint() {
		logger.info("Trying to fetch all Complaint in service layer ");
		return complaintRepository.findAll();	
	}

	/**
	 * Updating or modifying complaint details
	 */
	@Override
	@Transactional
	public Complaint updateComplaint(Complaint complaint) throws MyComplaintException{
		logger.info(" Trying to update Complaint in service layer ");
		Long complaintId=complaint.getComplaintId();
		Optional<Complaint> complaintFound=getComplaintById(complaintId);
		Complaint updateComplaint=null;
		if(complaintFound.isPresent())
			updateComplaint=complaintRepository.save(complaint);
		return updateComplaint;
	}
}

