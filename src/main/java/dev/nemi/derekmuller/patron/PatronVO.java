package dev.nemi.derekmuller.patron;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.N;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatronVO {
  private String userid;
  private String username;
  private byte[] secret;
  private Instant added;
  private String email;
}
