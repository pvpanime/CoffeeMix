package dev.nemi.derekmuller.board.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardViewDTO {
  private long id;
  private String title;
  private String content;
  private Instant createdAt;
  private Instant lastMod;
  private Integer userId;

}
