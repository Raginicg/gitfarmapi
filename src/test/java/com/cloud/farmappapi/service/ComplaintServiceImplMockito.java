package com.cloud.farmappapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.exception.ComplaintNotFoundException;
import com.cloud.farmappapi.repository.ComplaintRepository;

/**
 * 
 * This test case used for testing the Compalint serviceImpl
 * @author Ragini
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplaintServiceImplMockito {
	
	@Autowired
	private ComplaintServiceImpl complaintServiceImpl;
	
	@MockBean
	private ComplaintRepository complaintRepository;
	
	private Complaint viewAllComplaint() {
		Complaint complaint=new Complaint();
		complaint.setComplaintId(100L);
		complaint.setComplainDescription("Corn quality bad");
		complaint.setCreatedBy("Farmer");
		return complaint;
	}
	
	@Test
	void testAddComplaint() {
		Complaint complaint=viewAllComplaint();
		Mockito.when(complaintRepository.save(complaint)).thenReturn(complaint);
	}
	
	@Test
	void testGetComplaintById() throws ComplaintNotFoundException {
		Complaint complaint=new Complaint();
		complaint.setComplaintId(100L);
		complaint.setComplainDescription("Crops have problem");
		complaint.setCreatedBy("Farmer");
		
		Optional<Complaint> expected=Optional.of(complaint);
		Mockito.when(complaintRepository.findById(100L)).thenReturn(expected);
		Complaint result=complaintServiceImpl.getComplaintById(complaint.getComplaintId());
		assertEquals(complaint, result);
	}
	
//	@Test
//	void testCompalintDeleteById() throws ComplaintNotFoundException {
//		Complaint complaint=viewAllComplaint();
//		complaintServiceImpl.deleteComplaintById(complaint.getComplaintId());
//		Optional<Complaint> deleteComplaint=complaintServiceImpl.getComplaintById(complaint.getComplaintId());
//		Assert.assertEquals(true,deleteComplaint.isEmpty() ); 
//	//	Optional<Complaint> deleteComplaint=Optional.of(complaintServiceImpl.getComplaintById(complaint.getComplaintId()));
//		
//	}
	
	
}
