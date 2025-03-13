package com.freightfox.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.freightfox.dto.AssignmentRequest;
import com.freightfox.dto.DataInputRequest;
import com.freightfox.service.AssignmentService;
import com.freightfox.service.TransporterService;

class TransporterControllerTest {

  @Mock private AssignmentService assignmentService;

  @Mock private TransporterService transporterService;

  @InjectMocks private TransporterController transporterController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSubmitInputData() {
    DataInputRequest request = new DataInputRequest();

    doNothing().when(transporterService).saveTransporterData(any(DataInputRequest.class));

    ResponseEntity<String> response = transporterController.submitInputData(request);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Input data saved successfully.", response.getBody());
    verify(transporterService, times(1)).saveTransporterData(any(DataInputRequest.class));
  }

  @Test
  void testGetOptimizedAssignments() {
    AssignmentRequest request = new AssignmentRequest(3);

    Map<String, Object> mockResponse =
        Map.of(
            "status",
            "success",
            "totalCost",
            75000,
            "assignments",
            Map.of("laneId", 1, "transporterId", 4));

    when(assignmentService.optimizeAssignments(3)).thenReturn(mockResponse);

    ResponseEntity<Map<String, Object>> response =
        transporterController.getOptimizedAssignments(request);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().get("status"));
    verify(assignmentService, times(1)).optimizeAssignments(3);
  }
}
