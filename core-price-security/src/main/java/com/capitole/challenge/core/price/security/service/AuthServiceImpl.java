package com.capitole.challenge.core.price.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capitole.challenge.core.price.security.component.JwtUtils;
import com.capitole.challenge.core.price.security.component.UserDetailsImpl;
import com.capitole.challenge.core.price.security.domain.ERole;
import com.capitole.challenge.core.price.security.domain.Role;
import com.capitole.challenge.core.price.security.domain.User;
import com.capitole.challenge.core.price.security.repository.RoleRepository;
import com.capitole.challenge.core.price.security.repository.UserRepository;
import com.capitole.challenge.core.price.util.dto.security.JwtResponse;
import com.capitole.challenge.core.price.util.dto.security.LoginRequest;
import com.capitole.challenge.core.price.util.dto.security.SignupRequest;

@Service
public class AuthServiceImpl implements IAuthService {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Override
  public JwtResponse authenticateUser(LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return JwtResponse.builder()//
            .id(userDetails.getId())//
            .email(userDetails.getEmail())//
            .roles(roles)//
            .token(jwt)//
            .username(userDetails.getUsername())//
            .build();

  }

  @Override
  public Optional<User> registerUser(SignupRequest signUpRequest) {

//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//              .badRequest()
//              .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//              .badRequest()
//              .body(new MessageResponse("Error: Email is already in use!"));
//    }

    // Create new user's account
    User user = User.builder() //
            .username(signUpRequest.getUsername())//
            .email(signUpRequest.getEmail())//
            .password(encoder.encode(signUpRequest.getPassword()))//
            .build();

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return Optional.of(user);
  }
}
