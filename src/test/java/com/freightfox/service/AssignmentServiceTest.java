package com.freightfox.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freightfox.model.Lane;
import com.freightfox.model.LaneQuote;
import com.freightfox.model.Transporter;
import com.freightfox.repository.AssignmentRepository;
import com.freightfox.repository.LaneQuoteRepository;
import com.freightfox.repository.LaneRepository;

class AssignmentServiceTest {

  @Mock private LaneRepository laneRepository;

  @Mock private LaneQuoteRepository laneQuoteRepository;

  @Mock private AssignmentRepository assignmentRepository;

  @InjectMocks private AssignmentService assignmentService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testOptimizeAssignments() {

    List<Lane> lanes = List.of(new Lane(1L, "Mumbai", "Delhi"), new Lane(2L, "Delhi", "Bangalore"));

    List<LaneQuote> quotes =
        List.of(
            new LaneQuote(1L, lanes.get(0), new Transporter(1L, "Transporter T1"), 5000.0),
            new LaneQuote(2L, lanes.get(1), new Transporter(2L, "Transporter T2"), 6000.0));

    when(laneRepository.findAll()).thenReturn(lanes);
    when(laneQuoteRepository.findAll()).thenReturn(quotes);

    Map<String, Object> response = assignmentService.optimizeAssignments(3);

    assertEquals("success", response.get("status"));
    assertTrue(((List<?>) response.get("assignments")).size() > 0);
  }

  @Test
  void testOptimizeAssignments_NoQuotes() {
    when(laneRepository.findAll()).thenReturn(Collections.emptyList());
    when(laneQuoteRepository.findAll()).thenReturn(Collections.emptyList());

    Map<String, Object> response = assignmentService.optimizeAssignments(3);

    assertEquals("success", response.get("status"));
    assertTrue(((List<?>) response.get("assignments")).isEmpty());
  }
}
