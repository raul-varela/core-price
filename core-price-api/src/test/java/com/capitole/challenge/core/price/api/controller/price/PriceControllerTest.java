package com.capitole.challenge.core.price.api.controller.price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
  @DisplayName("Should return a list of prices when there are prices in the database")
  void getAllWhenThereArePricesInTheDatabaseThenReturnAListOfPrices() {
    PriceList priceList =
            PriceList.builder()
                    .id(1L)
                    .brand(Brand.builder().id(1L).name("Brand 1").build())
                    .product(Product.builder().id(1L).name("Product 1").build())
                    .startDate(LocalDateTime.now())
                    .endDate(LocalDateTime.now())
                    .priority(1)
                    .price(10.0)
                    .currency(ECurrency.EUR)
                    .build();

    Pageable pageable = PageRequest.of(0, 50);
    List<PriceList> listPrice = Arrays.asList(priceList);
    Page<PriceList> priceListPage = new PageImpl<>(Collections.singletonList(priceList));

    when(priceRepository.findAll(pageable)).thenReturn(priceListPage);


    ResponseEntity<?> rta = priceController.getAll();

    assertEquals(HttpStatus.OK, rta.getStatusCode());
    assertEquals(priceListPage, rta.getBody());
  }

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
            .thenReturn(Arrays.asList(priceList));

    var response = priceController.getPriceByProductAndDate(requestPriceList);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(Arrays.asList(priceList), response.getBody());
  }
}