/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.mapping.manual;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
   * Convert phone DTO to REST/JSON structure
   *
   * @param dto the DTO to convert
   *
   * @return the JSON structure
   */
  public static com.github.vlachenal.webservice.bench.rest.api.model.Phone toRest(final PhoneDTO dto) {
    com.github.vlachenal.webservice.bench.rest.api.model.Phone phone = null;
    if(dto != null) {
      phone = new com.github.vlachenal.webservice.bench.rest.api.model.Phone();
      phone.setNumber(dto.getNumber());
      if(dto.getType() != null) {
        switch(dto.getType()) {
          case LANDLINE:
            phone.setType(com.github.vlachenal.webservice.bench.rest.api.model.Phone.Type.LANDLINE);
            break;
          case MOBILE:
            phone.setType(com.github.vlachenal.webservice.bench.rest.api.model.Phone.Type.MOBILE);
            break;
          default:
            // Nothing to do
        }
      }
    }
    return phone;
  }

  /**
   * Convert phone DTOs to REST/JSON structures
   *
   * @param dto the DTOs to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.rest.api.model.Phone> toRest(final List<PhoneDTO> dto) {
    return Optional.ofNullable(dto).map(l -> l.stream().map(phone -> toRest(phone)).collect(Collectors.toList())).orElse(null);
  }

  /**
   * Convert REST phone to DTO
   *
   * @param phone the REST phone
   *
   * @return the DTO
   */
  public static PhoneDTO fromRest(final com.github.vlachenal.webservice.bench.rest.api.model.Phone phone) {
    PhoneDTO dto = null;
    if(phone != null) {
      dto = new PhoneDTO();
      dto.setNumber(phone.getNumber());
      switch(phone.getType()) {
        case LANDLINE:
          dto.setType(Type.LANDLINE);
          break;
        case MOBILE:
          dto.setType(Type.MOBILE);
          break;
        default:
          // Nothing to do
      }
    }
    return dto;
  }

  /**
   * Convert REST phone list to DTO list
   *
   * @param phones the REST phone list
   *
   * @return the DTO list
   */
  public static List<PhoneDTO> fromRest(final List<com.github.vlachenal.webservice.bench.rest.api.model.Phone> phones) {
    return Optional.ofNullable(phones).map(l -> l.stream().map(phone -> fromRest(phone)).collect(Collectors.toList())).orElse(null);
  }

  /**
   * Convert phone DTO to Thift structure
   *
   * @param dto the DTO to convert
   *
   * @return the Thrift structure
   */
  public static com.github.vlachenal.webservice.bench.thrift.api.Phone toThrift(final PhoneDTO dto) {
    com.github.vlachenal.webservice.bench.thrift.api.Phone phone = null;
    if(dto != null) {
      phone = new com.github.vlachenal.webservice.bench.thrift.api.Phone();
      phone.setNumber(dto.getNumber());
      if(dto.getType() != null) {
        switch(dto.getType()) {
          case LANDLINE:
            phone.setType(com.github.vlachenal.webservice.bench.thrift.api.PhoneType.LANDLINE);
            break;
          case MOBILE:
            phone.setType(com.github.vlachenal.webservice.bench.thrift.api.PhoneType.MOBILE);
            break;
          default:
            // Nothing to do
        }
      }
    }
    return phone;
  }

  /**
   * Convert phone DTOs to REST/JSON structures
   *
   * @param dto the DTOs to convert
   *
   * @return the JSON structures
   */
  public static List<com.github.vlachenal.webservice.bench.thrift.api.Phone> toThrift(final List<PhoneDTO> dto) {
    return Optional.ofNullable(dto).map(l -> l.stream().map(phone -> toThrift(phone)).collect(Collectors.toList())).orElse(null);
  }

  /**
   * Convert Thrift phone to DTO
   *
   * @param phone the Thrift phone
   *
   * @return the DTO
   */
  public static PhoneDTO fromThrift(final com.github.vlachenal.webservice.bench.thrift.api.Phone phone) {
    PhoneDTO dto = null;
    if(phone != null) {
      dto = new PhoneDTO();
      dto.setNumber(phone.getNumber());
      switch(phone.getType()) {
        case LANDLINE:
          dto.setType(Type.LANDLINE);
          break;
        case MOBILE:
          dto.setType(Type.MOBILE);
          break;
        default:
          // Nothing to do
      }
    }
    return dto;
  }

  /**
   * Convert Thrift phone list to DTO list
   *
   * @param phones the Thrift phone list
   *
   * @return the DTO list
   */
  public static List<PhoneDTO> fromThrift(final List<com.github.vlachenal.webservice.bench.thrift.api.Phone> phones) {
    return Optional.ofNullable(phones).map(l -> l.stream().map(phone -> fromThrift(phone)).collect(Collectors.toList())).orElse(null);
  }

  /**
   * Convert phone DTO to SOAP structure
   *
   * @param dto the DTO to convert
   *
   * @return the SOAP structure
   */
  public static com.github.vlachenal.webservice.bench.soap.api.Phone toSoap(final PhoneDTO dto) {
    com.github.vlachenal.webservice.bench.soap.api.Phone phone = null;
    if(dto != null) {
      phone = new com.github.vlachenal.webservice.bench.soap.api.Phone();
      phone.setNumber(dto.getNumber());
      if(dto.getType() != null) {
        switch(dto.getType()) {
          case LANDLINE:
            phone.setPhoneType(com.github.vlachenal.webservice.bench.soap.api.PhoneType.LANDLINE);
            break;
          case MOBILE:
            phone.setPhoneType(com.github.vlachenal.webservice.bench.soap.api.PhoneType.MOBILE);
            break;
          default:
            // Nothing to do
        }
      }
    }
    return phone;
  }

  /**
   * Convert phone DTOs to SOAP structures
   *
   * @param dto the DTOs to convert
   *
   * @return the SOAP structures
   */
  public static List<com.github.vlachenal.webservice.bench.soap.api.Phone> toSoap(final List<PhoneDTO> dto) {
    return Optional.ofNullable(dto).map(l -> l.stream().map(phone -> toSoap(phone)).collect(Collectors.toList())).orElse(null);
  }

  /**
   * Convert SOAP phone to DTO
   *
   * @param phone the SOAP phone
   *
   * @return the DTO
   */
  public static PhoneDTO fromSoap(final com.github.vlachenal.webservice.bench.soap.api.Phone phone) {
    PhoneDTO dto = null;
    if(phone != null) {
      dto = new PhoneDTO();
      dto.setNumber(phone.getNumber());
      switch(phone.getPhoneType()) {
        case LANDLINE:
          dto.setType(Type.LANDLINE);
          break;
        case MOBILE:
          dto.setType(Type.MOBILE);
          break;
        default:
          // Nothing to do
      }
    }
    return dto;
  }

  /**
   * Convert SOAP phone list to DTO list
   *
   * @param phones the SOAP phone list
   *
   * @return the DTO list
   */
  public static List<PhoneDTO> fromSoap(final List<com.github.vlachenal.webservice.bench.soap.api.Phone> phones) {
    return Optional.ofNullable(phones).map(l -> l.stream().map(phone -> fromSoap(phone)).collect(Collectors.toList())).orElse(null);
  }

  /**
   * Convert phone DTO to Protocol buffer structure
   *
   * @param dto the DTO to convert
   *
   * @return the Protocol buffer structure
   */
  public static com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone toProtobuf(final PhoneDTO dto) {
    com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone phone = null;
    if(dto != null) {
      final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.Builder builder = com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.newBuilder();
      Optional.ofNullable(dto.getNumber()).ifPresent(number -> builder.setNumber(number));
      if(dto.getType() != null) {
        switch(dto.getType()) {
          case LANDLINE:
            builder.setType(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType.LANDLINE);
            break;
          case MOBILE:
            builder.setType(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType.MOBILE);
            break;
          default:
            // Nothing to do
        }
      }
      phone = builder.build();
    }
    return phone;
  }

  /**
   * Convert phone DTOs to Protocol buffer structures
   *
   * @param dto the DTOs to convert
   *
   * @return the SOAP structures
   */
  public static List<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone> toProtobuf(final List<PhoneDTO> dto) {
    return Optional.ofNullable(dto).map(l -> l.stream().map(phone -> toProtobuf(phone)).collect(Collectors.toList())).orElse(null);
  }

  /**
   * Convert Protocol buffer phone to DTO
   *
   * @param phone the Protocol buffer phone
   *
   * @return the DTO
   */
  public static PhoneDTO fromProtobuf(final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone phone) {
    PhoneDTO dto = null;
    if(phone != null) {
      dto = new PhoneDTO();
      dto.setNumber(ProtobufBridgeUtils.getValue(phone, PHONE_DESC, com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.NUMBER_FIELD_NUMBER));
      if(phone.hasField(PHONE_DESC.findFieldByNumber(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.TYPE_FIELD_NUMBER))) {
        switch(phone.getType()) {
          case LANDLINE:
            dto.setType(Type.LANDLINE);
            break;
          case MOBILE:
            dto.setType(Type.MOBILE);
            break;
          default:
            // Nothing to do
        }
      }
    }
    return dto;
  }

  /**
   * Convert Protocol buffer phone list to DTO list
   *
   * @param phones the Protocol buffer phone list
   *
   * @return the DTO list
   */
  public static List<PhoneDTO> fromProtobuf(final List<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone> phones) {
    return Optional.ofNullable(phones).map(l -> l.stream().map(phone -> fromProtobuf(phone)).collect(Collectors.toList())).orElse(null);
  }
  // Methods -

}
