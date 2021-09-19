package com.cloud.farmappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.farmappapi.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
