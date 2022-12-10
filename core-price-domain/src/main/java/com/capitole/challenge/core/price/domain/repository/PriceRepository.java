package com.capitole.challenge.core.price.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.capitole.challenge.core.price.domain.entity.PriceList;

@Repository
public interface PriceRepository extends PagingAndSortingRepository<PriceList, Long> {

  @Query("select pl from PriceList pl " + //
          " where pl.brand.id= :brandId and " + //
          " pl.product.id= :productId and " + //
          " :applicationDateTime between  pl.startDate and pl.endDate order by pl.priority desc")
  List<PriceList> getPriceListByBrandAndProductAndBetweenDates(Long brandId, Long productId, LocalDateTime applicationDateTime, PageRequest ps);


}
