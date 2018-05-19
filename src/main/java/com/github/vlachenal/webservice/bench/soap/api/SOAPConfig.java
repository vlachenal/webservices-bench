/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.soap.api;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


/**
 * SOAP services configuration
 *
 * @author Vincent Lachenal
 */
@EnableWs
@Configuration
public class SOAPConfig {

  // Methods +
  /**
   * SOAP message dispatcher
   *
   * @param applicationContext the application context
   *
   * @return the servlet dispatcher
   */
  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(final ApplicationContext applicationContext) {
    final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(servlet, "/soap/*");
  }

  /**
   * Customer WSDL definition
   *
   * @param customerSchema the customer service schema
   *
   * @return the WSDL definition
   */
  @Bean(name="soap-customer")
  public DefaultWsdl11Definition customerWsdl11Definition(final XsdSchema customerSchema) {
    final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("CustomerPort");
    wsdl11Definition.setLocationUri("/soap/customer");
    wsdl11Definition.setTargetNamespace("http://github.com/vlachenal/webservices-bench");
    wsdl11Definition.setSchema(customerSchema);
    return wsdl11Definition;
  }

  /**
   * Customer schema
   *
   * @return the schema
   */
  @Bean
  public XsdSchema customerSchema() {
    return new SimpleXsdSchema(new ClassPathResource("customer.xsd"));
  }

  /**
   * Statistics WSDL definition
   *
   * @param statisticsSchema the statistics service schema
   *
   * @return the WSDL definition
   */
  @Bean(name="soap-statistics")
  public DefaultWsdl11Definition statisticsWsdl11Definition(final XsdSchema statisticsSchema) {
    final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("StatisticsPort");
    wsdl11Definition.setLocationUri("/soap/stats");
    wsdl11Definition.setTargetNamespace("http://github.com/vlachenal/webservices-bench");
    wsdl11Definition.setSchema(statisticsSchema);
    return wsdl11Definition;
  }

  /**
   * Statistics schema
   *
   * @return the schema
   */
  @Bean
  public XsdSchema statisticsSchema() {
    return new SimpleXsdSchema(new ClassPathResource("statistics.xsd"));
  }
  // Methods -

}
