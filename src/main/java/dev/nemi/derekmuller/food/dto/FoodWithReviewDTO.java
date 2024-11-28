package dev.nemi.derekmuller.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodWithReviewDTO {
  private long id;
  private String name;
  private String description;
  private long price;
  private int status;
  private double avgRate;

  public String getPriceView() {
    return NumberFormat.getCurrencyInstance(new Locale("ko", "kr")).format(price);
  }
  public String getAvgRateView() {
    return String.format("%.1f", avgRate);
  }
}
