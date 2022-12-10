package com.capitole.challenge.core.price.util.dto.price;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPriceList {

  private Long brandId;
  private Long productId;
  private LocalDateTime applicationTime;
}
