package com.freightfox.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freightfox.model.Transporter;

class TransporterRepositoryTest {

  @Mock private TransporterRepository transporterRepository;

  @InjectMocks private TransporterRepositoryTest mockRepositoryTest;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindByName() {

    Transporter transporter = new Transporter(1L, "Transporter Y");
    when(transporterRepository.findByName("Transporter Y")).thenReturn(Optional.of(transporter));

    Optional<Transporter> foundTransporter = transporterRepository.findByName("Transporter Y");

    assertThat(foundTransporter).isPresent();
    assertThat(foundTransporter.get().getName()).isEqualTo("Transporter Y");

    verify(transporterRepository, times(1)).findByName("Transporter Y");
  }
}
