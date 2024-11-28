package dev.nemi.derekmuller.food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class FoodReviewVO {
  long foodId;
  String userId;
  int rate;
  Instant added;
  Instant updated;
}
