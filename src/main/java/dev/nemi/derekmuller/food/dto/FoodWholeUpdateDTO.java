package dev.nemi.derekmuller.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FoodWholeUpdateDTO {
  private long id;
  private String name;
  private String description;
  private long price;
}
