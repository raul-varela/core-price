package com.capitole.challenge.core.price.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capitole.challenge.core.price.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findByName(String name);

  Boolean existsByName(String name);

}
