package com.freightfox.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.freightfox.dto.AssignmentRequest;
import com.freightfox.dto.DataInputRequest;
import com.freightfox.service.AssignmentService;
import com.freightfox.service.TransporterService;

@RestController
@RequestMapping("/api/v1/transporters")
public class TransporterController {

  private final AssignmentService assignmentService;

  private final TransporterService transporterService;

  public TransporterController(
      AssignmentService assignmentService, TransporterService transporterService) {
    this.assignmentService = assignmentService;
    this.transporterService = transporterService;
  }

  @PostMapping("/input")
  public ResponseEntity<String> submitInputData(@RequestBody DataInputRequest request) {
    transporterService.saveTransporterData(request);
    return ResponseEntity.ok("Input data saved successfully.");
  }

  @PostMapping("/assignment")
  public ResponseEntity<Map<String, Object>> getOptimizedAssignments(
      @RequestBody AssignmentRequest request) {
    int maxTransporters = request.getMaxTransporters();
    Map<String, Object> response = assignmentService.optimizeAssignments(maxTransporters);
    return ResponseEntity.ok(response);
  }
}
