package com.capitole.challenge.core.price.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "PRICE_LISTS")
public class PriceList {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  private Brand brand;

  @ManyToOne
  private Product product;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  private Integer priority;

  private Double price;

  @Enumerated(EnumType.STRING)
  @Column(length = 3, name = "curr")
  private ECurrency currency;

}
