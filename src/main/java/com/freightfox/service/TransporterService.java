package com.freightfox.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freightfox.dto.DataInputRequest;
import com.freightfox.model.Lane;
import com.freightfox.model.LaneQuote;
import com.freightfox.model.Transporter;
import com.freightfox.repository.LaneQuoteRepository;
import com.freightfox.repository.LaneRepository;
import com.freightfox.repository.TransporterRepository;

@Service
public class TransporterService {

  private final LaneRepository laneRepository;
  private final TransporterRepository transporterRepository;
  private final LaneQuoteRepository laneQuoteRepository;

  public TransporterService(
      LaneRepository laneRepository,
      TransporterRepository transporterRepository,
      LaneQuoteRepository laneQuoteRepository) {
    this.laneRepository = laneRepository;
    this.transporterRepository = transporterRepository;
    this.laneQuoteRepository = laneQuoteRepository;
  }

  @Transactional
  public void saveTransporterData(DataInputRequest request) {
    // Save lanes
    List<Lane> lanes =
        request.getLanes().stream()
            .map(dto -> new Lane(dto.getId(), dto.getOrigin(), dto.getDestination()))
            .collect(Collectors.toList());
    laneRepository.saveAll(lanes);

    // Save transporters
    List<Transporter> transporters =
        request.getTransporters().stream()
            .map(dto -> new Transporter(dto.getId(), dto.getName()))
            .collect(Collectors.toList());
    transporterRepository.saveAll(transporters);

    // Save lane quotes
    List<LaneQuote> laneQuotes =
        request.getTransporters().stream()
            .flatMap(
                transporterDTO ->
                    transporterDTO.getLaneQuotes().stream()
                        .map(
                            quoteDTO -> {
                              Optional<Lane> lane = laneRepository.findById(quoteDTO.getLaneId());
                              Optional<Transporter> transporter =
                                  transporterRepository.findById(transporterDTO.getId());

                              if (lane.isPresent() && transporter.isPresent()) {
                                return new LaneQuote(
                                    null, lane.get(), transporter.get(), quoteDTO.getQuote());
                              }
                              return null;
                            }))
            .filter(Objects::nonNull) // Remove null values
            .collect(Collectors.toList());

    laneQuoteRepository.saveAll(laneQuotes);
  }
}
