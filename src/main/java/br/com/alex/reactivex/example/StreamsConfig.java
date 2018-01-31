package br.com.alex.reactivex.example;

import io.reactivex.subjects.ReplaySubject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StreamsConfig {

  @Bean
  public ReplaySubject subject() {
    return ReplaySubject.createWithSize(10);
  }

}
