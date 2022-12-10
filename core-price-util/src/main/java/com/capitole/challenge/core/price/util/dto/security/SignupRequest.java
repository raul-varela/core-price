package com.capitole.challenge.core.price.util.dto.security;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

@Data
@ApiModel
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  @ApiModelProperty(notes = "username", example = "challenge", required = true)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  @ApiModelProperty(notes = "email", example = "challenge@capitole.es", required = true)
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  @ApiModelProperty(notes = "password", example = "abcdefg", required = true)
  private String password;

}
