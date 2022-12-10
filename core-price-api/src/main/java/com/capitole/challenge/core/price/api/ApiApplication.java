package com.capitole.challenge.core.price.api;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.capitole.challenge.core.price")
@ComponentScan("com.capitole.challenge.core.price")
@EnableJpaRepositories(basePackages = "com.capitole.challenge.core.price")
@EntityScan("com.capitole.challenge.core.price.*")
public class ApiApplication {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(ApiApplication.class);
    application.setBannerMode(Banner.Mode.LOG);
    application.run(args);
  }

}
