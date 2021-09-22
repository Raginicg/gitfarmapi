/**
 * 
 */
package com.cloud.farmappapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.farmappapi.entity.Advertisement;
import com.cloud.farmappapi.exception.AdvertisementAlreadyExistException;
import com.cloud.farmappapi.exception.AdvertisementNotFoundException;
import com.cloud.farmappapi.repository.AdvertisementRepository;
import com.cloud.farmappapi.utilities.GlobalLogger;

/**
 * @author Sanjeet
 *
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	
	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	private Logger logger = GlobalLogger.getLogger(AdvertisementServiceImpl.class);
	
	@Override
	public Advertisement saveAdvertisement(Advertisement Advertisement) {
		logger.info("Inside advertisement service adding advertisement");
		if (Advertisement.getTitle() == null) {
			throw new NullPointerException("Please Enter All Values in the field");
		}
		
		if ((advertisementRepository.findBytitle(Advertisement.getTitle())) != null) {
			
			throw new AdvertisementAlreadyExistException("Advertisement already exists");
		} 
		return advertisementRepository.save(Advertisement);
		
	}
	
	@Override
	public Advertisement findBytitle(String title) {
		logger.info("Inside Advertisement service getAdvertisementBytitle");
		if (title == null) {
			
			throw new NullPointerException("Please Provide Title");
		}
		
		if ((advertisementRepository.findBytitle(title)) == null) {
			
			throw new AdvertisementNotFoundException("User with title : " + title + " does not exists");
		}
		return advertisementRepository.findBytitle(title);
		
	}

	@Override
	public Advertisement findByadvertiseIdentifier(String advertiseIdentifier) {
		logger.info("Inside Advertisement service find by Ad Id");
		if (advertiseIdentifier == null) {
			
			throw new NullPointerException("Please Provide Advertise Id");
		}
		
		if ((advertisementRepository.findByadvertiseIdentifier(advertiseIdentifier)) == null) {
			
			throw new AdvertisementNotFoundException("User with Advertise Id : " + advertiseIdentifier + " does not exists");
		}
		return advertisementRepository.findByadvertiseIdentifier(advertiseIdentifier);
		
	}

	@Override
	public Advertisement findByPostedBy(String PostedBy) {
		logger.info("Inside Advertisement service find by Posted Id");
		@SuppressWarnings("unused")
		Advertisement advertisement=null;
		if (PostedBy == null) {
			
			throw new NullPointerException("Please Provide Post");
		}
		
		if ((advertisement = advertisementRepository.findByPostedBy(PostedBy)) == null) {
			
			throw new AdvertisementNotFoundException("Advertisement with PostedBy : " + PostedBy + " does not exists");
		}
		return advertisementRepository.findByPostedBy(PostedBy);	
	}
	/**
	 * Updating or modifying advertisement details
	 */
	@Override
	public Advertisement updateAdvertisement(Advertisement advertisement) {
Advertisement oldAdvertisement =null;
		
		if (advertisement.getTitle() == null) {
			//log.error("-----------updateAdvertisement--------- : Please Fill the Required Fields");
			throw new NullPointerException("Please Fill the Required Fields");
		}
		
		if ((oldAdvertisement=advertisementRepository.findBytitle(advertisement.getTitle())) == null) {
			//log.error("-----------updateAdvertisement--------- : Product Owner with loginName : " + advertisement.getLoginName() + " does not exists");
			throw new AdvertisementNotFoundException("Advertisement with title: " + advertisement.getTitle() + " does not exists");
		}
		
		//log.info("-----------updateAdvertisement--------- : Product Owner Update Successful");
		advertisement.setAdvertiseId(oldAdvertisement.getAdvertiseId());
		oldAdvertisement = advertisement;
		return advertisementRepository.save(oldAdvertisement);
	}

	@Override
	public List<Advertisement> findAll() {
		try {
			
			return advertisementRepository.findAll();
		} catch (Exception e) {
			
			throw new AdvertisementNotFoundException("No Advertisement Found");
		}
	}
	/**
	 * Deleting advertisement
	 */
	@Override
	public void deleteByadvertiseIdentifier(String advertiseIdentifier) {	
Advertisement advertisement = null;
		
		if (advertiseIdentifier == null) {
			// log.error("-----------deleteAdvertisementByLoginName--------- : Product Owner with loginName : " + loginName + " does not exists");
			throw new NullPointerException("Please Provide Advertise Identifier");
		}
		
		if ((advertisement = advertisementRepository.findByadvertiseIdentifier(advertiseIdentifier)) == null) {
			// log.error("-----------deleteAdvertisementBytitle--------- : Please Provide Login Name");
			throw new AdvertisementNotFoundException("Advertisement with task identifier: " + advertiseIdentifier + " does not exists");
		}
		// log.info("-----------deleteAdvertisementByLoginName--------- : Product Owner Deleted Successfully");
		advertisementRepository.deleteByadvertiseIdentifier(advertiseIdentifier);
	}
}
