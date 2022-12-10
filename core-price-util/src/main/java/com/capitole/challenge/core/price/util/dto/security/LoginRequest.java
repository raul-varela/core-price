package com.capitole.challenge.core.price.util.dto.security;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
  @NotBlank
  @ApiModelProperty(notes = "username", example = "challenge", required = true)
  private String username;

  @NotBlank
  @ApiModelProperty(notes = "password", example = "abcdefg", required = true)
  private String password;

}
