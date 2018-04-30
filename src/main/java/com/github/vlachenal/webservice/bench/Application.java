/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import com.github.vlachenal.webservice.bench.thrift.api.CustomerService;
import com.github.vlachenal.webservice.bench.thrift.api.CustomerServiceHandler;
import com.github.vlachenal.webservice.bench.thrift.api.StatisticsServiceHandler;
import com.github.vlachenal.webservice.bench.thrift.api.StatsService;


/**
 * Spring-boot application
 *
 * @author Vincent Lachenal
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@PropertySource({"classpath:application.properties","classpath:hardware.properties"})
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
    setRegisterErrorPageFilter(false); // Let servlet container manage error code
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
   * Thrift customer service servlet provider
   *
   * @param protocolFactory the protocol factory to use
   * @param handler the Thrift service implementation
   *
   * @return the Thrift servlet
   */
  @Bean
  public ServletRegistrationBean<TServlet> thriftCustomer(final TProtocolFactory protocolFactory, final CustomerServiceHandler handler) {
    final ServletRegistrationBean<TServlet> servletReg = new ServletRegistrationBean<>(new TServlet(new CustomerService.Processor<>(handler), protocolFactory), "/thrift/customer");
    servletReg.setName("thriftCustomer");
    return servletReg;
  }

  /**
   * Thrift statistics service servlet provider
   *
   * @param protocolFactory the protocol factory to use
   * @param handler the Thrift service implementation
   *
   * @return the Thrift servlet
   */
  @Bean
  public ServletRegistrationBean<TServlet> thriftStats(final TProtocolFactory protocolFactory, final StatisticsServiceHandler handler) {
    final ServletRegistrationBean<TServlet> servletReg = new ServletRegistrationBean<>(new TServlet(new StatsService.Processor<>(handler), protocolFactory), "/thrift/statistics");
    servletReg.setName("thriftStatistics");
    return servletReg;
  }

  /**
   * HTTP Protocol Buffers message converter provider
   *
   * @return the HTTP Protocol Buffers message converter
   */
  @Bean
  public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
    return new ProtobufHttpMessageConverter();
  }
  // Methods -

}
