/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.bridge;

import com.github.vlachenal.webservice.bench.dao.bean.AddressBean;


/**
 * Address conversion utility class
 *
 * @author Vincent Lachenal
 */
public final class AddressBridge {

  // Constructors +
  /**
   * {@link AddressBridge} private construtor
   */
  private AddressBridge() {
    // Nothing to do
  }
  // Constructors -


  // Methods +
  /**
   * Convert addres bean to REST/JSON structure
   *
   * @param bean the bean to convert
   *
   * @return the JSON structure
   */
  public static com.github.vlachenal.webservice.bench.rest.api.bean.Address toRest(final AddressBean bean) {
    com.github.vlachenal.webservice.bench.rest.api.bean.Address address = null;
    if(bean != null) {
      address = new com.github.vlachenal.webservice.bench.rest.api.bean.Address();
      address.setLines(bean.getLines());
      address.setZipCode(bean.getZipCode());
      address.setCity(bean.getCity());
      address.setCountry(bean.getCountry());
    }
    return address;
  }

  /**
   * Convert REST address to bean
   *
   * @param address the REST address
   *
   * @return the bean
   */
  public static AddressBean toBean(final com.github.vlachenal.webservice.bench.rest.api.bean.Address address) {
    AddressBean bean = null;
    if(address != null) {
      bean = new AddressBean();
      bean.setLines(address.getLines());
      bean.setZipCode(address.getZipCode());
      bean.setCity(address.getCity());
      bean.setCountry(address.getCountry());
    }
    return bean;
  }

  /**
   * Convert address bean to Thift structure
   *
   * @param bean the bean to convert
   *
   * @return the Thrift structure
   */
  public static com.github.vlachenal.webservice.bench.thrift.api.Address toThrift(final AddressBean bean) {
    com.github.vlachenal.webservice.bench.thrift.api.Address address = null;
    if(bean != null) {
      address = new com.github.vlachenal.webservice.bench.thrift.api.Address();
      address.setLines(bean.getLines());
      address.setZipCode(bean.getZipCode());
      address.setCity(bean.getCity());
      address.setCountry(bean.getCountry());
    }
    return address;
  }

  /**
   * Convert Thrift address to bean
   *
   * @param address the Thrift address
   *
   * @return the bean
   */
  public static AddressBean toBean(final com.github.vlachenal.webservice.bench.thrift.api.Address address) {
    AddressBean bean = null;
    if(address != null) {
      bean = new AddressBean();
      bean.setLines(address.getLines());
      bean.setZipCode(address.getZipCode());
      bean.setCity(address.getCity());
      bean.setCountry(address.getCountry());
    }
    return bean;
  }

  /**
   * Convert addres bean to SOAP structure
   *
   * @param bean the bean to convert
   *
   * @return the SOAP structure
   */
  public static com.github.vlachenal.webservice.bench.soap.api.Address toSoap(final AddressBean bean) {
    com.github.vlachenal.webservice.bench.soap.api.Address address = null;
    if(bean != null) {
      address = new com.github.vlachenal.webservice.bench.soap.api.Address();
      if(bean.getLines() != null) {
        address.getLines().addAll(bean.getLines());
      }
      address.setZipCode(bean.getZipCode());
      address.setCity(bean.getCity());
      address.setCountry(bean.getCountry());
    }
    return address;
  }

  /**
   * Convert SOAP address to bean
   *
   * @param address the SOAP address
   *
   * @return the bean
   */
  public static AddressBean toBean(final com.github.vlachenal.webservice.bench.soap.api.Address address) {
    AddressBean bean = null;
    if(address != null) {
      bean = new AddressBean();
      bean.setLines(address.getLines());
      bean.setZipCode(address.getZipCode());
      bean.setCity(address.getCity());
      bean.setCountry(address.getCountry());
    }
    return bean;
  }

  /**
   * Convert addres bean to Protocol buffer structure
   *
   * @param bean the bean to convert
   *
   * @return the Protocol buffer structure
   */
  public static com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address toProtobuf(final AddressBean bean) {
    com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address address = null;
    if(bean != null) {
      final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.Builder builder = com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.newBuilder();
      if(bean.getLines() != null) {
        builder.addAllLines(bean.getLines());
      }
      if(bean.getZipCode() != null) {
        builder.setZipCode(bean.getZipCode());
      }
      if(bean.getCity() != null) {
        builder.setCity(bean.getCity());
      }
      if(bean.getCountry() != null) {
        builder.setCountry(bean.getCountry());
      }
      address = builder.build();
    }
    return address;
  }

  /**
   * Convert Protocol buffer address to bean
   *
   * @param address the Protocol buffer address
   *
   * @return the bean
   */
  public static AddressBean toBean(final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address address) {
    AddressBean bean = null;
    if(address != null) {
      bean = new AddressBean();
      bean.setLines(address.getLinesList());
      bean.setZipCode(address.getZipCode());
      bean.setCity(address.getCity());
      bean.setCountry(address.getCountry());
    }
    return bean;
  }
  // Methods -

}
