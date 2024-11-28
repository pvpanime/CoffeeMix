package dev.nemi.derekmuller.patron.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatronProfileDTO {
  private String userid;
  private String username;
  private String email;
}
