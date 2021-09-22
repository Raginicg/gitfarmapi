package com.cloud.farmappapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloud.farmappapi.entity.Complaint;

/**
 * This test cases for testing repository of complaint
 * @author Ragini
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ComplaintRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	private Complaint viewAllComplaint() {
		Complaint complaint=new Complaint();
		complaint.setComplainDescription("Corn quality bad");
		complaint.setCreatedBy("Farmer");
		return complaint;
	}
	
	@Test
	void testAddComplaint() throws Exception{
		Complaint complaint=viewAllComplaint();
		Complaint saveInDb=testEntityManager.persist(complaint);
		System.out.println("saveInDb");
		System.out.println("Add Complaint from farmer");
		Complaint getFromDb=complaintRepository.findById(saveInDb.getComplaintId()).get();
		assertThat(getFromDb).isEqualTo(saveInDb);		
	}
	
	@Test
	void testGetCompalinById() {
		Complaint complaint=viewAllComplaint();
		Complaint saveInDb=testEntityManager.persist(complaint);
		Complaint count=complaintRepository.findById(complaint.getComplaintId()).get();
		assertThat(saveInDb).isEqualTo(count);
		
	}
	
	  @Test 
	  void testDeleteComplaint()throws Exception {
	  
			Complaint complaint = viewAllComplaint();
			Complaint deleteInDb = testEntityManager.persist(complaint);
			complaintRepository.deleteById(complaint.getComplaintId());
			List<Complaint> deleteCom = complaintRepository.findAll();
			
			assertThat(deleteCom).isEmpty();

		}
	 
}






