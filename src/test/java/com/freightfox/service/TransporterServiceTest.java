package com.freightfox.service;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freightfox.dto.DataInputRequest;
import com.freightfox.dto.DataInputRequest.LaneDTO;
import com.freightfox.dto.DataInputRequest.LaneQuoteDTO;
import com.freightfox.dto.DataInputRequest.TransporterDTO;
import com.freightfox.repository.LaneQuoteRepository;
import com.freightfox.repository.LaneRepository;
import com.freightfox.repository.TransporterRepository;

class TransporterServiceTest {

  @Mock private LaneRepository laneRepository;

  @Mock private TransporterRepository transporterRepository;

  @Mock private LaneQuoteRepository laneQuoteRepository;

  @InjectMocks private TransporterService transporterService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveTransporterData() {

    DataInputRequest mockRequest = new DataInputRequest();

    mockRequest.setLanes(
        List.of(new LaneDTO(1L, "Mumbai", "Delhi"), new LaneDTO(2L, "Delhi", "Bangalore")));

    mockRequest.setTransporters(
        List.of(
            new TransporterDTO(
                1L,
                "Transporter T1",
                List.of(new LaneQuoteDTO(1L, 20835.0), new LaneQuoteDTO(2L, 10512.0))),
            new TransporterDTO(
                2L,
                "Transporter T2",
                List.of(new LaneQuoteDTO(1L, 48844.0), new LaneQuoteDTO(2L, 31326.0)))));

    when(laneRepository.saveAll(anyList())).thenReturn(List.of());
    when(transporterRepository.saveAll(anyList())).thenReturn(List.of());
    when(laneQuoteRepository.saveAll(anyList())).thenReturn(List.of());

    transporterService.saveTransporterData(mockRequest);

    verify(laneRepository, times(1)).saveAll(anyList());
    verify(transporterRepository, times(1)).saveAll(anyList());
    verify(laneQuoteRepository, times(1)).saveAll(anyList());
  }
}
