/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO;


/**
 * Customer DAO unit tests
 *
 * @author Vincent Lachenal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDAOTest {

  // Attributes +
  /** {@link CustomerDAOTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(CustomerDAOTest.class);

  /** Date formatter */
  private static DateTimeFormatter dateFormat;

  /** Newly created customer identifier */
  private static String customerId;

  /** Initialization status */
  private static AtomicBoolean initialized = new AtomicBoolean(false);

  /** Customer datasource */
  @Qualifier("ds.customer")
  @Autowired
  private DataSource dataSource;

  /** Customer DAO */
  @Autowired
  private CustomerDAO dao;
  // Attributes -


  // Unit tests (un)initialization +
  /**
   * Unit tests initialization
   */
  @BeforeClass
  public static void setUpBeforeClass() {
    dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  }

  /**
   * Initialize database if needed
   */
  @Profile("ci")
  @Before
  public void setUpBefore() {
    if(!initialized.get()) {
      final ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("schema-hsqldb.sql"));
      populator.execute(dataSource);
      initialized.set(true);
    }
  }
  // Unit tests (un)initialization -


  // Tests +
  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#listAll()}.
   */
  @Test
  public void test2ListAll() {
    final List<CustomerDTO> customers = dao.listAll();
    boolean found = false;
    for(final CustomerDTO customer : customers) {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
      if(customer.getId().equals(customerId)) {
        found = true;
      }
    }
    assertTrue("New customer has not been found in database", found);
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#getDetails(java.lang.String)}.
   */
  @Test
  public void test3GetDetails() {
    assertNotNull("Customer identifier is not set", customerId);
    final UUID id = UUID.fromString(customerId);
    final CustomerDTO customer = dao.getDetails(id);
    LOG.info("Customer {} is {} {}", customerId, customer.getFirstName(), customer.getLastName());
    LOG.info("He has been born {}", customer.getBirthDate());
    LOG.info("His email address is {}", customer.getEmail());
    if(customer.getAddress() != null) {
      LOG.info("Address: ");
      for(final String line : customer.getAddress().getLines()) {
        LOG.info(line);
      }
      LOG.info("{} {}", customer.getAddress().getZipCode(), customer.getAddress().getCity());
      LOG.info(customer.getAddress().getCountry());
    }
    if(customer.getPhones() != null) {
      for(final PhoneDTO phone : customer.getPhones()) {
        LOG.info("Phone {}: {}", phone.getType(), phone.getNumber());
      }
    }
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#create(com.github.vlachenal.webservice.bench.dto.CustomerDTO)}.
   */
  @Test
  public void test1Create() {
    final CustomerDTO cust = new CustomerDTO();
    cust.setFirstName("Chuck");
    cust.setLastName("Norris");
    cust.setBirthDate(Date.from(LocalDate.parse("1940-03-10", dateFormat).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    cust.setEmail("chuck.norris@yopmail.com");
    final AddressDTO addr = new AddressDTO();
    final ArrayList<String> lines = new ArrayList<>(1);
    lines.add("1 rue du Petit-Rapporteur");
    addr.setLines(lines);
    addr.setZipCode("46800");
    addr.setCity("Montcuq");
    addr.setCountry("France");
    cust.setAddress(addr);
    final ArrayList<PhoneDTO> phones = new ArrayList<>(2);
    PhoneDTO phone = new PhoneDTO();
    phone.setType(PhoneDTO.Type.MOBILE);
    phone.setNumber("+33636656565");
    phones.add(phone);
    phone = new PhoneDTO();
    phone.setType(PhoneDTO.Type.LANDLINE);
    phone.setNumber("+33836656565");
    phones.add(phone);
    cust.setPhones(phones);
    final String uuid = dao.create(cust);
    LOG.debug("New customer UUID is {}", uuid);
    customerId = uuid;
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#deleteAll()}.
   */
  @Test
  public void test5DeleteAll() {
    dao.deleteAll();
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#create(com.github.vlachenal.webservice.bench.dto.CustomerDTO)}.<br>
   * This should fail due to database integrity constraints
   */
  @Test(expected=DataAccessException.class)
  public void test4CreateFail() {
    final CustomerDTO cust = new CustomerDTO();
    final String uuid = dao.create(cust);
    LOG.debug("New customer UUID is {}", uuid);
    customerId = uuid;
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#create(com.github.vlachenal.webservice.bench.dto.CustomerDTO)}.<br>
   * This should fail due to database integrity constraints.<br>
   * This will test transction annotation.
   */
  @Test(expected=DataAccessException.class)
  public void test6CreateFail() {
    final CustomerDTO cust = new CustomerDTO();
    cust.setFirstName("Chuck");
    cust.setLastName("Norris");
    cust.setBirthDate(Date.from(LocalDate.parse("1940-03-10", dateFormat).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    cust.setEmail("chuck.norris@yopmail.com");
    final AddressDTO addr = new AddressDTO();
    addr.setZipCode("46800");
    addr.setCity("Montcuq");
    addr.setCountry("France");
    cust.setAddress(addr);
    final ArrayList<PhoneDTO> phones = new ArrayList<>(2);
    PhoneDTO phone = new PhoneDTO();
    phone.setType(PhoneDTO.Type.MOBILE);
    phone.setNumber("+33636656565");
    phones.add(phone);
    phone = new PhoneDTO();
    phone.setType(PhoneDTO.Type.LANDLINE);
    phone.setNumber("+33836656565");
    phones.add(phone);
    cust.setPhones(phones);
    final String uuid = dao.create(cust);
    LOG.debug("New customer UUID is {}", uuid);
    customerId = uuid;
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#listAll()}.
   */
  @Test
  public void test7ListAll() {
    final List<CustomerDTO> customers = dao.listAll();
    boolean found = false;
    for(final CustomerDTO customer : customers) {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
      if(customer.getId().equals(customerId)) {
        found = true;
      }
    }
    assertFalse("New customer has not been found in database", found);
  }
  // Tests -

}
