/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.dozer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import com.github.vlachenal.webservice.bench.dao.bean.AddressBean;
import com.github.vlachenal.webservice.bench.dao.bean.CustomerBean;
import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean;


/**
 * Abstract mapping unit tests
 *
 * @author Vincent Lachenal
 */
public abstract class AbstractMappingTest {

  // Methods +
  /**
   * Compare SOAP/bean customer
   *
   * @param bean the bean
   * @param customer the SOAP customer
   */
  protected void compareCustomer(final CustomerBean bean, final com.github.vlachenal.webservice.bench.soap.api.Customer customer) {
    assertEquals("Identifiers are differents", bean.getId(), customer.getId());
    assertEquals("Firstnames are differents", bean.getFirstName(), customer.getFirstName());
    assertEquals("Lastnames are differents", bean.getLastName(), customer.getLastName());
    assertEquals("Email addresses are differents", bean.getEmail(), customer.getEmail());
    assertEquals("Birthdates are differents", bean.getBirthDate(), customer.getBirthDate().toGregorianCalendar().getTime());
    compareAddress(bean.getAddress(), customer.getAddress());
    compareSPhones(bean.getPhones(), customer.getPhones());
  }

  /**
   * Compare SOAP/bean address
   *
   * @param bean the bean
   * @param address the SOAP address
   */
  protected void compareAddress(final AddressBean bean, final com.github.vlachenal.webservice.bench.soap.api.Address address) {
    assertEquals("Lines are different", bean.getLines(), address.getLines());
    assertEquals("ZIP codes are different", bean.getZipCode(), address.getZipCode());
    assertEquals("Cities are different", bean.getCity(), address.getCity());
    assertEquals("Countries are different", bean.getCountry(), address.getCountry());
  }

  /**
   * Compare SOAP/bean phones
   *
   * @param beans the bean list
   * @param phones the SOAP phone list
   */
  protected void compareSPhones(final List<PhoneBean> beans, final List<com.github.vlachenal.webservice.bench.soap.api.Phone> phones) {
    if(beans != null) {
      assertNotNull("SOAP phone list is null", phones);
      assertEquals("Number of phones is different", beans.size(), phones.size());
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
  protected void comparePhone(final PhoneBean bean, final com.github.vlachenal.webservice.bench.soap.api.Phone phone) {
    assertEquals(bean.getNumber(), phone.getNumber());
    switch(bean.getType()) {
      case LANDLINE:
        assertEquals(com.github.vlachenal.webservice.bench.soap.api.PhoneType.LANDLINE, phone.getPhoneType());
        break;
      case MOBILE:
        assertEquals(com.github.vlachenal.webservice.bench.soap.api.PhoneType.MOBILE, phone.getPhoneType());
        break;
      default:
        fail("Unexpected type: " + bean.getType());
    }
  }

  /**
   * Compare Thrift/bean customer
   *
   * @param bean the bean
   * @param customer the Thrift customer
   */
  protected void compareCustomer(final CustomerBean bean, final com.github.vlachenal.webservice.bench.thrift.api.Customer customer) {
    assertEquals("Identifiers are differents", bean.getId(), customer.getId());
    assertEquals("Firstnames are differents", bean.getFirstName(), customer.getFirstName());
    assertEquals("Lastnames are differents", bean.getLastName(), customer.getLastName());
    assertEquals("Email addresses are differents", bean.getEmail(), customer.getEmail());
    assertEquals("Birthdates are differents", bean.getBirthDate(), new Date(customer.getBirthDate()));
    compareAddress(bean.getAddress(), customer.getAddress());
    compareTPhones(bean.getPhones(), customer.getPhones());
  }

  /**
   * Compare Thrift/bean address
   *
   * @param bean the bean
   * @param address the SOAP address
   */
  protected void compareAddress(final AddressBean bean, final com.github.vlachenal.webservice.bench.thrift.api.Address address) {
    assertEquals("Lines are different", bean.getLines(), address.getLines());
    assertEquals("ZIP codes are different", bean.getZipCode(), address.getZipCode());
    assertEquals("Cities are different", bean.getCity(), address.getCity());
    assertEquals("Countries are different", bean.getCountry(), address.getCountry());
  }

  /**
   * Compare SOAP/bean phones
   *
   * @param beans the bean list
   * @param phones the SOAP phone list
   */
  protected void compareTPhones(final List<PhoneBean> beans, final List<com.github.vlachenal.webservice.bench.thrift.api.Phone> phones) {
    if(beans != null) {
      assertNotNull("SOAP phone list is null", phones);
      assertEquals("Number of phones is different", beans.size(), phones.size());
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
  protected void comparePhone(final PhoneBean bean, final com.github.vlachenal.webservice.bench.thrift.api.Phone phone) {
    assertEquals(bean.getNumber(), phone.getNumber());
    switch(bean.getType()) {
      case LANDLINE:
        assertEquals(com.github.vlachenal.webservice.bench.thrift.api.PhoneType.LANDLINE, phone.getType());
        break;
      case MOBILE:
        assertEquals(com.github.vlachenal.webservice.bench.thrift.api.PhoneType.MOBILE, phone.getType());
        break;
      default:
        fail("Unexpected type: " + bean.getType());
    }
  }

  /**
   * Compare Thrift/bean customer
   *
   * @param bean the bean
   * @param customer the Thrift customer
   */
  protected void compareCustomer(final CustomerBean bean, final com.github.vlachenal.webservice.bench.rest.api.bean.Customer customer) {
    assertEquals("Identifiers are differents", bean.getId(), customer.getId());
    assertEquals("Firstnames are differents", bean.getFirstName(), customer.getFirstName());
    assertEquals("Lastnames are differents", bean.getLastName(), customer.getLastName());
    assertEquals("Email addresses are differents", bean.getEmail(), customer.getEmail());
    assertEquals("Birthdates are differents", bean.getBirthDate(), customer.getBirthDate());
    compareAddress(bean.getAddress(), customer.getAddress());
    compareRPhones(bean.getPhones(), customer.getPhones());
  }

  /**
   * Compare Thrift/bean address
   *
   * @param bean the bean
   * @param address the SOAP address
   */
  protected void compareAddress(final AddressBean bean, final com.github.vlachenal.webservice.bench.rest.api.bean.Address address) {
    assertEquals("Lines are different", bean.getLines(), address.getLines());
    assertEquals("ZIP codes are different", bean.getZipCode(), address.getZipCode());
    assertEquals("Cities are different", bean.getCity(), address.getCity());
    assertEquals("Countries are different", bean.getCountry(), address.getCountry());
  }

  /**
   * Compare SOAP/bean phones
   *
   * @param beans the bean list
   * @param phones the SOAP phone list
   */
  protected void compareRPhones(final List<PhoneBean> beans, final List<com.github.vlachenal.webservice.bench.rest.api.bean.Phone> phones) {
    if(beans != null) {
      assertNotNull("SOAP phone list is null", phones);
      assertEquals("Number of phones is different", beans.size(), phones.size());
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
  protected void comparePhone(final PhoneBean bean, final com.github.vlachenal.webservice.bench.rest.api.bean.Phone phone) {
    assertEquals(bean.getNumber(), phone.getNumber());
    switch(bean.getType()) {
      case LANDLINE:
        assertEquals(com.github.vlachenal.webservice.bench.rest.api.bean.Phone.Type.LANDLINE, phone.getType());
        break;
      case MOBILE:
        assertEquals(com.github.vlachenal.webservice.bench.rest.api.bean.Phone.Type.MOBILE, phone.getType());
        break;
      default:
        fail("Unexpected type: " + bean.getType());
    }
  }
  // Methods -

}
