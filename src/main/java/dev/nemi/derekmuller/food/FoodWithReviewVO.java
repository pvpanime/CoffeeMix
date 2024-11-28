package dev.nemi.derekmuller.food;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class FoodWithReviewVO {
  long id;
  String name;
  String description;
  long price;
  int status;
  double rate;
}
