package com.cloud.farmappapi.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.service.ComplaintServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ComplaintController.class)
public class CompalintControllerTest {
	
	@MockBean
	 private ComplaintServiceImpl complaintServiceImpl;
	
	private Complaint viewAllComplaint() {
		Complaint complaint=new Complaint();
		complaint.setComplaintId(100L);
		complaint.setComplainDescription("Payment not done");
		complaint.setCreatedBy("Farmer");
		return complaint;
	}

}
