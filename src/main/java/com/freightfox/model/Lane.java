package com.freightfox.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lanes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lane {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String origin;

  @Column(nullable = false)
  private String destination;
}
