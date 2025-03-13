package com.freightfox.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freightfox.model.Lane;
import com.freightfox.model.LaneQuote;
import com.freightfox.model.Transporter;

class LaneQuoteRepositoryTest {

  @Mock private LaneRepository laneRepository;

  @Mock private TransporterRepository transporterRepository;

  @Mock private LaneQuoteRepository laneQuoteRepository;

  @InjectMocks private LaneQuoteRepositoryTest mockRepositoryTest;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveAndFindLaneQuote() {

    Lane lane = new Lane(1L, "Mumbai", "Delhi");
    Transporter transporter = new Transporter(1L, "Transporter A");
    LaneQuote laneQuote = new LaneQuote(1L, lane, transporter, 5000.0);

    when(laneRepository.save(any(Lane.class))).thenReturn(lane);
    when(transporterRepository.save(any(Transporter.class))).thenReturn(transporter);
    when(laneQuoteRepository.save(any(LaneQuote.class))).thenReturn(laneQuote);

    Lane savedLane = laneRepository.save(lane);
    Transporter savedTransporter = transporterRepository.save(transporter);
    LaneQuote savedQuote = laneQuoteRepository.save(laneQuote);

    assertThat(savedQuote.getId()).isNotNull();
    assertThat(savedQuote.getQuote()).isEqualTo(5000.0);

    when(laneQuoteRepository.findAll()).thenReturn(List.of(laneQuote));

    List<LaneQuote> quotes = laneQuoteRepository.findAll();

    assertThat(quotes).hasSize(1);

    verify(laneRepository, times(1)).save(any(Lane.class));
    verify(transporterRepository, times(1)).save(any(Transporter.class));
    verify(laneQuoteRepository, times(1)).save(any(LaneQuote.class));
    verify(laneQuoteRepository, times(1)).findAll();
  }
}
