/**
 * 
 */
package com.cloud.farmappapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kmahendr
 *
 */
@Entity
@Table(name="complaints")
public class Complaint {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long complaintId;
	/**
	 * Description given as a Complaint it cannot be blank
	 */
	@Column(name="descriptions")
	private String complainDescription;
	
	/**
	 * 
	 * createdBy field will contain name of farmer retrieved through session
	 */
	
	@Column(name="creators")
	private String createdBy;

	
	
	
	
	/**
	 * 
	 */
	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}





	/**
	 * @param complaintId
	 * @param complainDescription
	 * @param createdBy
	 */
	public Complaint(Long complaintId, String complainDescription, String createdBy) {
		super();
		this.complaintId = complaintId;
		this.complainDescription = complainDescription;
		this.createdBy = createdBy;
	}





	/**
	 * @return the complaintId
	 */
	public Long getComplaintId() {
		return complaintId;
	}





	/**
	 * @param complaintId the complaintId to set
	 */
	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}





	/**
	 * @return the complainDescription
	 */
	public String getComplainDescription() {
		return complainDescription;
	}





	/**
	 * @param complainDescription the complainDescription to set
	 */
	public void setComplainDescription(String complainDescription) {
		this.complainDescription = complainDescription;
	}





	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}





	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
	

}
