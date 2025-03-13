package com.freightfox.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "lane_id", nullable = false)
  private Lane lane;

  @ManyToOne
  @JoinColumn(name = "transporter_id", nullable = false)
  private Transporter transporter;
}
