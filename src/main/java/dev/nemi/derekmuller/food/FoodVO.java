package dev.nemi.derekmuller.food;


import lombok.*;

@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class FoodVO {
  long id;
  String name;
  String description;
  long price;
  int status;
}
