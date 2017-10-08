package com.github.vlachenal.webservice.bench.protobuf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Protocol buffer message converter converter
 *
 * @author Vincent Lachenal
 */
@Configuration
public class ProtobufMessageConverterConfig {

  // Methods +
  /**
   * Protocol buffer customer message converter
   *
   * @return the message converter
   */
  @Bean
  public CustomerMessageConverter protobufCustomerConverter() {
    return new CustomerMessageConverter();
  }
  // Methods -

}
