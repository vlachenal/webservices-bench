/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;


/**
 * Customer DAO unit tests
 *
 * @author Vincent Lachenal
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Customer DAO test suite")
public class CustomerDAOTest {

  // Attributes +
  /** {@link CustomerDAOTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(CustomerDAOTest.class);

  /** Date formatter */
  private static DateTimeFormatter dateFormat;

  /** Newly created customer identifier */
  private String customerId;

  /** Customer datasource */
  @Qualifier("ds.customer")
  @Autowired
  private DataSource dataSource;

  /** Customer DAO */
  @Autowired
  private CustomerDAO dao;

  /** Active profiles ... */
  @Value("${spring.profiles.active:}")
  private String activeProfiles;
  // Attributes -


  // Unit tests (un)initialization +
  /**
   * Unit tests initialization
   */
  @BeforeAll
  public static void setUpBeforeClass() {
    dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  }

  /**
   * Before each test
   */
  @BeforeEach
  public void beforeEach() {
    customerId = null; // Ensure customer identifier is null
  }

  /**
   * After each test
   */
  @AfterEach
  public void afterEach() {
    if(customerId != null) {
      dao.deleteCustomer(UUID.fromString(customerId));
      customerId = null;
    }
  }
  // Unit tests (un)initialization -


  // Tests +
  /**
   * Create Chuck Norris as customer
   *
   * @return the new custome's identifier
   */
  private String createCustomer() {
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
    customerId = dao.createCustomer(cust);
    LOG.debug("New customer UUID is {}", customerId);
    return customerId;
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#createCustomer(com.github.vlachenal.webservice.bench.dto.CustomerDTO)}.
   */
  @DisplayName("Create customer")
  @Test
  public void testCreate() {
    assertNotNull(createCustomer(), "Customer identifier is null");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("List all customers")
  @Test
  public void testListAll() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    final List<CustomerDTO> customers = dao.search(new SearchRequestDTO());
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Search customer by first name pattern")
  @Test
  public void testSearchMatchFirstName() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    final SearchRequestDTO req = new SearchRequestDTO();
    req.setFirstName("C%k");
    final List<CustomerDTO> customers = dao.search(req);
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Search customer by first name")
  @Test
  public void testSearchEqualsFirstName() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    final SearchRequestDTO req = new SearchRequestDTO();
    req.setFirstName("Chuck");
    final List<CustomerDTO> customers = dao.search(req);
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Search customer by last name pattern")
  @Test
  public void testSearchMatchLastName() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    final SearchRequestDTO req = new SearchRequestDTO();
    req.setLastName("N%s");
    final List<CustomerDTO> customers = dao.search(req);
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Search customer by last name")
  @Test
  public void testSearchEqualsLastName() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    final SearchRequestDTO req = new SearchRequestDTO();
    req.setLastName("Norris");
    final List<CustomerDTO> customers = dao.search(req);
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Search customer by birth date")
  @Test
  public void testSearchEqualsBirthDate() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    final SearchRequestDTO req = new SearchRequestDTO();
    req.setBirthDate(Date.from(LocalDate.parse("1940-03-10", dateFormat).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    final List<CustomerDTO> customers = dao.search(req);
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Search customer by maximum birth date")
  @Test
  public void testSearchBornBefore() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    final SearchRequestDTO req = new SearchRequestDTO();
    req.setBornBefore(Date.from(LocalDate.parse("1941-03-10", dateFormat).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    final List<CustomerDTO> customers = dao.search(req);
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Search customer by minimum birth date")
  @Test
  public void testSearchBornAfter() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    final SearchRequestDTO req = new SearchRequestDTO();
    req.setBornAfter(Date.from(LocalDate.parse("1939-03-10", dateFormat).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    final List<CustomerDTO> customers = dao.search(req);
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Search customer by ids")
  @Test
  public void testSearchIds() {
    if(activeProfiles.contains("ci")) { // Do not run this test when HSQLDB ...
      return;
    }
    final String uid = createCustomer();
    assertNotNull(uid, "Customer identifier is null");
    final SearchRequestDTO req = new SearchRequestDTO();
    req.setIds(Stream.of(uid).collect(Collectors.toList()));
    final List<CustomerDTO> customers = dao.search(req);
    customers.forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertTrue(chuck.isPresent(), "New customer has not been found in database");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#getDetails(java.lang.String)}.
   */
  @DisplayName("Retrieve customer's details")
  @Test
  public void testGetDetails() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    assertNotNull("Customer identifier is not set", customerId);
    final UUID id = UUID.fromString(customerId);
    final CustomerDTO customer = dao.getDetails(id);
    LOG.info("Customer {} is {} {}", customerId, customer.getFirstName(), customer.getLastName());
    LOG.info("He has been born {}", customer.getBirthDate());
    LOG.info("His email address is {}", customer.getEmail());
    Optional.ofNullable(customer.getAddress()).ifPresent(addr -> {
      LOG.info("Address: ");
      for(final String line : addr.getLines()) {
        LOG.info(line);
      }
      LOG.info("{} {}", addr.getZipCode(), addr.getCity());
      LOG.info(addr.getCountry());
    });
    Optional.ofNullable(customer.getPhones()).ifPresent(phones -> phones.forEach(phone -> LOG.info("Phone {}: {}", phone.getType(), phone.getNumber())));
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#createCustomer(com.github.vlachenal.webservice.bench.dto.CustomerDTO)}.<br>
   * This should fail due to database integrity constraints
   */
  @DisplayName("Create empty customer - FAIL")
  @Test
  public void testCreateFail() {
    final CustomerDTO cust = new CustomerDTO();
    assertThrows(DataAccessException.class, () -> {
      final String uuid = dao.createCustomer(cust);
      LOG.debug("New customer UUID is {}", uuid);
      customerId = uuid;
    });
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#deleteAll()}.
   */
  @DisplayName("Delete all customers")
  @Test
  public void testDeleteAll() {
    dao.deleteAll();
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#createCustomer(com.github.vlachenal.webservice.bench.dto.CustomerDTO)}.<br>
   * This should fail due to database integrity constraints.<br>
   * This will test transaction annotation.
   */
  @DisplayName("Create duplicate customer - FAIL")
  @Test
  public void testCreateFailDuplicate() {
    assertNotNull(createCustomer(), "Customer identifier is null");
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
    assertThrows(DataAccessException.class, () -> {
      final String uuid = dao.createCustomer(cust);
      LOG.debug("New customer UUID is {}", uuid);
      customerId = uuid;
    });
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.dao.CustomerDAO#search()}.
   */
  @DisplayName("Delete customer")
  @Test
  public void testEmpty() {
    assertNotNull(createCustomer(), "Customer identifier is null");
    dao.deleteCustomer(UUID.fromString(customerId));
    final List<CustomerDTO> customers = dao.search(new SearchRequestDTO());
    customers.stream().forEach(customer -> {
      LOG.info("Found customer {}: {} {} in database", customer.getId(), customer.getFirstName(), customer.getLastName());
    });
    final Optional<CustomerDTO> chuck = customers.stream()
        .filter(customer -> customer.getId().equals(customerId)).findFirst();
    assertFalse(chuck.isPresent(), "New customer has not been found in database");
  }
  // Tests -

}
