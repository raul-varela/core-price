package com.capitole.challenge.core.price.api.controller.auth;

import java.util.Optional;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitole.challenge.core.price.security.domain.User;
import com.capitole.challenge.core.price.security.service.IAuthService;
import com.capitole.challenge.core.price.util.dto.security.JwtResponse;
import com.capitole.challenge.core.price.util.dto.security.LoginRequest;
import com.capitole.challenge.core.price.util.dto.security.MessageResponse;
import com.capitole.challenge.core.price.util.dto.security.SignupRequest;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final IAuthService authService;

  @ApiOperation(value = "This method is used  to autenticate.")
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    JwtResponse response = authService.authenticateUser(loginRequest);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    Optional<User> ouser = authService.registerUser(signUpRequest);

    if (ouser.isPresent()) {
      return ResponseEntity.ok(new MessageResponse("User registered successfully! " + ouser.get()));
    } else {
      return ResponseEntity.badRequest().body(new MessageResponse("Check data entereds"));
    }

  }
}
