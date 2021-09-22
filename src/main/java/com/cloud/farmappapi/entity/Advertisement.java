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
 * @author Sanjeet
 *
 */
@Entity
@Table(name="advertisements")
public class Advertisement {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long advertiseId;
	
	@Column(name="ad_titles",unique=true,updatable=false)
	private String title;
	
	@Column(name="ad_identifiers",unique = true, updatable = false)
	private String advertiseIdentifier;
	
	@Column(name="offers")
	private String offerDescription;
	
	@Column(name="stock")
	private String availableStock;
	
	
	/**
	 * postedBy field will contain name of Dealer retrieved through session
	 */
	@Column(name="postedby")
	private String postedBy;
	/**
	 * @param advertiseId
	 * @param title
	 * @param advertiseIdentifier
	 * @param offerDescription
	 * @param availableStock
	 */
	
	/**
	 * Default Constructor for Advertisement class
	 */
	public Advertisement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the advertiseId
	 */
	public Long getAdvertiseId() {
		return advertiseId;
	}

	/**
	 * @param advertiseId the advertiseId to set
	 */
	public void setAdvertiseId(Long advertiseId) {
		this.advertiseId = advertiseId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the advertiseIdentifier
	 */
	public String getAdvertiseIdentifier() {
		return advertiseIdentifier;
	}

	/**
	 * @param advertiseIdentifier the advertiseIdentifier to set
	 */
	public void setAdvertiseIdentifier(String advertiseIdentifier) {
		this.advertiseIdentifier = advertiseIdentifier;
	}

	/**
	 * @return the offerDescription
	 */
	public String getOfferDescription() {
		return offerDescription;
	}

	/**
	 * @param offerDescription the offerDescription to set
	 */
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	/**
	 * @return the availableStock
	 */
	public String getAvailableStock() {
		return availableStock;
	}

	/**
	 * @param availableStock the availableStock to set
	 */
	public void setAvailableStock(String availableStock) {
		this.availableStock = availableStock;
	}

	/**
	 * @return the postedBy
	 */
	public String getPostedBy() {
		return postedBy;
	}

	/**
	 * @param postedBy the postedBy to set
	 */
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
}
