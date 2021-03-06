/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.vlachenal.webservice.bench.dto.AddressDTO;
import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO;
import com.github.vlachenal.webservice.bench.dto.SearchRequestDTO;
import com.github.vlachenal.webservice.bench.soap.api.ListCustomersRequest;
import com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest;


/**
 * Abstract mapping unit tests
 *
 * @author Vincent Lachenal
 */
public abstract class AbstractMappingTest {

  // Attributes +
  /** {@link AbstractMappingTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(AbstractMappingTest.class);

  /** Date formatter */
  private static final DateTimeFormatter DFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  // Attributes -


  // Methods +
  /**
   * Make customer bean for unit test
   *
   * @return the customer bean
   */
  protected CustomerDTO makeCustomerBean() {
    final CustomerDTO bean = new CustomerDTO();
    bean.setId(UUID.randomUUID().toString());
    bean.setFirstName("Chuck");
    bean.setLastName("Norris");
    bean.setBirthDate(Date.from(LocalDate.parse("1940-03-10", DFORMAT).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    bean.setEmail("chuck.norris@yopmail.com");
    final AddressDTO addr = new AddressDTO();
    final ArrayList<String> lines = new ArrayList<>(1);
    lines.add("1 rue du Petit-Rapporteur");
    addr.setLines(lines);
    addr.setZipCode("46800");
    addr.setCity("Montcuq");
    addr.setCountry("France");
    bean.setAddress(addr);
    final ArrayList<PhoneDTO> phones = new ArrayList<>(2);
    PhoneDTO phone = new PhoneDTO();
    phone.setType(PhoneDTO.Type.MOBILE);
    phone.setNumber("+33636656565");
    phones.add(phone);
    phone = new PhoneDTO();
    phone.setType(PhoneDTO.Type.LANDLINE);
    phone.setNumber("+33836656565");
    phones.add(phone);
    bean.setPhones(phones);
    return bean;
  }

  /**
   * Create SOAP search request
   *
   * @return the request
   *
   * @throws DatatypeConfigurationException can not happened ?
   */
  protected ListCustomersRequest makeSOAPSearchRequest() throws DatatypeConfigurationException {
    final ListCustomersRequest req = new ListCustomersRequest();
    req.setFirstName("Chuck");
    req.setLastName("Norris");
    req.setEmail("chuck.norris@yopmail.com");
    final GregorianCalendar cal = new GregorianCalendar();
    cal.set(1940, 3, 10);
    req.setBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
    cal.add(Calendar.MONTH, -1);
    req.setBornAfter(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
    cal.add(Calendar.MONTH, 2);
    req.setBornBefore(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
    return req;
  }

  /**
   * Create Thrift search request
   *
   * @return the request
   */
  protected ListAllRequest makeThriftSearchRequest() {
    final ListAllRequest req = new ListAllRequest();
    req.setFirstName("Chuck");
    req.setLastName("Norris");
    req.setEmail("chuck.norris@yopmail.com");
    final GregorianCalendar cal = new GregorianCalendar();
    cal.set(1940, 3, 10);
    req.setBirthDate(cal.getTimeInMillis());
    cal.add(Calendar.MONTH, -1);
    req.setBornAfter(cal.getTimeInMillis());
    cal.add(Calendar.MONTH, 2);
    req.setBornBefore(cal.getTimeInMillis());
    return req;
  }

  /**
   * Compare SOAP/bean customer
   *
   * @param bean the bean
   * @param customer the SOAP customer
   */
  protected void compareCustomer(final CustomerDTO bean, final com.github.vlachenal.webservice.bench.soap.api.Customer customer) {
    LOG.info("Customer id: bean = {} ; SOAP = {}", bean.getId(), customer.getId());
    LOG.info("Customer first name: bean = {} ; SOAP = {}", bean.getFirstName(), customer.getFirstName());
    LOG.info("Customer last name: bean = {} ; SOAP = {}", bean.getLastName(), customer.getLastName());
    LOG.info("Customer email: bean = {} ; SOAP = {}", bean.getEmail(), customer.getEmail());
    LOG.info("Customer birthdate: bean = {} ; SOAP = {}", bean.getBirthDate(), customer.getBirthDate());
    assertAll("Compare customer bean to SOAP",
              () -> assertEquals(bean.getId(), customer.getId(), "Identifiers are differents"),
              () -> assertEquals(bean.getFirstName(), customer.getFirstName(), "Firstnames are differents"),
              () -> assertEquals(bean.getLastName(), customer.getLastName(), "Lastnames are differents"),
              () -> assertEquals(bean.getEmail(), customer.getEmail(), "Email addresses are differents"),
              () -> assertEquals(bean.getBirthDate(), customer.getBirthDate().toGregorianCalendar().getTime(), "Birthdates are differents"),
              () -> compareAddress(bean.getAddress(), customer.getAddress()),
              () -> compareSPhones(bean.getPhones(), customer.getPhones()));
  }

  /**
   * Compare SOAP/bean address
   *
   * @param bean the bean
   * @param address the SOAP address
   */
  protected void compareAddress(final AddressDTO bean, final com.github.vlachenal.webservice.bench.soap.api.Address address) {
    LOG.info("Address lines: bean = {} ; SOAP = {}", bean.getLines(), address.getLines());
    LOG.info("Address ZIP code: bean = {} ; SOAP = {}", bean.getZipCode(), address.getZipCode());
    LOG.info("Address city: bean = {} ; SOAP = {}", bean.getCity(), address.getCity());
    LOG.info("Address country: bean = {} ; SOAP = {}", bean.getCountry(), address.getCountry());
    assertAll("Compare address bean to SOAP",
              () -> assertEquals(bean.getLines(), address.getLines(), "Lines are different"),
              () -> assertEquals(bean.getZipCode(), address.getZipCode(), "ZIP codes are different"),
              () -> assertEquals(bean.getCity(), address.getCity(), "Cities are different"),
              () -> assertEquals(bean.getCountry(), address.getCountry(), "Countries are different"));
  }

  /**
   * Compare SOAP/bean phones
   *
   * @param beans the bean list
   * @param phones the SOAP phone list
   */
  protected void compareSPhones(final List<PhoneDTO> beans, final List<com.github.vlachenal.webservice.bench.soap.api.Phone> phones) {
    if(beans != null) {
      assertNotNull(phones, "SOAP phone list is null");
      assertEquals(beans.size(), phones.size(), "Number of phones is different");
      for(int i = 0 ; i < beans.size() ; ++i) {
        comparePhone(beans.get(i), phones.get(i));
      }
    }
  }

  /**
   * Compare SOAP/bean phone
   *
   * @param bean the bean
   * @param phone the SOAP phone
   */
  protected void comparePhone(final PhoneDTO bean, final com.github.vlachenal.webservice.bench.soap.api.Phone phone) {
    LOG.info("Phone number: bean = {} ; SOAP = {}", bean.getNumber(), phone.getNumber());
    LOG.info("Phone type: bean = {} ; SOAP = {}", bean.getType(), phone.getPhoneType());
    assertEquals(bean.getNumber(), phone.getNumber(), "Phones number are differents");
    switch(bean.getType()) {
      case LANDLINE:
        assertEquals(com.github.vlachenal.webservice.bench.soap.api.PhoneType.LANDLINE, phone.getPhoneType(), "Phone types are diffents");
        break;
      case MOBILE:
        assertEquals(com.github.vlachenal.webservice.bench.soap.api.PhoneType.MOBILE, phone.getPhoneType(), "Phone types are diffents");
        break;
      default:
        fail("Unexpected type: " + bean.getType());
    }
  }

  /**
   * Compare SOAP/bean search request
   *
   * @param bean the bean
   * @param request the Thrift request
   */
  protected void compareSearchRequest(final SearchRequestDTO bean, final ListCustomersRequest request) {
    LOG.info("First name: bean = {} ; Thift = {}", bean.getFirstName(), request.getFirstName());
    LOG.info("Last name: bean = {} ; Thift = {}", bean.getLastName(), request.getLastName());
    LOG.info("Email: bean = {} ; Thift = {}", bean.getEmail(), request.getEmail());
    LOG.info("Birthdate: bean = {} ; Thift = {}", bean.getBirthDate(), request.getBirthDate());
    LOG.info("Born after: bean = {} ; Thift = {}", bean.getBornAfter(), request.getBornAfter());
    LOG.info("Born before: bean = {} ; Thift = {}", bean.getBornBefore(), request.getBornBefore());
    assertAll("Compare bean search request to SOAP",
              () -> assertEquals(bean.getFirstName(), request.getFirstName(), "First names are differents"),
              () -> assertEquals(bean.getLastName(), request.getLastName(), "Last names are differents"),
              () -> assertEquals(bean.getEmail(), request.getEmail(), "Emails are differents"),
              () -> assertEquals(bean.getBirthDate(), request.getBirthDate().toGregorianCalendar().getTime(), "Birthdates are differents"),
              () -> assertEquals(bean.getBornAfter(), request.getBornAfter().toGregorianCalendar().getTime(), "Born after dates are differents"),
              () -> assertEquals(bean.getBornBefore(), request.getBornBefore().toGregorianCalendar().getTime(), "Born before dates are differents")
        );
  }

  /**
   * Compare Thrift/bean customer
   *
   * @param bean the bean
   * @param customer the Thrift customer
   */
  protected void compareCustomer(final CustomerDTO bean, final com.github.vlachenal.webservice.bench.thrift.api.Customer customer) {
    LOG.info("Customer id: bean = {} ; Thrift = {}", bean.getId(), customer.getId());
    LOG.info("Customer first name: bean = {} ; Thrift = {}", bean.getFirstName(), customer.getFirstName());
    LOG.info("Customer last name: bean = {} ; Thrift = {}", bean.getLastName(), customer.getLastName());
    LOG.info("Customer email: bean = {} ; Thrift = {}", bean.getEmail(), customer.getEmail());
    LOG.info("Customer birthdate: bean = {} ; Thrift = {}", bean.getBirthDate(), customer.getBirthDate());
    assertAll("Compare bean customer to Thrift",
              () -> assertEquals(bean.getId(), customer.getId(), "Identifiers are differents"),
              () -> assertEquals(bean.getFirstName(), customer.getFirstName(), "Firstnames are differents"),
              () -> assertEquals(bean.getLastName(), customer.getLastName(), "Lastnames are differents"),
              () -> assertEquals(bean.getEmail(), customer.getEmail(), "Email addresses are differents"),
              () -> assertEquals(bean.getBirthDate(), new Date(customer.getBirthDate()), "Birthdates are differents"),
              () -> compareAddress(bean.getAddress(), customer.getAddress()),
              () -> compareTPhones(bean.getPhones(), customer.getPhones())
        );
  }

  /**
   * Compare Thrift/bean address
   *
   * @param bean the bean
   * @param address the Thrift address
   */
  protected void compareAddress(final AddressDTO bean, final com.github.vlachenal.webservice.bench.thrift.api.Address address) {
    LOG.info("Address lines: bean = {} ; Thrift = {}", bean.getLines(), address.getLines());
    LOG.info("Address ZIP code: bean = {} ; Thrift = {}", bean.getZipCode(), address.getZipCode());
    LOG.info("Address city: bean = {} ; Thrift = {}", bean.getCity(), address.getCity());
    LOG.info("Address country: bean = {} ; Thrift = {}", bean.getCountry(), address.getCountry());
    assertAll("Compare bean address to Thrift",
              () -> assertEquals(bean.getLines(), address.getLines(), "Lines are different"),
              () -> assertEquals(bean.getZipCode(), address.getZipCode(), "ZIP codes are different"),
              () -> assertEquals(bean.getCity(), address.getCity(), "Cities are different"),
              () -> assertEquals(bean.getCountry(), address.getCountry(), "Countries are different")
        );
  }

  /**
   * Compare Thrift/bean phones
   *
   * @param beans the bean list
   * @param phones the Thrift phone list
   */
  protected void compareTPhones(final List<PhoneDTO> beans, final List<com.github.vlachenal.webservice.bench.thrift.api.Phone> phones) {
    if(beans != null) {
      assertNotNull(phones, "Thrift phone list is null");
      assertEquals(beans.size(), phones.size(), "Number of phones is different");
      for(int i = 0 ; i < beans.size() ; ++i) {
        comparePhone(beans.get(i), phones.get(i));
      }
    }
  }

  /**
   * Compare Thrift/bean phone
   *
   * @param bean the bean
   * @param phone the Thrift phone
   */
  protected void comparePhone(final PhoneDTO bean, final com.github.vlachenal.webservice.bench.thrift.api.Phone phone) {
    LOG.info("Phone number: bean = {} ; Thrift = {}", bean.getNumber(), phone.getNumber());
    LOG.info("Phone type: bean = {} ; Thrift = {}", bean.getType(), phone.getType());
    assertEquals(bean.getNumber(), phone.getNumber(), "Phone numbers are different");
    switch(bean.getType()) {
      case LANDLINE:
        assertEquals(com.github.vlachenal.webservice.bench.thrift.api.PhoneType.LANDLINE, phone.getType(), "Phone types are different");
        break;
      case MOBILE:
        assertEquals(com.github.vlachenal.webservice.bench.thrift.api.PhoneType.MOBILE, phone.getType(), "Phone types are different");
        break;
      default:
        fail("Unexpected type: " + bean.getType());
    }
  }

  /**
   * Compare Thrift/bean search request
   *
   * @param bean the bean
   * @param request the Thrift request
   */
  protected void compareSearchRequest(final SearchRequestDTO bean, final com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest request) {
    LOG.info("First name: bean = {} ; Thift = {}", bean.getFirstName(), request.getFirstName());
    LOG.info("Last name: bean = {} ; Thift = {}", bean.getLastName(), request.getLastName());
    LOG.info("Email: bean = {} ; Thift = {}", bean.getEmail(), request.getEmail());
    LOG.info("Birthdate: bean = {} ; Thift = {}", bean.getBirthDate(), request.getBirthDate());
    LOG.info("Born after: bean = {} ; Thift = {}", bean.getBornAfter(), request.getBornAfter());
    LOG.info("Born before: bean = {} ; Thift = {}", bean.getBornBefore(), request.getBornBefore());
    assertAll("Compare bean search request to Thrift",
              () -> assertEquals(bean.getFirstName(), request.getFirstName(), "First names are differents"),
              () -> assertEquals(bean.getLastName(), request.getLastName(), "Last names are differents"),
              () -> assertEquals(bean.getEmail(), request.getEmail(), "Emails are differents"),
              () -> assertEquals(bean.getBirthDate(), new Date(request.getBirthDate()), "Birthdates are differents"),
              () -> assertEquals(bean.getBornAfter(), new Date(request.getBornAfter()), "Born after dates are differents"),
              () -> assertEquals(bean.getBornBefore(), new Date(request.getBornBefore()), "Born before dates are differents")
        );
  }

  /**
   * Compare REST/bean customer
   *
   * @param bean the bean
   * @param customer the REST customer
   */
  protected void compareCustomer(final CustomerDTO bean, final com.github.vlachenal.webservice.bench.rest.api.model.Customer customer) {
    LOG.info("Customer id: bean = {} ; REST = {}", bean.getId(), customer.getId());
    LOG.info("Customer first name: bean = {} ; REST = {}", bean.getFirstName(), customer.getFirstName());
    LOG.info("Customer last name: bean = {} ; REST = {}", bean.getLastName(), customer.getLastName());
    LOG.info("Customer email: bean = {} ; REST = {}", bean.getEmail(), customer.getEmail());
    LOG.info("Customer birthdate: bean = {} ; REST = {}", bean.getBirthDate(), customer.getBirthDate());
    assertAll("Compare bean customer to REST model",
              () -> assertEquals(bean.getId(), customer.getId(), "Identifiers are differents"),
              () -> assertEquals(bean.getFirstName(), customer.getFirstName(), "Firstnames are differents"),
              () -> assertEquals(bean.getLastName(), customer.getLastName(), "Lastnames are differents"),
              () -> assertEquals(bean.getEmail(), customer.getEmail(), "Email addresses are differents"),
              () -> assertEquals(bean.getBirthDate(), customer.getBirthDate(), "Birthdates are differents"),
              () -> compareAddress(bean.getAddress(), customer.getAddress()),
              () -> compareRPhones(bean.getPhones(), customer.getPhones())
        );
  }

  /**
   * Compare REST/bean address
   *
   * @param bean the bean
   * @param address the REST address
   */
  protected void compareAddress(final AddressDTO bean, final com.github.vlachenal.webservice.bench.rest.api.model.Address address) {
    LOG.info("Address lines: bean = {} ; REST = {}", bean.getLines(), address.getLines());
    LOG.info("Address ZIP code: bean = {} ; REST = {}", bean.getZipCode(), address.getZipCode());
    LOG.info("Address city: bean = {} ; REST = {}", bean.getCity(), address.getCity());
    LOG.info("Address country: bean = {} ; REST = {}", bean.getCountry(), address.getCountry());
    assertAll("Compare bean address to REST model",
              () -> assertEquals(bean.getLines(), address.getLines(), "Lines are different"),
              () -> assertEquals(bean.getZipCode(), address.getZipCode(), "ZIP codes are different"),
              () -> assertEquals(bean.getCity(), address.getCity(), "Cities are different"),
              () -> assertEquals(bean.getCountry(), address.getCountry(), "Countries are different")
        );
  }

  /**
   * Compare REST/bean phones
   *
   * @param beans the bean list
   * @param phones the REST phone list
   */
  protected void compareRPhones(final List<PhoneDTO> beans, final List<com.github.vlachenal.webservice.bench.rest.api.model.Phone> phones) {
    if(beans != null) {
      assertNotNull(phones, "REST phone list is null");
      assertEquals(beans.size(), phones.size(), "Number of phones is different");
      for(int i = 0 ; i < beans.size() ; ++i) {
        comparePhone(beans.get(i), phones.get(i));
      }
    }
  }

  /**
   * Compare REST/bean phone
   *
   * @param bean the bean
   * @param phone the REST phone
   */
  protected void comparePhone(final PhoneDTO bean, final com.github.vlachenal.webservice.bench.rest.api.model.Phone phone) {
    LOG.info("Phone number: bean = {} ; REST = {}", bean.getNumber(), phone.getNumber());
    LOG.info("Phone type: bean = {} ; REST = {}", bean.getType(), phone.getType());
    assertEquals(bean.getNumber(), phone.getNumber(), "Phone numbers are different");
    switch(bean.getType()) {
      case LANDLINE:
        assertEquals(com.github.vlachenal.webservice.bench.rest.api.model.Phone.Type.LANDLINE, phone.getType(), "Phone types are different");
        break;
      case MOBILE:
        assertEquals(com.github.vlachenal.webservice.bench.rest.api.model.Phone.Type.MOBILE, phone.getType(), "Phone types are different");
        break;
      default:
        fail("Unexpected type: " + bean.getType());
    }
  }

  /**
   * Compare Protocol Buffer/bean customer
   *
   * @param bean the bean
   * @param customer the REST customer
   */
  protected void compareCustomer(final CustomerDTO bean, final com.github.vlachenal.webservice.bench.protobuf.api.Customer customer) {
    LOG.info("Customer id: bean = {} ; Protocol Buffer = {}", bean.getId(), customer.getId());
    LOG.info("Customer first name: bean = {} ; Protocol Buffer = {}", bean.getFirstName(), customer.getFirstName());
    LOG.info("Customer last name: bean = {} ; Protocol Buffer = {}", bean.getLastName(), customer.getLastName());
    LOG.info("Customer email: bean = {} ; Protocol Buffer = {}", bean.getEmail(), customer.getEmail());
    LOG.info("Customer birthdate: bean = {} ; Protocol Buffer = {}", bean.getBirthDate(), customer.getBirthDate());
    assertAll("Compare bean customer to protobuf",
              () -> assertEquals(bean.getId(), customer.getId(), "Identifiers are differents"),
              () -> assertEquals(bean.getFirstName(), customer.getFirstName(), "Firstnames are differents"),
              () -> assertEquals(bean.getLastName(), customer.getLastName(), "Lastnames are differents"),
              () -> assertEquals(bean.getEmail(), customer.getEmail(), "Email addresses are differents"),
              () -> assertEquals(bean.getBirthDate(), new Date(customer.getBirthDate()), "Birthdates are differents"),
              () -> compareAddress(bean.getAddress(), customer.getAddress()),
              () -> comparePbPhones(bean.getPhones(), customer.getPhonesList())
        );
  }

  /**
   * Compare Protocol Buffer/bean address
   *
   * @param bean the bean
   * @param address the Protocol Buffer address
   */
  protected void compareAddress(final AddressDTO bean, final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address address) {
    LOG.info("Address lines: bean = {} ; Protocol Buffer = {}", bean.getLines(), address.getLinesList());
    LOG.info("Address ZIP code: bean = {} ; Protocol Buffer = {}", bean.getZipCode(), address.getZipCode());
    LOG.info("Address city: bean = {} ; Protocol Buffer = {}", bean.getCity(), address.getCity());
    LOG.info("Address country: bean = {} ; Protocol Buffer = {}", bean.getCountry(), address.getCountry());
    assertAll("Compare bean address to protobuf",
              () -> assertEquals(bean.getLines(), address.getLinesList(), "Lines are different"),
              () -> assertEquals(bean.getZipCode(), address.getZipCode(), "ZIP codes are different"),
              () -> assertEquals(bean.getCity(), address.getCity(), "Cities are different"),
              () -> assertEquals(bean.getCountry(), address.getCountry(), "Countries are different")
        );
  }

  /**
   * Compare Protocol Buffer/bean phones
   *
   * @param beans the bean list
   * @param phones the Protocol Buffer phone list
   */
  protected void comparePbPhones(final List<PhoneDTO> beans, final List<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone> phones) {
    if(beans != null) {
      assertNotNull(phones, "Protocol Buffer phone list is null");
      assertEquals(beans.size(), phones.size(), "Number of phones is different");
      for(int i = 0 ; i < beans.size() ; ++i) {
        comparePhone(beans.get(i), phones.get(i));
      }
    }
  }

  /**
   * Compare Protocol Buffer/bean phone
   *
   * @param bean the bean
   * @param phone the Protocol Buffer phone
   */
  protected void comparePhone(final PhoneDTO bean, final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone phone) {
    LOG.info("Phone number: bean = {} ; Protocol Buffer = {}", bean.getNumber(), phone.getNumber());
    assertEquals(bean.getNumber(), phone.getNumber());
    LOG.info("Phone type: bean = {} ; Protocol Buffer = {}", bean.getType(), phone.getType());
    switch(bean.getType()) {
      case LANDLINE:
        assertEquals(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType.LANDLINE, phone.getType());
        break;
      case MOBILE:
        assertEquals(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType.MOBILE, phone.getType());
        break;
      default:
        fail("Unexpected type: " + bean.getType());
    }
  }
  // Methods -

}
