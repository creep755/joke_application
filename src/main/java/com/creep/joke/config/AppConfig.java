package com.creep.joke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The type App config. */
@Configuration
public class AppConfig {
  /**
   * Random java . util . random.
   *
   * @return the java . util . random
   */
  @Bean
  public java.util.Random random() {
    return new java.util.Random();
  }
}
