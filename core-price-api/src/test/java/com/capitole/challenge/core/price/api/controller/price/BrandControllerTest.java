package com.capitole.challenge.core.price.api.controller.price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capitole.challenge.core.price.domain.entity.Product;
import com.capitole.challenge.core.price.domain.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class BrandControllerTest {
  @Mock
  ProductRepository productRepository;

  @InjectMocks
  BrandController brandController;

  @Test
  @DisplayName("Should return all products when the user is not authenticated")
  void listProductsWhenUserIsNotAuthenticated() {
    List<Product> products =
            Arrays.asList(
                    Product.builder().id(1L).name("product1").build(),
                    Product.builder().id(2L).name("product2").build());
    when(productRepository.findAll()).thenReturn(products);

    List<Product> result = brandController.listProducts();

    assertEquals(products, result);
  }

  @Test
  @DisplayName("Should return all products when the user is authenticated")
  void listProductsWhenUserIsAuthenticated() {
    Product product1 = Product.builder().id(1L).name("product1").build();
    Product product2 = Product.builder().id(2L).name("product2").build();
    List<Product> products = Arrays.asList(product1, product2);
    when(productRepository.findAll()).thenReturn(products);

    List<Product> result = brandController.listProducts();

    assertEquals(products, result);
  }
}