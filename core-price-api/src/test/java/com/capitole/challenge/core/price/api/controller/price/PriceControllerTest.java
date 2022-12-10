package com.capitole.challenge.core.price.api.controller.price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.capitole.challenge.core.price.api.controller.price.PriceController;
import com.capitole.challenge.core.price.domain.entity.Brand;
import com.capitole.challenge.core.price.domain.entity.ECurrency;
import com.capitole.challenge.core.price.domain.entity.PriceList;
import com.capitole.challenge.core.price.domain.entity.Product;
import com.capitole.challenge.core.price.domain.repository.PriceRepository;
import com.capitole.challenge.core.price.util.dto.price.RequestPriceList;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

  @Mock
  PriceRepository priceRepository;

  @InjectMocks
  PriceController priceController;

  @Test
  @DisplayName("Should return no price when the product and date are invalid")
  void getPriceByProductAndDateWhenProductAndDateAreInvalidThenReturnNoPrice() {
    RequestPriceList requestPriceList =
            RequestPriceList.builder()
                    .brandId(1L)
                    .productId(1L)
                    .applicationTime(LocalDateTime.now())
                    .build();

    when(priceRepository.getPriceListByBrandAndProductAndBetweenDates(
            anyLong(), anyLong(), any(), any()))
            .thenReturn(Collections.emptyList());

    assertEquals(
            HttpStatus.OK,
            priceController.getPriceByProductAndDate(requestPriceList).getStatusCode());
    assertEquals(
            "No Price Available",
            priceController.getPriceByProductAndDate(requestPriceList).getBody());
  }

  @Test
  @DisplayName("Should return a price when the product and date are valid")
  void getPriceByProductAndDateWhenProductAndDateAreValidThenReturnPrice() {
    RequestPriceList requestPriceList =
            RequestPriceList.builder()
                    .brandId(1L)
                    .productId(1L)
                    .applicationTime(LocalDateTime.now())
                    .build();

    PriceList priceList =
            PriceList.builder()
                    .id(1L)
                    .brand(Brand.builder().id(1L).name("brand").build())
                    .product(Product.builder().id(1L).name("product").build())
                    .startDate(LocalDateTime.now())
                    .endDate(LocalDateTime.now())
                    .priority(1)
                    .price(10D)
                    .currency(ECurrency.EUR)
                    .build();

    when(priceRepository.getPriceListByBrandAndProductAndBetweenDates(
            anyLong(), anyLong(), any(), any()))
            .thenReturn(Collections.singletonList(priceList));

    var response = priceController.getPriceByProductAndDate(requestPriceList);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(Collections.singletonList(priceList), response.getBody());
  }
}