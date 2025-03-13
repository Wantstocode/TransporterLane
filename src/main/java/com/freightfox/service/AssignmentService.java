package com.freightfox.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.freightfox.dto.AssignmentResponse;
import com.freightfox.model.Assignment;
import com.freightfox.model.Lane;
import com.freightfox.model.LaneQuote;
import com.freightfox.repository.AssignmentRepository;
import com.freightfox.repository.LaneQuoteRepository;
import com.freightfox.repository.LaneRepository;

@Service
public class AssignmentService {

  private final LaneRepository laneRepository;
  private final LaneQuoteRepository laneQuoteRepository;
  private final AssignmentRepository assignmentRepository;

  public AssignmentService(
      LaneRepository laneRepository,
      LaneQuoteRepository laneQuoteRepository,
      AssignmentRepository assignmentRepository) {
    this.laneRepository = laneRepository;
    this.laneQuoteRepository = laneQuoteRepository;
    this.assignmentRepository = assignmentRepository;
  }

  // Optimizes transporter assignments to minimize cost while ensuring full lane coverage.
  public Map<String, Object> optimizeAssignments(int maxTransporters) {
    List<Lane> allLanes = laneRepository.findAll();
    List<LaneQuote> allQuotes = laneQuoteRepository.findAll();

    // Group quotes by lane
    Map<Long, List<LaneQuote>> laneToQuotes = new HashMap<>();
    for (LaneQuote quote : allQuotes) {
      laneToQuotes.computeIfAbsent(quote.getLane().getId(), k -> new ArrayList<>()).add(quote);
    }

    Set<Long> selectedTransporters = new HashSet<>();
    List<Assignment> assignments = new ArrayList<>();
    double totalCost = 0;

    for (Lane lane : allLanes) {
      List<LaneQuote> quotes = laneToQuotes.getOrDefault(lane.getId(), new ArrayList<>());
      quotes.sort(Comparator.comparingDouble(LaneQuote::getQuote)); // Sort quotes by cost

      for (LaneQuote quote : quotes) {
        if (selectedTransporters.size() < maxTransporters
            || selectedTransporters.contains(quote.getTransporter().getId())) {

          Assignment assignment = new Assignment();
          assignment.setLane(lane);
          assignment.setTransporter(quote.getTransporter());
          assignments.add(assignment);

          selectedTransporters.add(quote.getTransporter().getId());
          totalCost += quote.getQuote();
          break;
        }
      }
    }

    assignmentRepository.saveAll(assignments);

    List<AssignmentResponse> assignmentResponses = new ArrayList<>();
    for (Assignment assignment : assignments) {
      assignmentResponses.add(
          new AssignmentResponse(
              assignment.getId(), assignment.getLane(), assignment.getTransporter()));
    }

    return Map.of(
        "status", "success",
        "totalCost", totalCost,
        "assignments", assignmentResponses,
        "selectedTransporters", selectedTransporters);
  }
}
