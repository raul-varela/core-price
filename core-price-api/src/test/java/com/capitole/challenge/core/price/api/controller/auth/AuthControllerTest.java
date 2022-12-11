package com.capitole.challenge.core.price.api.controller.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import com.capitole.challenge.core.price.security.domain.User;
import com.capitole.challenge.core.price.security.service.IAuthService;
import com.capitole.challenge.core.price.util.dto.security.JwtResponse;
import com.capitole.challenge.core.price.util.dto.security.LoginRequest;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

  @Mock
  private IAuthService authService;

  @InjectMocks
  private AuthController authController;

  @Test
  @DisplayName("Should throw an exception when the user is not authenticated")
  void authenticateUserWhenUserIsNotAuthenticatedThenThrowException() {
    LoginRequest loginRequest =
            LoginRequest.builder().username("challenge").password("abcdefg").build();

    when(authService.authenticateUser(loginRequest))
            .thenThrow(new BadCredentialsException("Bad credentials"));

    assertThrows(
            BadCredentialsException.class, () -> authController.authenticateUser(loginRequest));
  }

  @Test
  @DisplayName("Should return a jwtresponse when the user is authenticated")
  void authenticateUserWhenUserIsAuthenticatedThenReturnJwtResponse() {
    LoginRequest loginRequest =
            LoginRequest.builder().username("challenge").password("abcdefg").build();
    User user =
            User.builder()
                    .id(1L)
                    .username("challenge")
                    .email("challenge@capitole.es")
                    .password("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6")
                    .roles(new HashSet<>())
                    .build();
    JwtResponse jwtResponse =
            JwtResponse.builder()
                    .token("token")
                    .id(1L)
                    .username("challenge")
                    .email("challenge@capitole.es")
                    .roles(List.of("ROLE_USER"))
                    .build();

    when(authService.authenticateUser(loginRequest)).thenReturn(jwtResponse);

    ResponseEntity<?> response = authController.authenticateUser(loginRequest);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(jwtResponse, response.getBody());
  }
}