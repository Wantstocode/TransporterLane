package com.freightfox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freightfox.model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {}
