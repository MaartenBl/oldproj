package com.backend.FUN4;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(MatchRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Match("Eindhoven", "Monday","20:30", "A", "B")));
      log.info("Preloading " + repository.save(new Match("Eindhoven", "Thursday","20:30", "B", "A")));
    };
  }
}