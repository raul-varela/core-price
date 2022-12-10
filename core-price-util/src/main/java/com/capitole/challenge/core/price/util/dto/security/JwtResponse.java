package com.capitole.challenge.core.price.util.dto.security;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
  private String token;
  @Builder.Default
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private List<String> roles;

}
