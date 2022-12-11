package com.capitole.challenge.core.price.api.controller.price;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitole.challenge.core.price.domain.entity.Product;
import com.capitole.challenge.core.price.domain.repository.ProductRepository;

@RequestMapping(path = "api/v1/brand")
@RestController
public class BrandController {
  @Autowired
  ProductRepository productRepository;

  @GetMapping("/")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
  public List<Product> listProducts() {
    return productRepository.findAll();
  }

}
