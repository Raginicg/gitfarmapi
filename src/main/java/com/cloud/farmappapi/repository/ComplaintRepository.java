package com.cloud.farmappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloud.farmappapi.entity.Complaint;
import com.cloud.farmappapi.exception.ComplaintNotFoundException;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	
}
