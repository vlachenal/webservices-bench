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

import com.github.vlachenal.webservice.bench.dto.CustomerDTO;
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
   * Convert customer DTO to REST/JSON structure
   *
   * @param dto the DTO to convert
   *
   * @return the JSON structure
   */
  public static com.github.vlachenal.webservice.bench.rest.api.bean.Customer toRest(final CustomerDTO dto) {
    com.github.vlachenal.webservice.bench.rest.api.bean.Customer customer = null;
    if(dto != null) {
      customer = new com.github.vlachenal.webservice.bench.rest.api.bean.Customer();
      customer.setId(dto.getId());
      customer.setFirstName(dto.getFirstName());
      customer.setLastName(dto.getLastName());
      customer.setEmail(dto.getEmail());
      customer.setBirthDate(dto.getBirthDate());
      customer.setAddress(AddressBridge.toRest(dto.getAddress()));
      customer.setPhones(PhoneBridge.toRest(dto.getPhones()));
    }
    return customer;
  }

  /**
   * Convert customer REST/JSON structure to DTO
   *
   * @param customer the REST/JSON structure to convert
   *
   * @return the DTO
   */
  public static CustomerDTO fromRest(final com.github.vlachenal.webservice.bench.rest.api.bean.Customer customer) {
    CustomerDTO dto = null;
    if(customer != null) {
      dto = new CustomerDTO();
      dto.setId(customer.getId());
      dto.setFirstName(customer.getFirstName());
      dto.setLastName(customer.getLastName());
      dto.setEmail(customer.getEmail());
      dto.setBirthDate(customer.getBirthDate());
      dto.setAddress(AddressBridge.fromRest(customer.getAddress()));
      dto.setPhones(PhoneBridge.fromRest(customer.getPhones()));
    }
    return dto;
  }

  /**
   * Convert customer DTOs to REST/JSON structures
   *
   * @param dto the DTOs to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.rest.api.bean.Customer> toRest(final List<CustomerDTO> dto) {
    List<com.github.vlachenal.webservice.bench.rest.api.bean.Customer> customers = null;
    if(dto != null) {
      customers = new ArrayList<>();
      for(final CustomerDTO customer : dto) {
        final com.github.vlachenal.webservice.bench.rest.api.bean.Customer json = toRest(customer);
        if(json != null) {
          customers.add(json);
        }
      }
    }
    return customers;
  }

  /**
   * Convert customer DTO to Thift structure
   *
   * @param dto the DTO to convert
   *
   * @return the Thrift structure
   */
  public static com.github.vlachenal.webservice.bench.thrift.api.Customer toThrift(final CustomerDTO dto) {
    com.github.vlachenal.webservice.bench.thrift.api.Customer customer = null;
    if(dto != null) {
      customer = new com.github.vlachenal.webservice.bench.thrift.api.Customer();
      customer.setId(dto.getId());
      customer.setFirstName(dto.getFirstName());
      customer.setLastName(dto.getLastName());
      customer.setEmail(dto.getEmail());
      if(dto.getBirthDate() != null) {
        customer.setBirthDate(dto.getBirthDate().getTime());
      }
      customer.setAddress(AddressBridge.toThrift(dto.getAddress()));
      customer.setPhones(PhoneBridge.toThrift(dto.getPhones()));
    }
    return customer;
  }

  /**
   * Convert customer Thift structure to DTO
   *
   * @param customer the Thift structure to convert
   *
   * @return the DTO
   */
  public static CustomerDTO fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.Customer customer) {
    CustomerDTO dto = null;
    if(customer != null) {
      dto = new CustomerDTO();
      dto.setId(customer.getId());
      dto.setFirstName(customer.getFirstName());
      dto.setLastName(customer.getLastName());
      dto.setEmail(customer.getEmail());
      if(customer.getBirthDate() != 0) {
        dto.setBirthDate(new Date(customer.getBirthDate()));
      }
      dto.setAddress(AddressBridge.fromThrift(customer.getAddress()));
      dto.setPhones(PhoneBridge.fromThrift(customer.getPhones()));
    }
    return dto;
  }

  /**
   * Convert customer DTOs to REST/JSON structures
   *
   * @param dto the DTOs to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.thrift.api.Customer> toThrift(final List<CustomerDTO> dto) {
    List<com.github.vlachenal.webservice.bench.thrift.api.Customer> customers = null;
    if(dto != null) {
      customers = new ArrayList<>();
      for(final CustomerDTO customer : dto) {
        final com.github.vlachenal.webservice.bench.thrift.api.Customer thrift = toThrift(customer);
        if(thrift != null) {
          customers.add(thrift);
        }
      }
    }
    return customers;
  }

  /**
   * Convert customer DTO to SOAP structure
   *
   * @param dto the DTO to convert
   *
   * @return the SOAP structure
   */
  public static com.github.vlachenal.webservice.bench.soap.api.Customer toSoap(final CustomerDTO dto) {
    com.github.vlachenal.webservice.bench.soap.api.Customer customer = null;
    if(dto != null) {
      customer = new com.github.vlachenal.webservice.bench.soap.api.Customer();
      customer.setId(dto.getId());
      customer.setFirstName(dto.getFirstName());
      customer.setLastName(dto.getLastName());
      customer.setEmail(dto.getEmail());
      if(dto.getBirthDate() != null) {
        final GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dto.getBirthDate());
        try {
          customer.setBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
        } catch(final DatatypeConfigurationException e) {
          // Nothing to do
        }
      }
      customer.setAddress(AddressBridge.toSoap(dto.getAddress()));
      if(dto.getPhones() != null) {
        customer.getPhones().addAll(PhoneBridge.toSoap(dto.getPhones()));
      }
    }
    return customer;
  }

  /**
   * Convert customer SOAP structure to DTO
   *
   * @param customer the SOAP structure to convert
   *
   * @return the DTO
   */
  public static CustomerDTO fromSoap(final com.github.vlachenal.webservice.bench.soap.api.Customer customer) {
    CustomerDTO dto = null;
    if(customer != null) {
      dto = new CustomerDTO();
      dto.setId(customer.getId());
      dto.setFirstName(customer.getFirstName());
      dto.setLastName(customer.getLastName());
      dto.setEmail(customer.getEmail());
      if(customer.getBirthDate() != null) {
        dto.setBirthDate(customer.getBirthDate().toGregorianCalendar().getTime());
      }
      dto.setAddress(AddressBridge.fromSoap(customer.getAddress()));
      dto.setPhones(PhoneBridge.fromSoap(customer.getPhones()));
    }
    return dto;
  }

  /**
   * Convert customer DTOs to SOAP structures
   *
   * @param dto the DTOs to convert
   *
   * @return the SOAP structures
   */
  public static List<com.github.vlachenal.webservice.bench.soap.api.Customer> toSoap(final List<CustomerDTO> dto) {
    List<com.github.vlachenal.webservice.bench.soap.api.Customer> customers = null;
    if(dto != null) {
      customers = new ArrayList<>();
      for(final CustomerDTO customer : dto) {
        final com.github.vlachenal.webservice.bench.soap.api.Customer json = toSoap(customer);
        if(json != null) {
          customers.add(json);
        }
      }
    }
    return customers;
  }

  /**
   * Convert customer DTO to Protocol buffer structure
   *
   * @param dto the DTO to convert
   *
   * @return the Protocol buffer structure
   */
  public static com.github.vlachenal.webservice.bench.protobuf.api.Customer toProtobuf(final CustomerDTO dto) {
    com.github.vlachenal.webservice.bench.protobuf.api.Customer customer = null;
    if(dto != null) {
      final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder builder = com.github.vlachenal.webservice.bench.protobuf.api.Customer.newBuilder();
      if(dto.getId() != null) {
        builder.setId(dto.getId());
      }
      if(dto.getFirstName() != null) {
        builder.setFirstName(dto.getFirstName());
      }
      if(dto.getLastName() != null) {
        builder.setLastName(dto.getLastName());
      }
      if(dto.getEmail() != null) {
        builder.setEmail(dto.getEmail());
      }
      if(dto.getBirthDate() != null) {
        builder.setBirthDate(dto.getBirthDate().getTime());
      }
      if(dto.getAddress() != null) {
        builder.setAddress(AddressBridge.toProtobuf(dto.getAddress()));
      }
      if(dto.getPhones() != null) {
        builder.addAllPhones(PhoneBridge.toProtobuf(dto.getPhones()));
      }
      customer = builder.build();
    }
    return customer;
  }

  /**
   * Convert customer Protocol buffer structure to DTO
   *
   * @param customer the Protocol buffer structure to convert
   *
   * @return the DTO
   */
  public static CustomerDTO fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.Customer customer) {
    CustomerDTO dto = null;
    if(customer != null) {
      dto = new CustomerDTO();
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.ID_FIELD_NUMBER))) {
        dto.setId(customer.getId());
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.FIRSTNAME_FIELD_NUMBER))) {
        dto.setFirstName(customer.getFirstName());
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.LASTNAME_FIELD_NUMBER))) {
        dto.setLastName(customer.getLastName());
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.EMAIL_FIELD_NUMBER))) {
        dto.setEmail(customer.getEmail());
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.BIRTHDATE_FIELD_NUMBER))) {
        dto.setBirthDate(new Date(customer.getBirthDate()));
      }
      if(customer.hasField(CUSTOMER_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.ADDRESS_FIELD_NUMBER))) {
        dto.setAddress(AddressBridge.fromProtobuf(customer.getAddress()));
      }
      if(customer.getPhonesCount() > 0) {
        dto.setPhones(PhoneBridge.fromProtobuf(customer.getPhonesList()));
      }
    }
    return dto;
  }

  /**
   * Convert customer DTOs to Protocol buffer structures
   *
   * @param dto the DTOs to convert
   *
   * @return the Protocol buffer structures
   */
  public static List<com.github.vlachenal.webservice.bench.protobuf.api.Customer> toProtobuf(final List<CustomerDTO> dto) {
    List<com.github.vlachenal.webservice.bench.protobuf.api.Customer> customers = null;
    if(dto != null) {
      customers = new ArrayList<>();
      for(final CustomerDTO customer : dto) {
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
