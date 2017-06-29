/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;


/**
 * Datasource configuration
 *
 * @author Vincent Lachenal
 */
@Configuration
@PropertySource({"classpath:db.properties"})
public class RequestDataSource {

  // Methods +
  /**
   * Provide customer database datasource
   *
   * @return the datasource
   */
  @Bean(name="DS.customer")
  @Primary
  @ConfigurationProperties(prefix="DS.customer")
  public DataSource provideCustomerDataSource() {
    return DataSourceBuilder.create().build();
  }
  // Methods -

}
