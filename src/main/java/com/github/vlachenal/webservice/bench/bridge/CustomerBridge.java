/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.bridge;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.github.vlachenal.webservice.bench.dao.bean.CustomerBean;


/**
 * Customer conversion utility class
 *
 * @author Vincent Lachenal
 */
public final class CustomerBridge {

  // Constructors +
  /**
   * {@link CustomerBridge} private construtor
   */
  private CustomerBridge() {
    // Nothing to do
  }
  // Constructors -


  // Methods +
  /**
   * Convert customer bean to REST/JSON structure
   *
   * @param bean the bean to convert
   *
   * @return the JSON structure
   */
  public static com.github.vlachenal.webservice.bench.rest.api.bean.Customer toRest(final CustomerBean bean) {
    com.github.vlachenal.webservice.bench.rest.api.bean.Customer customer = null;
    if(bean != null) {
      customer = new com.github.vlachenal.webservice.bench.rest.api.bean.Customer();
      customer.setId(bean.getId());
      customer.setFirstName(bean.getFirstName());
      customer.setLastName(bean.getLastName());
      customer.setEmail(bean.getEmail());
      customer.setBirthDate(bean.getBirthDate());
      customer.setAddress(AddressBridge.toRest(bean.getAddress()));
      customer.setPhones(PhoneBridge.toRest(bean.getPhones()));
    }
    return customer;
  }

  /**
   * Convert customer REST/JSON structure to bean
   *
   * @param bean the REST/JSON structure to convert
   *
   * @return the bean
   */
  public static CustomerBean toBean(final com.github.vlachenal.webservice.bench.rest.api.bean.Customer customer) {
    CustomerBean bean = null;
    if(customer != null) {
      bean = new CustomerBean();
      bean.setId(customer.getId());
      bean.setFirstName(customer.getFirstName());
      bean.setLastName(customer.getLastName());
      bean.setEmail(customer.getEmail());
      bean.setBirthDate(customer.getBirthDate());
      bean.setAddress(AddressBridge.toBean(customer.getAddress()));
      bean.setPhones(PhoneBridge.toBeanRList(customer.getPhones()));
    }
    return bean;
  }

  /**
   * Convert customer beans to REST/JSON structures
   *
   * @param bean the beans to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.rest.api.bean.Customer> toRest(final List<CustomerBean> bean) {
    List<com.github.vlachenal.webservice.bench.rest.api.bean.Customer> customers = null;
    if(bean != null) {
      customers = new ArrayList<com.github.vlachenal.webservice.bench.rest.api.bean.Customer>();
      for(final CustomerBean customer : bean) {
        final com.github.vlachenal.webservice.bench.rest.api.bean.Customer json = toRest(customer);
        if(json != null) {
          customers.add(json);
        }
      }
    }
    return customers;
  }

  /**
   * Convert customer bean to Thift structure
   *
   * @param bean the bean to convert
   *
   * @return the Thrift structure
   */
  public static com.github.vlachenal.webservice.bench.thrift.api.Customer toThrift(final CustomerBean bean) {
    com.github.vlachenal.webservice.bench.thrift.api.Customer customer = null;
    if(bean != null) {
      customer = new com.github.vlachenal.webservice.bench.thrift.api.Customer();
      customer.setId(bean.getId());
      customer.setFirstName(bean.getFirstName());
      customer.setLastName(bean.getLastName());
      customer.setEmail(bean.getEmail());
      if(bean.getBirthDate() != null) {
        customer.setBirthDate(bean.getBirthDate().getTime());
      }
      customer.setAddress(AddressBridge.toThrift(bean.getAddress()));
      customer.setPhones(PhoneBridge.toThrift(bean.getPhones()));
    }
    return customer;
  }

  /**
   * Convert customer Thift structure to bean
   *
   * @param bean the Thift structure to convert
   *
   * @return the bean
   */
  public static CustomerBean toBean(final com.github.vlachenal.webservice.bench.thrift.api.Customer customer) {
    CustomerBean bean = null;
    if(customer != null) {
      bean = new CustomerBean();
      bean.setId(customer.getId());
      bean.setFirstName(customer.getFirstName());
      bean.setLastName(customer.getLastName());
      bean.setEmail(customer.getEmail());
      if(customer.getBirthDate() != 0) {
        bean.setBirthDate(new Date(customer.getBirthDate()));
      }
      bean.setAddress(AddressBridge.toBean(customer.getAddress()));
      bean.setPhones(PhoneBridge.toBeanTList(customer.getPhones()));
    }
    return bean;
  }

  /**
   * Convert customer beans to REST/JSON structures
   *
   * @param bean the beans to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.thrift.api.Customer> toThrift(final List<CustomerBean> bean) {
    List<com.github.vlachenal.webservice.bench.thrift.api.Customer> customers = null;
    if(bean != null) {
      customers = new ArrayList<com.github.vlachenal.webservice.bench.thrift.api.Customer>();
      for(final CustomerBean customer : bean) {
        final com.github.vlachenal.webservice.bench.thrift.api.Customer thrift = toThrift(customer);
        if(thrift != null) {
          customers.add(thrift);
        }
      }
    }
    return customers;
  }

  /**
   * Convert customer bean to SOAP structure
   *
   * @param bean the bean to convert
   *
   * @return the SOAP structure
   */
  public static com.github.vlachenal.webservice.bench.soap.api.Customer toSoap(final CustomerBean bean) {
    com.github.vlachenal.webservice.bench.soap.api.Customer customer = null;
    if(bean != null) {
      customer = new com.github.vlachenal.webservice.bench.soap.api.Customer();
      customer.setId(bean.getId());
      customer.setFirstName(bean.getFirstName());
      customer.setLastName(bean.getLastName());
      customer.setEmail(bean.getEmail());
      if(bean.getBirthDate() != null) {
        final GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(bean.getBirthDate());
        try {
          customer.setBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
        } catch(final DatatypeConfigurationException e) {
          // Nothing to do
        }
      }
      customer.setAddress(AddressBridge.toSoap(bean.getAddress()));
      if(bean.getPhones() != null) {
        customer.getPhones().addAll(PhoneBridge.toSoap(bean.getPhones()));
      }
    }
    return customer;
  }

  /**
   * Convert customer SOAP structure to bean
   *
   * @param bean the SOAP structure to convert
   *
   * @return the bean
   */
  public static CustomerBean toBean(final com.github.vlachenal.webservice.bench.soap.api.Customer customer) {
    CustomerBean bean = null;
    if(customer != null) {
      bean = new CustomerBean();
      bean.setId(customer.getId());
      bean.setFirstName(customer.getFirstName());
      bean.setLastName(customer.getLastName());
      bean.setEmail(customer.getEmail());
      if(customer.getBirthDate() != null) {
        bean.setBirthDate(customer.getBirthDate().toGregorianCalendar().getTime());
      }
      bean.setAddress(AddressBridge.toBean(customer.getAddress()));
      bean.setPhones(PhoneBridge.toBeanSList(customer.getPhones()));
    }
    return bean;
  }

  /**
   * Convert customer beans to REST/JSON structures
   *
   * @param bean the beans to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.soap.api.Customer> toSoap(final List<CustomerBean> bean) {
    List<com.github.vlachenal.webservice.bench.soap.api.Customer> customers = null;
    if(bean != null) {
      customers = new ArrayList<com.github.vlachenal.webservice.bench.soap.api.Customer>();
      for(final CustomerBean customer : bean) {
        final com.github.vlachenal.webservice.bench.soap.api.Customer json = toSoap(customer);
        if(json != null) {
          customers.add(json);
        }
      }
    }
    return customers;
  }
  // Methods -

}
