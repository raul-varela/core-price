package com.capitole.challenge.core.price.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("ApiApplication")
class ApiApplicationTest {

  @Test
  @DisplayName("Should run the application")
  void mainRunApplication() {
    ApiApplication.main(new String[]{});
  }
}