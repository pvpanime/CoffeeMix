package dev.nemi.derekmuller.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardAddDTO {
  private String title;
  private String content;
  private Integer userNum;
}
