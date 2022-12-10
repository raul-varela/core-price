package com.capitole.challenge.core.price.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capitole.challenge.core.price.security.component.AuthEntryPointJwt;
import com.capitole.challenge.core.price.security.component.AuthTokenFilter;
import com.capitole.challenge.core.price.security.component.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {

  private static final String[] AUTH_WHITELIST = {
          "/api/auth/**", // auth signin & signon

          "/h2/**", // H2 Console
          "/swagger-resources/**", //swagger
          "/swagger-ui/**", //swagger
          "/v3/api-docs/**" //swagger
  };
  private final UserDetailsServiceImpl userDetailsService;
  private final AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .cors().and() //
            .csrf().disable() //
            .exceptionHandling() //
            .authenticationEntryPoint(unauthorizedHandler).and() //
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //
            .authorizeRequests() //
            .antMatchers(AUTH_WHITELIST).permitAll() //
            .anyRequest().authenticated();


    // allow show tables and operators in H2 Console
    http.csrf().disable();
    http.headers().frameOptions().disable();

    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
