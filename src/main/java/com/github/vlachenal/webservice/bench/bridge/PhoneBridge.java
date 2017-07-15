/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.bridge;

import java.util.ArrayList;
import java.util.List;

import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean;


/**
 * Phone conversion utility class
 *
 * @author Vincent Lachenal
 */
public final class PhoneBridge {

  // Constructors +
  /**
   * {@link PhoneBridge} private construtor
   */
  private PhoneBridge() {
    // Nothing to do
  }
  // Constructors -


  // Methods +
  /**
   * Convert phone bean to REST/JSON structure
   *
   * @param bean the bean to convert
   *
   * @return the JSON structure
   */
  public static com.github.vlachenal.webservice.bench.rest.api.bean.Phone toRest(final PhoneBean bean) {
    com.github.vlachenal.webservice.bench.rest.api.bean.Phone phone = null;
    if(bean != null) {
      phone = new com.github.vlachenal.webservice.bench.rest.api.bean.Phone();
      phone.setNumber(bean.getNumber());
      if(bean.getType() != null) {
        switch(bean.getType()) {
          case LANDLINE:
            phone.setType(com.github.vlachenal.webservice.bench.rest.api.bean.Phone.Type.LANDLINE);
          case MOBILE:
            phone.setType(com.github.vlachenal.webservice.bench.rest.api.bean.Phone.Type.MOBILE);
          default:
            // Nothing to do
        }
      }
    }
    return phone;
  }

  /**
   * Convert phone beans to REST/JSON structures
   *
   * @param bean the beans to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.rest.api.bean.Phone> toRest(final List<PhoneBean> bean) {
    List<com.github.vlachenal.webservice.bench.rest.api.bean.Phone> phones = null;
    if(bean != null) {
      phones = new ArrayList<com.github.vlachenal.webservice.bench.rest.api.bean.Phone>();
      for(final PhoneBean phone : bean) {
        final com.github.vlachenal.webservice.bench.rest.api.bean.Phone json = toRest(phone);
        if(json != null) {
          phones.add(json);
        }
      }
    }
    return phones;
  }

  /**
   * Convert phone bean to Thift structure
   *
   * @param bean the bean to convert
   *
   * @return the Thrift structure
   */
  public static com.github.vlachenal.webservice.bench.thrift.api.Phone toThrift(final PhoneBean bean) {
    com.github.vlachenal.webservice.bench.thrift.api.Phone phone = null;
    if(bean != null) {
      phone = new com.github.vlachenal.webservice.bench.thrift.api.Phone();
      phone.setNumber(bean.getNumber());
      if(bean.getType() != null) {
        switch(bean.getType()) {
          case LANDLINE:
            phone.setType(com.github.vlachenal.webservice.bench.thrift.api.PhoneType.LANDLINE);
          case MOBILE:
            phone.setType(com.github.vlachenal.webservice.bench.thrift.api.PhoneType.MOBILE);
          default:
            // Nothing to do
        }
      }
    }
    return phone;
  }

  /**
   * Convert phone beans to REST/JSON structures
   *
   * @param bean the beans to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.thrift.api.Phone> toThrift(final List<PhoneBean> bean) {
    List<com.github.vlachenal.webservice.bench.thrift.api.Phone> phones = null;
    if(bean != null) {
      phones = new ArrayList<com.github.vlachenal.webservice.bench.thrift.api.Phone>();
      for(final PhoneBean phone : bean) {
        final com.github.vlachenal.webservice.bench.thrift.api.Phone thrift = toThrift(phone);
        if(thrift != null) {
          phones.add(thrift);
        }
      }
    }
    return phones;
  }
  // Methods -

}
