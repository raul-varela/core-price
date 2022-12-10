package com.capitole.challenge.core.price.security.service;

import java.util.Optional;

import com.capitole.challenge.core.price.security.domain.User;
import com.capitole.challenge.core.price.util.dto.security.JwtResponse;
import com.capitole.challenge.core.price.util.dto.security.LoginRequest;
import com.capitole.challenge.core.price.util.dto.security.SignupRequest;

public interface IAuthService {

  JwtResponse authenticateUser(LoginRequest loginRequest);

  Optional<User> registerUser(SignupRequest signUpRequest);
}
