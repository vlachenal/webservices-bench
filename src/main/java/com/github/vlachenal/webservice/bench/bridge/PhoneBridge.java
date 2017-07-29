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
import com.github.vlachenal.webservice.bench.dao.bean.PhoneBean.Type;


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
   * Convert REST phone to bean
   *
   * @param phone the REST phone
   *
   * @return the bean
   */
  public static PhoneBean toBean(final com.github.vlachenal.webservice.bench.rest.api.bean.Phone phone) {
    PhoneBean bean = null;
    if(phone != null) {
      bean = new PhoneBean();
      bean.setNumber(phone.getNumber());
      switch(phone.getType()) {
        case LANDLINE:
          bean.setType(Type.LANDLINE);
          break;
        case MOBILE:
          bean.setType(Type.MOBILE);
          break;
        default:
          // Nothing to do
      }
    }
    return bean;
  }

  /**
   * Convert REST phone list to bean list
   *
   * @param phones the REST phone list
   *
   * @return the bean list
   */
  public static List<PhoneBean> toBeanRList(final List<com.github.vlachenal.webservice.bench.rest.api.bean.Phone> phones) {
    List<PhoneBean> bean = null;
    if(phones != null) {
      bean = new ArrayList<>();
      for(final com.github.vlachenal.webservice.bench.rest.api.bean.Phone phone : phones) {
        bean.add(toBean(phone));
      }
    }
    return bean;
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

  /**
   * Convert Thrift phone to bean
   *
   * @param phone the Thrift phone
   *
   * @return the bean
   */
  public static PhoneBean toBean(final com.github.vlachenal.webservice.bench.thrift.api.Phone phone) {
    PhoneBean bean = null;
    if(phone != null) {
      bean = new PhoneBean();
      bean.setNumber(phone.getNumber());
      switch(phone.getType()) {
        case LANDLINE:
          bean.setType(Type.LANDLINE);
          break;
        case MOBILE:
          bean.setType(Type.MOBILE);
          break;
        default:
          // Nothing to do
      }
    }
    return bean;
  }

  /**
   * Convert Thrift phone list to bean list
   *
   * @param phones the Thrift phone list
   *
   * @return the bean list
   */
  public static List<PhoneBean> toBeanTList(final List<com.github.vlachenal.webservice.bench.thrift.api.Phone> phones) {
    List<PhoneBean> bean = null;
    if(phones != null) {
      bean = new ArrayList<>();
      for(final com.github.vlachenal.webservice.bench.thrift.api.Phone phone : phones) {
        bean.add(toBean(phone));
      }
    }
    return bean;
  }

  /**
   * Convert phone bean to SOAP structure
   *
   * @param bean the bean to convert
   *
   * @return the SOAP structure
   */
  public static com.github.vlachenal.webservice.bench.soap.api.Phone toSoap(final PhoneBean bean) {
    com.github.vlachenal.webservice.bench.soap.api.Phone phone = null;
    if(bean != null) {
      phone = new com.github.vlachenal.webservice.bench.soap.api.Phone();
      phone.setNumber(bean.getNumber());
      if(bean.getType() != null) {
        switch(bean.getType()) {
          case LANDLINE:
            phone.setPhoneType(com.github.vlachenal.webservice.bench.soap.api.PhoneType.LANDLINE);
          case MOBILE:
            phone.setPhoneType(com.github.vlachenal.webservice.bench.soap.api.PhoneType.MOBILE);
          default:
            // Nothing to do
        }
      }
    }
    return phone;
  }

  /**
   * Convert phone beans to SOAP structures
   *
   * @param bean the beans to convert
   *
   * @return the SOAP structures
   */
  public static List<com.github.vlachenal.webservice.bench.soap.api.Phone> toSoap(final List<PhoneBean> bean) {
    List<com.github.vlachenal.webservice.bench.soap.api.Phone> phones = null;
    if(bean != null) {
      phones = new ArrayList<com.github.vlachenal.webservice.bench.soap.api.Phone>();
      for(final PhoneBean phone : bean) {
        final com.github.vlachenal.webservice.bench.soap.api.Phone soap = toSoap(phone);
        if(soap != null) {
          phones.add(soap);
        }
      }
    }
    return phones;
  }

  /**
   * Convert SOAP phone to bean
   *
   * @param phone the SOAP phone
   *
   * @return the bean
   */
  public static PhoneBean toBean(final com.github.vlachenal.webservice.bench.soap.api.Phone phone) {
    PhoneBean bean = null;
    if(phone != null) {
      bean = new PhoneBean();
      bean.setNumber(phone.getNumber());
      switch(phone.getPhoneType()) {
        case LANDLINE:
          bean.setType(Type.LANDLINE);
          break;
        case MOBILE:
          bean.setType(Type.MOBILE);
          break;
        default:
          // Nothing to do
      }
    }
    return bean;
  }

  /**
   * Convert SOAP phone list to bean list
   *
   * @param phones the SOAP phone list
   *
   * @return the bean list
   */
  public static List<PhoneBean> toBeanSList(final List<com.github.vlachenal.webservice.bench.soap.api.Phone> phones) {
    List<PhoneBean> bean = null;
    if(phones != null) {
      bean = new ArrayList<>();
      for(final com.github.vlachenal.webservice.bench.soap.api.Phone phone : phones) {
        bean.add(toBean(phone));
      }
    }
    return bean;
  }
  // Methods -

}
