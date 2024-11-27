package dev.nemi.derekmuller.food;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodVO {
  private long id;
  private String name;
  private String description;
  private long price;
  private int status;
}
