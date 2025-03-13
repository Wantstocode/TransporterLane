package com.freightfox.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataInputRequest {

  private List<LaneDTO> lanes;
  private List<TransporterDTO> transporters;

  @Getter
  @Setter
  public static class LaneDTO {
    private Long id;
    private String origin;
    private String destination;

    public LaneDTO(Long id, String origin, String destination) {
      this.id = id;
      this.origin = origin;
      this.destination = destination;
    }
  }

  @Getter
  @Setter
  public static class TransporterDTO {
    private Long id;
    private String name;
    private List<LaneQuoteDTO> laneQuotes;

    public TransporterDTO(Long id, String name, List<LaneQuoteDTO> laneQuotes) {
      this.id = id;
      this.name = name;
      this.laneQuotes = laneQuotes;
    }
  }

  @Getter
  @Setter
  public static class LaneQuoteDTO {
    private Long laneId;
    private Double quote;

    public LaneQuoteDTO(Long laneId, Double quote) {
      this.laneId = laneId;
      this.quote = quote;
    }
  }
}
