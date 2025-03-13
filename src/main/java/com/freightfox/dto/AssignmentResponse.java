package com.freightfox.dto;

import com.freightfox.model.Lane;
import com.freightfox.model.Transporter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentResponse {

  private Long assignment_id;
  private Lane lane;
  private Transporter transporter;
}
