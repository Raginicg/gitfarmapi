/**
 * 
 */
package com.cloud.farmappapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ragini
 *
 */
@Entity
@Table(name="complaint")
public class Complaint implements Serializable {
	private static final long serialVersionUID = 6325307718376939175L;
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
	 * Default constructor
	 */
	public Complaint() {
		super();
	}

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

	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", complainDescription=" + complainDescription + ", createdBy="
				+ createdBy + "]";
	}
	
}

