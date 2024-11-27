package dev.nemi.derekmuller.board.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateDTO {
  private long id;
  private String title;
  private String content;
}
