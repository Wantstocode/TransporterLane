package com.freightfox.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freightfox.model.Lane;

class LaneRepositoryTest {

  @Mock private LaneRepository laneRepository;

  @InjectMocks private LaneRepositoryTest mockRepositoryTest;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveAndFindLane() {

    Lane lane = new Lane(1L, "Mumbai", "Delhi");

    when(laneRepository.save(any(Lane.class))).thenReturn(lane);

    Lane savedLane = laneRepository.save(lane);

    assertThat(savedLane.getId()).isNotNull();
    assertThat(savedLane.getOrigin()).isEqualTo("Mumbai");
    assertThat(savedLane.getDestination()).isEqualTo("Delhi");

    when(laneRepository.findById(1L)).thenReturn(Optional.of(lane));

    Lane fetchedLane = laneRepository.findById(1L).orElse(null);

    assertThat(fetchedLane).isNotNull();
    assertThat(fetchedLane.getOrigin()).isEqualTo("Mumbai");
    assertThat(fetchedLane.getDestination()).isEqualTo("Delhi");

    when(laneRepository.findAll()).thenReturn(List.of(lane));

    List<Lane> allLanes = laneRepository.findAll();

    assertThat(allLanes).hasSize(1);

    verify(laneRepository, times(1)).save(any(Lane.class));
    verify(laneRepository, times(1)).findById(1L);
    verify(laneRepository, times(1)).findAll();
  }
}
