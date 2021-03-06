/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.mapping.AbstractMappingTest;
import com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest;
import com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest;


/**
 * MapStruct mapping unit tests
 *
 * @author Vincent Lachenal
 */
@DisplayName("MapStruct mapping test suite")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Execution(ExecutionMode.CONCURRENT)
public class MapStructMappingTest extends AbstractMappingTest {

  // Attributes +
  /** {@link MapStructMappingTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(MapStructMappingTest.class);

  /** MapStruct mappers instance */
  @Autowired
  private MapStructMappers mapstruct;
  // Attributes -


  // Tests +
  /**
   * Customer bean to SOAP conversion unit test
   */
  @DisplayName("Bean to SOAP customer")
  @Test
  public void testBeanToSOAPCustomer() {
    LOG.debug("Enter in testBeanToSOAPCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.soap.api.Customer customer = mapstruct.customer().toSoap(bean);
    assertAll(() -> assertNotNull(customer, "SOAP customer is null"),
              () -> compareCustomer(bean, customer));
    LOG.debug("Exit testBeanToSOAPCustomer");
  }

  /**
   * Customer bean to Thrift conversion unit test
   */
  @DisplayName("Bean to SOAP Thrift")
  @Test
  public void testBeanToThriftCustomer() {
    LOG.debug("Enter in testBeanToThriftCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.thrift.api.Customer customer = mapstruct.customer().toThrift(bean);
    assertAll(() -> assertNotNull(customer, "Thrift customer is null"),
              () -> compareCustomer(bean, customer));
    LOG.debug("Exit testBeanToThriftCustomer");
  }

  /**
   * Customer bean to REST conversion unit test
   */
  @DisplayName("Bean to REST customer")
  @Test
  public void testBeanToRESTCustomer() {
    LOG.debug("Enter in testBeanToRESTCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.rest.api.model.Customer customer = mapstruct.customer().toRest(bean);
    assertAll(() -> assertNotNull(customer, "REST customer is null"),
              () -> compareCustomer(bean, customer));
    LOG.debug("Exit testBeanToRESTCustomer");
  }

  /**
   * Customer bean to Protocol Buffer conversion unit test
   */
  @DisplayName("Bean to Protocol Buffers customer")
  @Test
  public void testBeanToProtobufCustomer() {
    LOG.debug("Enter in testBeanToProtobufCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.protobuf.api.Customer customer = mapstruct.protobuf().toProtobuf(bean);
    assertAll(() -> assertNotNull(customer, "Protocol buffers customer is null"),
              () -> compareCustomer(bean, customer));
    LOG.debug("Exit testBeanToProtobufCustomer");
  }

  /**
   * Search request Thrift to bean conversion unit test
   */
  @DisplayName("Thrift to bean search request")
  @Test
  public void testThriftToDTOSearchRequest() {
    LOG.debug("Enter in testThriftToDTOSearchRequest");
    final ListAllRequest thrift = makeThriftSearchRequest();
    final SearchRequestDTO dto = mapstruct.search().fromThrift(thrift);
    assertAll(() -> assertNotNull(dto, "DTO is null"),
              () -> compareSearchRequest(dto, thrift));
    LOG.debug("Exit testThriftToDTOSearchRequest");
  }

  /**
   * Search request SOAP to bean conversion unit test
   *
   * @throws DatatypeConfigurationException can not happened ?
   */
  @DisplayName("SOAP to bean search request")
  @Test
  public void testSOAPToDTOSearchRequest() throws DatatypeConfigurationException {
    LOG.debug("Enter in testSOAPToDTOSearchRequest");
    final ListCustomersRequest soap = makeSOAPSearchRequest();
    final SearchRequestDTO dto = mapstruct.search().fromSoap(soap);
    assertAll(() -> assertNotNull(dto, "DTO is null"),
              () -> compareSearchRequest(dto, soap));
    LOG.debug("Exit testSOAPToDTOSearchRequest");
  }
  // Tests -

}
