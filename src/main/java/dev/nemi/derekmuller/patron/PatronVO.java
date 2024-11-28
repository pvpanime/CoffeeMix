package dev.nemi.derekmuller.patron;

import lombok.*;

import java.time.Instant;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PatronVO {
  String userid;
  String username;
  byte[] secret;
  Instant added;
  String email;
  String ticket;
}
