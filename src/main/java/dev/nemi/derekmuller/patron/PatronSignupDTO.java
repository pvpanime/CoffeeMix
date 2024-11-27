package dev.nemi.derekmuller.patron;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatronSignupDTO {
  private String userid;
  private String username;
  private String rawPassword;
  private String email;
}
