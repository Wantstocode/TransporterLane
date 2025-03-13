package com.freightfox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freightfox.model.Lane;

@Repository
public interface LaneRepository extends JpaRepository<Lane, Long> {}
