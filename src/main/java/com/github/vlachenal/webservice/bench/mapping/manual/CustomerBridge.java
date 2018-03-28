/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.github.vlachenal.webservice.bench.dto.CustomerBean;
import com.google.protobuf.Descriptors.Descriptor;


/**
 * Customer conversion utility class
 *
 * @author Vincent Lachenal
 */
public final class CustomerBridge {

  // Attributes +
  /** Protocol Buffer customer descriptor */
  private static final Descriptor CUSTOMER_DESC = com.github.vlachenal.webservice.bench.protobuf.api.Customer.getDescriptor();
  // Attributes -


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
  public static CustomerBean fromRest(final com.github.vlachenal.webservice.bench.rest.api.bean.Customer customer) {
    CustomerBean bean = null;
    if(customer != null) {
      bean = new CustomerBean();
      bean.setId(customer.getId());
      bean.setFirstName(customer.getFirstName());
      bean.setLastName(customer.getLastName());
      bean.setEmail(customer.getEmail());
      bean.setBirthDate(customer.getBirthDate());
      bean.setAddress(AddressBridge.fromRest(customer.getAddress()));
      bean.setPhones(PhoneBridge.fromRest(customer.getPhones()));
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
      customers = new ArrayList<>();
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
  public static CustomerBean fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.Customer customer) {
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
      bean.setAddress(AddressBridge.fromThrift(customer.getAddress()));
      bean.setPhones(PhoneBridge.fromThrift(customer.getPhones()));
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
      customers = new ArrayList<>();
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
  public static CustomerBean fromSoap(final com.github.vlachenal.webservice.bench.soap.api.Customer customer) {
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
      bean.setAddress(AddressBridge.fromSoap(customer.getAddress()));
      bean.setPhones(PhoneBridge.fromSoap(customer.getPhones()));
    }
    return bean;
  }

  /**
   * Convert customer beans to SOAP structures
   *
   * @param bean the beans to convert
   *
   * @return the SOAP structures
   */
  public static List<com.github.vlachenal.webservice.bench.soap.api.Customer> toSoap(final List<CustomerBean> bean) {
    List<com.github.vlachenal.webservice.bench.soap.api.Customer> customers = null;
    if(bean != null) {
      customers = new ArrayList<>();
      for(final CustomerBean customer : bean) {
        final com.github.vlachenal.webservice.bench.soap.api.Customer json = toSoap(customer);
        if(json != null) {
          customers.add(json);
        }
      }
    }
    return customers;
  }

  /**
   * Convert customer bean to Protocol buffer structure
   *
   * @param bean the bean to convert
   *
   * @return the Protocol buffer structure
   */
  public static com.github.vlachenal.webservice.bench.protobuf.api.Customer toProtobuf(final CustomerBean bean) {
    com.github.vlachenal.webservice.bench.protobuf.api.Customer customer = null;
    if(bean != null) {
      final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder builder = com.github.vlachenal.webservice.bench.protobuf.api.Customer.newBuilder();
      if(bean.getId() != null) {
        builder.setId(bean.getId());
      }
      if(bean.getFirstName() != null) {
        builder.setFirstName(bean.getFirstName());
      }
      if(bean.getLastName() != null) {
        builder.setLastName(bean.getLastName());
      }
      if(bean.getEmail() != null) {
        builder.setEmail(bean.getEmail());
      }
      if(bean.getBirthDate() != null) {
        builder.setBirthDate(bean.getBirthDate().getTime());
      }
      if(bean.getAddress() != null) {
        builder.setAddress(AddressBridge.toProtobuf(bean.getAddress()));
      }
      if(bean.getPhones() != null) {
        builder.addAllPhones(PhoneBridge.toProtobuf(bean.getPhones()));
      }
      customer = builder.build();
    }
    return customer;
  }

  /**
   * Convert customer Protocol buffer structure to bean
   *
   * @param bean the Protocol buffer structure to convert
   *
   * @return the bean
   */
  public static CustomerBean fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.Customer customer) {
    CustomerBean bean = null;
    if(customer != null) {
      bean = new CustomerBean();
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.ID_FIELD_NUMBER))) {
        bean.setId(customer.getId());
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.FIRSTNAME_FIELD_NUMBER))) {
        bean.setFirstName(customer.getFirstName());
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.LASTNAME_FIELD_NUMBER))) {
        bean.setLastName(customer.getLastName());
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.EMAIL_FIELD_NUMBER))) {
        bean.setEmail(customer.getEmail());
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.BIRTHDATE_FIELD_NUMBER))) {
        bean.setBirthDate(new Date(customer.getBirthDate()));
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.ADDRESS_FIELD_NUMBER))) {
        bean.setAddress(AddressBridge.fromProtobuf(customer.getAddress()));
      }
      if(customer.getPhonesCount() > 0) {
        bean.setPhones(PhoneBridge.fromProtobuf(customer.getPhonesList()));
      }
    }
    return bean;
  }

  /**
   * Convert customer beans to Protocol buffer structures
   *
   * @param bean the beans to convert
   *
   * @return the Protocol buffer structures
   */
  public static List<com.github.vlachenal.webservice.bench.protobuf.api.Customer> toProtobuf(final List<CustomerBean> bean) {
    List<com.github.vlachenal.webservice.bench.protobuf.api.Customer> customers = null;
    if(bean != null) {
      customers = new ArrayList<>();
      for(final CustomerBean customer : bean) {
        final com.github.vlachenal.webservice.bench.protobuf.api.Customer json = toProtobuf(customer);
        if(json != null) {
          customers.add(json);
        }
      }
    }
    return customers;
  }
  // Methods -

}
