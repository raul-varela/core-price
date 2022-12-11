package com.capitole.challenge.core.price.api.controller.price;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitole.challenge.core.price.domain.entity.PriceList;
import com.capitole.challenge.core.price.domain.repository.PriceRepository;
import com.capitole.challenge.core.price.util.dto.price.RequestPriceList;

@RequestMapping(path = "api/v1/price")
@RestController
public class PriceController {

  @Autowired
  PriceRepository priceRepository;


  @PostMapping("/getPriceByProductAndDate")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
  public ResponseEntity<?> getPriceByProductAndDate(@RequestBody RequestPriceList payload) {
    List<PriceList> priceList = priceRepository.getPriceListByBrandAndProductAndBetweenDates(payload.getBrandId(), payload.getProductId(), //
            payload.getApplicationTime(), PageRequest.of(0, 1));
    if (!priceList.isEmpty()) {
      return ResponseEntity.ok(priceList);
    } else {
      return ResponseEntity.ok("No Price Available");
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
  public ResponseEntity<?> getAll() {
    Page<PriceList> priceList = priceRepository.findAll(PageRequest.of(0, 50));
    if (!priceList.isEmpty()) {
      return ResponseEntity.ok(priceList);
    } else {
      return ResponseEntity.ok("No Price Available");
    }
  }

}
