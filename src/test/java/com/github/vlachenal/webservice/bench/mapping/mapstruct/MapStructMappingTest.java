/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.mapstruct;

import static org.junit.Assert.assertNotNull;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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
  @Test
  public void testBeanToSOAPCustomer() {
    LOG.debug("Enter in testBeanToSOAPCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.soap.api.Customer customer = mapstruct.customer().toSoap(bean);
    assertNotNull("SOAP customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToSOAPCustomer");
  }

  /**
   * Customer bean to Thrift conversion unit test
   */
  @Test
  public void testBeanToThriftCustomer() {
    LOG.debug("Enter in testBeanToThriftCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.thrift.api.Customer customer = mapstruct.customer().toThrift(bean);
    assertNotNull("Thrift customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToThriftCustomer");
  }

  /**
   * Customer bean to REST conversion unit test
   */
  @Test
  public void testBeanToRESTCustomer() {
    LOG.debug("Enter in testBeanToRESTCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.rest.api.model.Customer customer = mapstruct.customer().toRest(bean);
    assertNotNull("REST customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToRESTCustomer");
  }

  /**
   * Customer bean to Protocol Buffer conversion unit test
   */
  @Test
  public void testBeanToProtobufCustomer() {
    LOG.debug("Enter in testBeanToProtobufCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.protobuf.api.Customer customer = mapstruct.protobuf().dtoToProtobuf(bean);
    assertNotNull("Protocol buffers customer is null", customer);
    //    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToProtobufCustomer");
  }

  /**
   * Search request Thrift to bean conversion unit test
   */
  @Test
  public void testThriftToDTOSearchRequest() {
    LOG.debug("Enter in testThriftToDTOSearchRequest");
    final ListAllRequest thrift = makeThriftSearchRequest();
    final SearchRequestDTO dto = mapstruct.search().fromThrift(thrift);
    assertNotNull("DTO is null", dto);
    compareSearchRequest(dto, thrift);
    LOG.debug("Exit testThriftToDTOSearchRequest");
  }

  /**
   * Search request SOAP to bean conversion unit test
   *
   * @throws DatatypeConfigurationException can not happened ?
   */
  @Test
  public void testSOAPToDTOSearchRequest() throws DatatypeConfigurationException {
    LOG.debug("Enter in testSOAPToDTOSearchRequest");
    final ListCustomersRequest soap = makeSOAPSearchRequest();
    final SearchRequestDTO dto = mapstruct.search().fromSoap(soap);
    assertNotNull("DTO is null", dto);
    compareSearchRequest(dto, soap);
    LOG.debug("Exit testSOAPToDTOSearchRequest");
  }
  // Tests -

}
