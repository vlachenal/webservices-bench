/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.dozer;

import static org.junit.Assert.assertNotNull;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.dozermapper.core.Mapper;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.mapping.AbstractMappingTest;
import com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest;
import com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest;


/**
 * Dozer mapping unit tests
 *
 * @author Vincent Lachenal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DozerMappingTest extends AbstractMappingTest {

  // Attributes +
  /** {@link DozerMappingTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(DozerMappingTest.class);

  /** Dozer mapper instance */
  @Autowired
  private Mapper dozer;
  // Attributes -


  // Tests +
  /**
   * Customer bean to SOAP conversion unit test
   */
  @Test
  public void testBeanToSOAPCustomer() {
    LOG.debug("Enter in testBeanToSOAPCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.soap.api.Customer customer = dozer.map(bean, com.github.vlachenal.webservice.bench.soap.api.Customer.class);
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
    final com.github.vlachenal.webservice.bench.thrift.api.Customer customer = dozer.map(bean, com.github.vlachenal.webservice.bench.thrift.api.Customer.class);
    assertNotNull("SOAP customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToThriftCustomer");
  }

  /**
   * Customer bean to Thrift conversion unit test
   */
  @Test
  public void testBeanToRESTCustomer() {
    LOG.debug("Enter in testBeanToRESTCustomer");
    final CustomerDTO bean = makeCustomerBean();
    final com.github.vlachenal.webservice.bench.rest.api.model.Customer customer = dozer.map(bean, com.github.vlachenal.webservice.bench.rest.api.model.Customer.class);
    assertNotNull("SOAP customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToRESTCustomer");
  }

  /**
   * Search request Thrift to bean conversion unit test
   */
  @Test
  public void testThriftToDTOSearchRequest() {
    LOG.debug("Enter in testThriftToDTOSearchRequest");
    final ListAllRequest thrift = makeThriftSearchRequest();
    final SearchRequestDTO dto = dozer.map(thrift, SearchRequestDTO.class);
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
    final SearchRequestDTO dto = dozer.map(soap, SearchRequestDTO.class);
    assertNotNull("DTO is null", dto);
    compareSearchRequest(dto, soap);
    LOG.debug("Exit testSOAPToDTOSearchRequest");
  }
  // Tests -

}
