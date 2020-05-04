package com.pluralsight.conferencedemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {

  @Value("${spring.datasource.password}")
  private String password;
  @Value("${spring.datasource.username}")
  private String username;

  @Bean
  public DataSource dataSource() {

    //Builder Pattern
    DataSourceBuilder builder = DataSourceBuilder.create();

    builder.url("jdbc:postgresql://localhost:5432/conference_app");
    builder.username(username);
    builder.password(password);

    System.out.println("Custom datasource url.");

    return builder.build();
  }
}
