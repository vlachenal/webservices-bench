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
  // Methods -

}
