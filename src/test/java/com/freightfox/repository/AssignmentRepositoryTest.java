package com.freightfox.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freightfox.model.Assignment;
import com.freightfox.model.Lane;
import com.freightfox.model.Transporter;

class AssignmentRepositoryTest {

  @Mock private LaneRepository laneRepository;

  @Mock private TransporterRepository transporterRepository;

  @Mock private AssignmentRepository assignmentRepository;

  @InjectMocks private AssignmentRepositoryTest mockRepositoryTest;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveAndFindAssignment() {

    Lane lane = new Lane(1L, "Mumbai", "Delhi");
    Transporter transporter = new Transporter(1L, "Transporter A");
    Assignment assignment = new Assignment(1L, lane, transporter);

    when(laneRepository.save(any(Lane.class))).thenReturn(lane);
    when(transporterRepository.save(any(Transporter.class))).thenReturn(transporter);
    when(assignmentRepository.save(any(Assignment.class))).thenReturn(assignment);

    Lane savedLane = laneRepository.save(lane);
    Transporter savedTransporter = transporterRepository.save(transporter);
    Assignment savedAssignment = assignmentRepository.save(assignment);

    assertThat(savedAssignment.getId()).isNotNull();

    when(assignmentRepository.findAll()).thenReturn(List.of(assignment));

    List<Assignment> assignments = assignmentRepository.findAll();

    assertThat(assignments).hasSize(1);
    assertThat(assignments.get(0).getLane().getOrigin()).isEqualTo("Mumbai");
    assertThat(assignments.get(0).getTransporter().getName()).isEqualTo("Transporter A");

    verify(laneRepository, times(1)).save(any(Lane.class));
    verify(transporterRepository, times(1)).save(any(Transporter.class));
    verify(assignmentRepository, times(1)).save(any(Assignment.class));
    verify(assignmentRepository, times(1)).findAll();
  }
}
