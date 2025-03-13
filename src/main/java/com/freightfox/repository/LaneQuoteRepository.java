package com.freightfox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freightfox.model.LaneQuote;

@Repository
public interface LaneQuoteRepository extends JpaRepository<LaneQuote, Long> {

  // Find all quotes for a specific lane
  List<LaneQuote> findByLaneId(Long laneId);

  // Find all quotes for a specific transporter
  List<LaneQuote> findByTransporterId(Long transporterId);
}
