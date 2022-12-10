package com.capitole.challenge.core.price.api.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.tomcat.websocket.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  private ApiInfo apiInfo() {
    return new ApiInfo("Capitole's Challenge", //
            "Capitole's Challenge", //
            "1.0", //
            "Terms of service", //
            new Contact("Raúl Varela", "---", "varela.raul.adrian@gmail.com"), //
            "License of API", "API license URL", //
            Collections.emptyList());
  }


  @Bean
  public Docket api() {
    return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()))
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
  }

  private ApiKey apiKey() {
    return new ApiKey(Constants.AUTHORIZATION_HEADER_NAME, "JWT", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference(Constants.AUTHORIZATION_HEADER_NAME, authorizationScopes));
  }
}
