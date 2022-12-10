package com.capitole.challenge.core.price.domain.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BRANDS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

}

