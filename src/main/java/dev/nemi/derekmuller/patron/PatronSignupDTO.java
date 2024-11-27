package dev.nemi.derekmuller.patron;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatronSignupDTO {
  private String userid;
  private String username;
  private String password_raw;
  private String email;
}
