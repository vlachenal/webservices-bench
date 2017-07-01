/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.vlachenal.webservice.bench.thrift.api.CustomerService;
import com.github.vlachenal.webservice.bench.thrift.api.CustomerServiceHandler;
import com.github.vlachenal.webservice.bench.thrift.api.TRequestSeqServlet;


/**
 * Spring-boot application
 *
 * @author Vincent Lachenal
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  // Methods +
  /**
   * Application entry point
   *
   * @param args arguments ... not used
   */
  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
   */
  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
    return builder.sources(Application.class);
  }

  /**
   * Thrift protocol factory provider
   *
   * @return the Thrift protocol factory
   */
  @Bean
  public TProtocolFactory getThriftProtocolFactory() {
    return new TCompactProtocol.Factory();
  }

  /**
   * Thrift protocol servlet provider
   *
   * @param protocolFactory the protocol factory to use
   * @param handler the Thrift service implementation
   *
   * @return the Thrift servlet
   */
  @Bean
  public ServletRegistrationBean thriftCustomer(final TProtocolFactory protocolFactory, final CustomerServiceHandler handler) {
    final ServletRegistrationBean bean = new ServletRegistrationBean(new TRequestSeqServlet(new CustomerService.Processor<CustomerServiceHandler>(handler),
                                                                                            protocolFactory),
        "/thrift/customer/");
    bean.setLoadOnStartup(1);
    return bean;
  }
  // Methods -

}
