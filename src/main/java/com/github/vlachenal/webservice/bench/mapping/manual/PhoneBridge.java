/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import java.util.ArrayList;
import java.util.List;

import com.github.vlachenal.webservice.bench.dto.PhoneDTO;
import com.github.vlachenal.webservice.bench.dto.PhoneDTO.Type;
import com.google.protobuf.Descriptors.Descriptor;


/**
 * Phone conversion utility class
 *
 * @author Vincent Lachenal
 */
public final class PhoneBridge {

  // Attributes +
  /** Protocol Buffer phone descriptor */
  private static final Descriptor PHONE_DESC = com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.getDescriptor();
  // Attributes -


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
  public static com.github.vlachenal.webservice.bench.rest.api.bean.Phone toRest(final PhoneDTO bean) {
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
  public static List<com.github.vlachenal.webservice.bench.rest.api.bean.Phone> toRest(final List<PhoneDTO> bean) {
    List<com.github.vlachenal.webservice.bench.rest.api.bean.Phone> phones = null;
    if(bean != null) {
      phones = new ArrayList<>();
      for(final PhoneDTO phone : bean) {
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
  public static PhoneDTO fromRest(final com.github.vlachenal.webservice.bench.rest.api.bean.Phone phone) {
    PhoneDTO bean = null;
    if(phone != null) {
      bean = new PhoneDTO();
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
  public static List<PhoneDTO> fromRest(final List<com.github.vlachenal.webservice.bench.rest.api.bean.Phone> phones) {
    List<PhoneDTO> bean = null;
    if(phones != null) {
      bean = new ArrayList<>();
      for(final com.github.vlachenal.webservice.bench.rest.api.bean.Phone phone : phones) {
        bean.add(fromRest(phone));
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
  public static com.github.vlachenal.webservice.bench.thrift.api.Phone toThrift(final PhoneDTO bean) {
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
  public static List<com.github.vlachenal.webservice.bench.thrift.api.Phone> toThrift(final List<PhoneDTO> bean) {
    List<com.github.vlachenal.webservice.bench.thrift.api.Phone> phones = null;
    if(bean != null) {
      phones = new ArrayList<>();
      for(final PhoneDTO phone : bean) {
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
  public static PhoneDTO fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.Phone phone) {
    PhoneDTO bean = null;
    if(phone != null) {
      bean = new PhoneDTO();
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
  public static List<PhoneDTO> fromThrift(final List<com.github.vlachenal.webservice.bench.thrift.api.Phone> phones) {
    List<PhoneDTO> bean = null;
    if(phones != null) {
      bean = new ArrayList<>();
      for(final com.github.vlachenal.webservice.bench.thrift.api.Phone phone : phones) {
        bean.add(fromThrift(phone));
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
  public static com.github.vlachenal.webservice.bench.soap.api.Phone toSoap(final PhoneDTO bean) {
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
  public static List<com.github.vlachenal.webservice.bench.soap.api.Phone> toSoap(final List<PhoneDTO> bean) {
    List<com.github.vlachenal.webservice.bench.soap.api.Phone> phones = null;
    if(bean != null) {
      phones = new ArrayList<>();
      for(final PhoneDTO phone : bean) {
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
  public static PhoneDTO fromSoap(final com.github.vlachenal.webservice.bench.soap.api.Phone phone) {
    PhoneDTO bean = null;
    if(phone != null) {
      bean = new PhoneDTO();
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
  public static List<PhoneDTO> fromSoap(final List<com.github.vlachenal.webservice.bench.soap.api.Phone> phones) {
    List<PhoneDTO> bean = null;
    if(phones != null) {
      bean = new ArrayList<>();
      for(final com.github.vlachenal.webservice.bench.soap.api.Phone phone : phones) {
        bean.add(fromSoap(phone));
      }
    }
    return bean;
  }

  /**
   * Convert phone bean to Protocol buffer structure
   *
   * @param bean the bean to convert
   *
   * @return the Protocol buffer structure
   */
  public static com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone toProtobuf(final PhoneDTO bean) {
    com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone phone = null;
    if(bean != null) {
      final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.Builder builder = com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.newBuilder();
      if(bean.getNumber() != null) {
        builder.setNumber(bean.getNumber());
      }
      if(bean.getType() != null) {
        switch(bean.getType()) {
          case LANDLINE:
            builder.setType(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType.LANDLINE);
          case MOBILE:
            builder.setType(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType.MOBILE);
          default:
            // Nothing to do
        }
      }
      phone = builder.build();
    }
    return phone;
  }

  /**
   * Convert phone beans to Protocol buffer structures
   *
   * @param bean the beans to convert
   *
   * @return the SOAP structures
   */
  public static List<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone> toProtobuf(final List<PhoneDTO> bean) {
    List<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone> phones = null;
    if(bean != null) {
      phones = new ArrayList<>();
      for(final PhoneDTO phone : bean) {
        final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone protobuf = toProtobuf(phone);
        if(protobuf != null) {
          phones.add(protobuf);
        }
      }
    }
    return phones;
  }

  /**
   * Convert Protocol buffer phone to bean
   *
   * @param phone the Protocol buffer phone
   *
   * @return the bean
   */
  public static PhoneDTO fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone phone) {
    PhoneDTO bean = null;
    if(phone != null) {
      bean = new PhoneDTO();
      if(phone.hasField(PHONE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.NUMBER_FIELD_NUMBER))) {
        bean.setNumber(phone.getNumber());
      }
      if(phone.hasField(PHONE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.TYPE_FIELD_NUMBER))) {
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
    }
    return bean;
  }

  /**
   * Convert Protocol buffer phone list to bean list
   *
   * @param phones the Protocol buffer phone list
   *
   * @return the bean list
   */
  public static List<PhoneDTO> fromProtobuf(final List<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone> phones) {
    List<PhoneDTO> bean = null;
    if(phones != null) {
      bean = new ArrayList<>();
      for(final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone phone : phones) {
        bean.add(fromProtobuf(phone));
      }
    }
    return bean;
  }
  // Methods -

}
