/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.dozer;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.vlachenal.webservice.bench.dao.bean.AddressBean;
import com.github.vlachenal.webservice.bench.dao.bean.CustomerBean;
import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean;
import com.github.vlachenal.webservice.bench.mapping.AbstractMappingTest;


/**
 * Dozer mapping unit tests
 *
 * @author Vincent Lachenal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DozerMappingTest extends AbstractMappingTest {

  // Attributes +
  /** {@link DozerMappingTest logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(DozerMappingTest.class);

  /** Date formatter */
  private static final DateTimeFormatter DFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
    final CustomerBean bean = new CustomerBean();
    bean.setId(UUID.randomUUID().toString());
    bean.setFirstName("Chuck");
    bean.setLastName("Norris");
    bean.setBirthDate(Date.from(LocalDate.parse("1940-03-10", DFORMAT).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    bean.setEmail("chuck.norris@yopmail.com");
    final AddressBean addr = new AddressBean();
    final ArrayList<String> lines = new ArrayList<>(1);
    lines.add("1 rue du Petit-Rapporteur");
    addr.setLines(lines);
    addr.setZipCode("46800");
    addr.setCity("Montcuq");
    addr.setCountry("France");
    bean.setAddress(addr);
    final ArrayList<PhoneBean> phones = new ArrayList<>(2);
    PhoneBean phone = new PhoneBean();
    phone.setType(PhoneBean.Type.MOBILE);
    phone.setNumber("+33636656565");
    phones.add(phone);
    phone = new PhoneBean();
    phone.setType(PhoneBean.Type.LANDLINE);
    phone.setNumber("+33836656565");
    phones.add(phone);
    bean.setPhones(phones);
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
    final CustomerBean bean = new CustomerBean();
    bean.setId(UUID.randomUUID().toString());
    bean.setFirstName("Chuck");
    bean.setLastName("Norris");
    bean.setBirthDate(Date.from(LocalDate.parse("1940-03-10", DFORMAT).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    bean.setEmail("chuck.norris@yopmail.com");
    final AddressBean addr = new AddressBean();
    final ArrayList<String> lines = new ArrayList<>(1);
    lines.add("1 rue du Petit-Rapporteur");
    addr.setLines(lines);
    addr.setZipCode("46800");
    addr.setCity("Montcuq");
    addr.setCountry("France");
    bean.setAddress(addr);
    final ArrayList<PhoneBean> phones = new ArrayList<>(2);
    PhoneBean phone = new PhoneBean();
    phone.setType(PhoneBean.Type.MOBILE);
    phone.setNumber("+33636656565");
    phones.add(phone);
    phone = new PhoneBean();
    phone.setType(PhoneBean.Type.LANDLINE);
    phone.setNumber("+33836656565");
    phones.add(phone);
    bean.setPhones(phones);
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
    final CustomerBean bean = new CustomerBean();
    bean.setId(UUID.randomUUID().toString());
    bean.setFirstName("Chuck");
    bean.setLastName("Norris");
    bean.setBirthDate(Date.from(LocalDate.parse("1940-03-10", DFORMAT).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    bean.setEmail("chuck.norris@yopmail.com");
    final AddressBean addr = new AddressBean();
    final ArrayList<String> lines = new ArrayList<>(1);
    lines.add("1 rue du Petit-Rapporteur");
    addr.setLines(lines);
    addr.setZipCode("46800");
    addr.setCity("Montcuq");
    addr.setCountry("France");
    bean.setAddress(addr);
    final ArrayList<PhoneBean> phones = new ArrayList<>(2);
    PhoneBean phone = new PhoneBean();
    phone.setType(PhoneBean.Type.MOBILE);
    phone.setNumber("+33636656565");
    phones.add(phone);
    phone = new PhoneBean();
    phone.setType(PhoneBean.Type.LANDLINE);
    phone.setNumber("+33836656565");
    phones.add(phone);
    bean.setPhones(phones);
    final com.github.vlachenal.webservice.bench.rest.api.bean.Customer customer = dozer.map(bean, com.github.vlachenal.webservice.bench.rest.api.bean.Customer.class);
    assertNotNull("SOAP customer is null", customer);
    compareCustomer(bean, customer);
    LOG.debug("Exit testBeanToRESTCustomer");
  }
  // Tests -

}
